package io.vitess.service.so;

import com.alibaba.fastjson.JSON;
import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SalesOrderLineCommand;
import io.vitess.common.ErrorCode;
import io.vitess.constants.Constants;
import io.vitess.constants.SalesModelToTOMS;
import io.vitess.constants.SysWmsStatus;
import io.vitess.dao.base.*;
import io.vitess.dao.mq.*;
import io.vitess.dao.so.OrderLocationMappingDao;
import io.vitess.dao.so.SoInvFlowDao;
import io.vitess.dao.so.TradeDao;
import io.vitess.enums.*;
import io.vitess.model.base.*;
import io.vitess.model.mq.*;
import io.vitess.model.so.*;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import io.vitess.service.common.PromotionManager;
import io.vitess.service.common.SkuManager;
import io.vitess.service.common.SpecialSkuManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service("platformSoManager")
public class PlatformSoManagerImpl extends BaseManagerImpl implements PlatformSoManager{

	private static final long serialVersionUID = -7492482366382229324L;

	private static final Logger loggerSoCreate = LoggerFactory.getLogger("logger-tb-so-create");

	@Autowired
	private ProductDao productDao;

	@Autowired
	private TradeDao tradeDao;

	@Autowired
	private MqSoLogDao mqSoLogDao;
	
	@Autowired
	private MqPlatformMemberLogDao mqMemberLogDao;

	@Autowired
	private CompanyShopDao companyShopDao;

	@Autowired
	private MqSoLineLogDao mqSoLineLogDao;

	@Autowired
	private OrderLocationMappingDao orderLocationMappingDao;

	@Autowired
	private MqDeliveryInfoLogDao mqDeliveryInfoLogDao;

	@Autowired
	private SalesOrderManager salesOrderManager;
	
	@Autowired
	private SalesOrderSplitManager salesOrderSplitManager;
	
	@Autowired
	private SpecifySkuAppointmentDao specifySkuAppointmentDao;

	@Autowired
	private PromotionManager promotionManager;

	@Autowired
	private MqSoPackingInfoLogDao mqSoPackingInfoLogDao;
	
	@Autowired
	private SpecialSkuManager specialSkuManager;
	
	@Autowired
	private SkuManager skuManager;
	
	@Autowired
	private SoServiceLineManager soServiceLineManager;
	
	@Autowired
	private PlatformPromotionManager platformPromotionManager;
	
	@Autowired
	private ComBoSkuDao comBoSkuDao;
	
	@Autowired
	private SalesOrderStoreSplitManager salesOrderStoreSplitManager;
	
	@Autowired
	private ComboSkuDetailDao comboSkuDetailDao;

	@Autowired
	private SkuDao skuDao;
	@Autowired
	private SoInvFlowDao soInvFlowDao;


	//eticket：电子凭证订单fanht
	private String PLATFORM_TRADE_TYPE_ETICKET = "eticket";
	// 各种校验

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createTbSoFromMqSoLog(MqSoLog soLog, SalesOrderType orderType, Map<String, String> sourceMap, Long shopId){
		Long mqSoLogId = soLog.getId();
		try {
			//增加创单日志fanht
			Long tId= Thread.currentThread().getId(),
					cct = System.currentTimeMillis();
			String errorMsg = null;
			if(soLog.getShopId() == null){
				errorMsg = "创建订单出错：所属店铺为null";
				//优化代码fanht
				mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
				loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", shopId, soLog.getCode(), errorMsg);
				return;
			}
			String platformOrderCode = soLog.getCode();
			loggerSoCreate.debug("create mq so with platform_order_code{" + platformOrderCode + "} start........");
			CompanyShop shop = null;

			//当前订单所属店铺是否存在
			if (shopId == null || (shop = companyShopDao.findById(shopId)) == null) {
				errorMsg = "创建订单出错：所属店铺不存在，店铺Id[ " + shopId + " ]";
				mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
				loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", shopId, soLog.getCode(), errorMsg);
				return;
			}

			List<MqDeliveryInfoLog> dfLogList = mqDeliveryInfoLogDao.getMqDeliveryInfoLogsBySoLogId(soLog.getId(),shopId);
			MqDeliveryInfoLog deliveryInfoLog = null;
			// 发货信息集中默认取第一条为当前订单发货信息
			deliveryInfoLog = CollectionUtils.isNotEmpty(dfLogList) ? dfLogList.get(0): null;

			// 判断发货信息是否空
			if (deliveryInfoLog == null) {
				// 发货信息为空
				errorMsg = "发货信息为空";
				mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
				loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", shopId, soLog.getCode(), errorMsg);
				return;
			}

//		  A.等待付款的  						WAIT_BUYER_PAY
//	      B.付款前被取消的订单 				TRADE_CLOSED_BY_TAOBAO
//	      C.等待卖家发货的 					WAIT_SELLER_SEND_GOODS
//	      D.等待买家确认收货,即:卖家已发货 	WAIT_BUYER_CONFIRM_GOODS
//	 	  E.交易成功							TRADE_FINISHED
//	 	  F.经销采购单关闭					TRADE_CLOSED
//		  G.???								WAIT_FOR_APPLIER_STORAGE
//		  H.???								WAIT_FOR_SUPPLIER_DELIVER
			// 校验订单平台状态: 各订单平台状态含义
			String platformOrderStatus = soLog.getPlatformOrderStatus();
			//校验淘宝订单当前状态是否符合创建oms订单条件 fanht
			//boolean isLgType = soLog.getIsLgtype() == null ? Boolean.FALSE : soLog.getIsLgtype();
			boolean statusFlag = validateStatusForMqSoLog(platformOrderStatus, shop, soLog.getPlatformType(), soLog);
			if (!statusFlag) {
				errorMsg = "创建订单出错：订单状态不对";
				mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
				loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", shopId, soLog.getCode(), errorMsg);
				return;
			}

			// 校验平台订单号是否存在（自动拆单前）
			Trade existedTd = tradeDao.findByPlatformOrderCode(platformOrderCode, shopId);
			if (existedTd != null) {
				errorMsg = " 创建订单出错：订单已存在";
				mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
				loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", shopId, soLog.getCode(), errorMsg);
				return;
			}
			// 校验该订单所属店铺是否初始化销售模式
			if(shop.getIsApplySalesMode() != null && shop.getIsApplySalesMode() &&  shop.getSalesMode() == null){
				errorMsg = "创建订单出错:"+shop.getId()+"该店铺未初始化销售模式";
				mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
				loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", shopId, soLog.getCode(), errorMsg);
				return;
			}

			//创建
			createTbSoFromMqSoLog(shop, soLog, deliveryInfoLog, orderType, sourceMap);

			//增加创单日志fanht
			Long tmpt = System.currentTimeMillis();
			log.info("[Thread-{}]Create One TB shopId[{}] Order[{}] within {}ms", tId, shopId, soLog.getCode(), tmpt-cct);
		}catch(BusinessException be) {
			mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), JSON.toJSONString(be.getArgs()), null);
			loggerSoCreate.error("---------createTaobaoSo Error, mqSoLogId:" + mqSoLogId + "------------", be);
		}catch (Exception e) {
			mqCreateSoErrorCount(mqSoLogId, shopId, e.getMessage());
			loggerSoCreate.error("---------createTaobaoSo Error, mqSoLogId:" + mqSoLogId + "------------",e);
		}
	}

	/**
	 * 记录错误次数,不允许超过3次
	 * @param mqSoLogId
	 */
	private void mqCreateSoErrorCount(Long mqSoLogId, Long shopId, String msg) {
		MqSoLog mqSoLog = mqSoLogDao.findMqSoLogByIdShopId(mqSoLogId, shopId);
		Integer initCount = new Integer(1);
		Integer errorCount = mqSoLog.getErrorCount() == null ? initCount : initCount + mqSoLog.getErrorCount();
		int orderStatus = mqSoLog.getStatus();
		if(errorCount >= 3){
			orderStatus = MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue();
			msg = mqSoLog.getErrorMsg() + "(创单错误次数超过3次)";
		}

		mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, orderStatus, msg, errorCount);
	}
	
	private void copySoLogProperties(SalesOrderCommand soCmd, MqSoLog soLog) {
	    soCmd.setExtVc1(calcExtVc(soLog.getExtVc1()));
	    soCmd.setExtVc2(calcExtVc(soLog.getExtVc2()));
        soCmd.setExtVc3(calcExtVc(soLog.getExtVc3()));
	}

	private BigDecimal calcExtVc(BigDecimal vc) {
		if (vc == null) {
			return BigDecimal.ZERO;
		}

		return vc.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_DOWN);
	}
	
	private void createTbSoFromMqSoLog(CompanyShop shop, MqSoLog soLog, MqDeliveryInfoLog deliveryInfoLog, SalesOrderType orderType, Map<String, String> sourceMap){
		String platformOrderCode = soLog.getCode();
		Date platformCreateTime = soLog.getOuterCreateTime();

		// 1. 构建 SO 信息
		SalesOrderCommand soCmd = new SalesOrderCommand();
		
		soCmd.setBuyerTaxNO(soLog.getBuyerTaxNO());
		
		//处理支付宝积分、天猫点券卡fanht
		this.copySoLogProperties(soCmd, soLog);
		soCmd.setPlatformOrderCode(platformOrderCode);
		soCmd.setOrderType(orderType.getValue());
		soCmd.setCompanyShop(shop.getId());
		soCmd.setOrderStatus(SalesOrderStatus.CREATED.getValue()); // 此处订单创建时订单新建未付款
		soCmd.setFinanceStatus(SoFinanceStatus.FULLPAYMENT.getValue()); //xin.feng 默认只接全额付款的订单，预售的订单会在后面重置此字段的值,淘宝无COD订单
		soCmd.setPlatformPaymentTime(soLog.getPaymentTime());
		soCmd.setMainPaymentType(shop.getDefPaymentType()); // 付款方式 取店铺默认支付方式
		soCmd.setPayAccount(soLog.getPayAccount());
		soCmd.setPayNo(soLog.getPayNo());
		soCmd.setPlatformCreateTime(platformCreateTime);
		soCmd.setInvoiceTitle(soLog.getInvoiceTitle());
		soCmd.setInvoiceMemo(soLog.getInvoiceContent());
		soCmd.setInvoiceKind(soLog.getInvoiceKind());
		soCmd.setSourceType(SoSourceType.TB.getValue());
		//平台交易类型fanht
		soCmd.setPlatformTradeType(soLog.getPlatformOrderType());
		soCmd.setSourceRemark(soLog.getSource());
		soCmd.setBuyerMemo(soLog.getRemark());
		
		// 订单定制信息
		soCmd.setCustomizationMemo(soLog.getCustomizationMemo());
		
		soCmd.setSellerMemo(soLog.getSellerMemo());
		soCmd.setNeededPacking((soLog.getIsNeededPacking() != null && soLog.getIsNeededPacking()) ? Constants.YES : Constants.NO);
		soCmd.setAlipayId(soLog.getAlipayId());
		soCmd.setCreditCardFee(soLog.getCreditCardFee() == null ? BigDecimal.ZERO: new BigDecimal(soLog.getCreditCardFee()));
		soCmd.setInvoiceType(InvoiceType.ORDINARY_COMMERCIAL.getValue());
		soCmd.setIsBillingManual(Constants.NO);
		soCmd.setOrderTaxFee(soLog.getOrderTaxFee());

		// 2.构建配送信息
		soCmd.setCountry(deliveryInfoLog.getCountry());
		String province = deliveryInfoLog.getProvince();
		String city = deliveryInfoLog.getCity();
		String district = deliveryInfoLog.getDistrict();
		soCmd.setProvince(province);
		soCmd.setCity(city);
		soCmd.setDistrict(district);
		StringBuffer addrBuf = new StringBuffer(province == null ? "" : province);
		addrBuf.append(city == null ? "" : " " + city);
		addrBuf.append(district == null ? "" : " " + district);
		addrBuf.append(" " + deliveryInfoLog.getAddress());
		soCmd.setAddress(addrBuf.toString());
		soCmd.setZipcode(deliveryInfoLog.getZipCode());
		soCmd.setContactPerson(deliveryInfoLog.getContactPerson());
		soCmd.setReceiver(deliveryInfoLog.getReceiver());
		String mobile = deliveryInfoLog.getReceiverMobile() == null ? "" : deliveryInfoLog.getReceiverMobile();
		String phone = deliveryInfoLog.getReceiverPhone() == null ? "" : deliveryInfoLog.getReceiverPhone();
		soCmd.setMobile(mobile);
		soCmd.setReceiverPhone(phone);
		soCmd.setTransExpCode(deliveryInfoLog.getLpCode());
		soCmd.setIsBundle(soLog.getIsBundle() == null ? false: soLog.getIsBundle());
		
		// 3.构建SoPlatformMember信息 // 平台会员信息
		MqPlatformMemberLog memLog = mqMemberLogDao.findMqPlatformMemberLog(soLog.getId(), soLog.getShopId());
		OrderMember orderMember = null;
		if (memLog != null) {
			orderMember = new OrderMember();
			orderMember.setPlatformMemberCode(memLog.getLoginName());
			orderMember.setRealName(memLog.getRealName());
			orderMember.setPhone(memLog.getTelephone());
			orderMember.setMobile(memLog.getMobile());
			orderMember.setEmail(memLog.getEmail());
			orderMember.setCountry(memLog.getCountry());
			orderMember.setProvince(memLog.getProvince());
			orderMember.setCity(memLog.getCity());
			orderMember.setDistrict(memLog.getDistrict());
			orderMember.setAddress(memLog.getAddress());
			orderMember.setShopId(shop.getId());
			soCmd.setOrderMember(orderMember);
			soCmd.setMemberEmail(orderMember.getEmail());
			soCmd.setMemberName(orderMember.getRealName());
		}

		// 4. 构建订单行信息
		List<SalesOrderLineCommand> solCmdList = constructSoLine(soLog, shop);
		if(solCmdList == null){
			return;
		}
		
		//订单行金额汇总
		BigDecimal sumTotalAmountOfLine = BigDecimal.ZERO; // 订单行unitPrice*qty总和
		BigDecimal sumTotalDiscountOfLine = BigDecimal.ZERO; // 订单行优惠总和
		
		for(SalesOrderLineCommand soLineCommand: solCmdList){
			sumTotalAmountOfLine = sumTotalAmountOfLine.add(soLineCommand.getTotalAmount());
			sumTotalDiscountOfLine.add(soLineCommand.getLineDiscount());
		}

		soCmd.setTransFee(soLog.getActualTransFee());
		soCmd.setAmountBeforeDiscount(sumTotalAmountOfLine);
		soCmd.setAmountAfterDiscount(soLog.getTotalActual());
		soCmd.setDiscountTotal(soCmd.getAmountBeforeDiscount().subtract(soCmd.getAmountAfterDiscount()));
		// FIXME 没有任何意义  yy sumTotalDiscountOfLine
		soCmd.setLinesDiscount(sumTotalDiscountOfLine);
		soCmd.setHeadDiscount(soCmd.getDiscountTotal().subtract(soCmd.getLinesDiscount()));
		soCmd.setPaymentDiscount(BigDecimal.ZERO);
		// 积分
		BigDecimal totalOuterPoint = soLog.getTotalOuterPoint() == null ? BigDecimal.ZERO : soLog.getTotalOuterPoint();
		BigDecimal totalInnerPoint = soLog.getTotalInnerPoint() == null ? BigDecimal.ZERO : soLog.getTotalInnerPoint();
		soCmd.setTotalInnerPoint(totalInnerPoint);
		soCmd.setTotalOuterPoint(totalOuterPoint);
		soCmd.setUsePoint(totalOuterPoint.add(totalInnerPoint));
		soCmd.setVirtualAmount(soCmd.getUsePoint().divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_DOWN));
		//服务费如何分摊？
		soCmd.setServiceItemFee(soLog.getServiceItemFee());
		SkuAppointment skuAppointment = null;
		List<SkuAppointment> skuAppointmentList = new ArrayList<SkuAppointment>();
		for(SalesOrderLineCommand soLineCommand: solCmdList){
			//查询是否是预约过仓的订单  
			 skuAppointment =  specifySkuAppointmentDao.queryAppointmentSku(soLineCommand.getSku(), soLineCommand.getShopId(),soLog.getPaymentTime());
			if(skuAppointment!=null){
				skuAppointmentList.add(skuAppointment);
			}
		}
		//如果是多条秒杀商品则取 预约时长 最小的那个
		if(skuAppointmentList.size()>0){
			if(skuAppointmentList.size() == 1){
				skuAppointment = skuAppointmentList.get(0);
			}else{
				// 按行的toWhTime1升序排列
		        Collections.sort(skuAppointmentList, new Comparator<SkuAppointment>() {
					@Override
					public int compare(SkuAppointment s1, SkuAppointment s2) {
						BigDecimal toWhTime1 = new BigDecimal(s1.getToWhTime()) ;
						BigDecimal toWhTime2 =  new BigDecimal(s2.getToWhTime()) ;
						 int flag = toWhTime1.compareTo(toWhTime2);
		                 return flag;
					}
		        });
		    	skuAppointment = skuAppointmentList.get(0);
			}
		}
	    //预售订单定金
		soCmd.setStepPaidFee(soLog.getStepPaidFee());
			
		// 订单类型确定：soLog.getSpecialType() 普通订单、物流宝订单 yy 预售、预包装
		soCmd.setSpecialType(getSoSpecialType(soLog, solCmdList, skuAppointment, shop, soCmd).getValue());
		
		soCmd.setSoLineCommandList(solCmdList);
		
	    // 应用平摊让利与积分 fanht 折扣均摊提到促销前面
        salesOrderManager.assignPromotionfee(soCmd);
        
        // O2O店铺 O2O订单fanht
        if (shop.getIsO2OShop() != null && shop.getIsO2OShop()) {
            String errorMsgResult = salesOrderManager.validateBranchWarehouseCode(shop, soCmd, solCmdList);
            if (org.springframework.util.StringUtils.hasText(errorMsgResult)) {
                throw new BusinessException(ErrorCode.SO_BRANCH_WAREHOUSE_CODE_VALIDATE_ERROR_MSG, new Object[] {errorMsgResult});
            }
            //如果是O2O订单
            if ((soCmd.getIsO2oOrder() != null && soCmd.getIsO2oOrder())) {
            	//判定O2O订单明细行，是否为有售后服务
            	for (SalesOrderLineCommand soLineCmd : soCmd.getSoLineCommandList()) {
        			if (soLineCmd.getOrderLineType() != null && OrderLineType.isNotTmallPlatformGift(soLineCmd.getOrderLineType())) {//赠品
        				continue;
        			}
					Sku sku = skuDao.findById(soLineCmd.getSku());
					String exCode = sku.getExtensionCode1();
        			SpecialSku specialSku = specialSkuManager.findSpecialSku(shop.getId(), exCode, SpecialSkuType.O2O_POST_SALE_SERVICE);
        			soLineCmd.setIsPostSalesServiceSku(specialSku != null ? Boolean.TRUE : Boolean.FALSE);
        		}
            }
        }
        
		// 促销
		soCmd = promotionManager.applyPromotionAndAddGiftLine(soCmd);

		// VMI促销
		Boolean needApplyVmiPromotion = shop.getIsApplyVmiPromotion();
		if (needApplyVmiPromotion != null && needApplyVmiPromotion) {
			soCmd = promotionManager.applyVmiPromotion(soCmd);
		}
		
		List<SalesOrderLineCommand> lineCommands = soCmd.getSoLineCommandList();
		for(SalesOrderLineCommand lineCommand: lineCommands){
			//订单行上打标销售模式
			if(shop.getIsApplySalesMode() != null && shop.getIsApplySalesMode()){
				//获取店铺模式
				String shopModelStr = SalesModelToTOMS.mappingSalesModel(shop.getSalesMode());
				String[] shopModel = shopModelStr.split(",");


				Sku sku = skuDao.findById(lineCommand.getSku());
				Product byId = productDao.findById(sku.getProduct());
				Integer salesMode = byId.getSalesMode();
				if(salesMode == null){
					String errorMsg = "创建订单出错:"+sku.getId()+"该商品未初始化销售模式";
					mqSoLogDao.updateStatusAndErrorMsgById(soLog.getId(), soLog.getShopId(), null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
					loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", soLog.getShopId(), soLog.getCode(), errorMsg);
					return;
				}else{
					//订单行经营模式打标fanht
					String proModelStr = SalesModelToTOMS.mappingSalesModel(salesMode) ;
					String[] proModel = proModelStr.split(",");
					this.markeSoLineModel(shopModel, proModel, lineCommand);
				}
			}
		}
		
		// salesorder service information
		soServiceLineManager.applySoServiceInfo(shop, soLog, soCmd);
		
		platformPromotionManager.constructSoPromotionInfo(shop, soLog, soCmd);
		
		//构建交易头Trade
		Trade trade = new Trade();
			trade.setPlatformOrderCode(platformOrderCode);
			trade.setPlatformOrderCreateTime(platformCreateTime);
			trade.setCompanyShop(shop.getId());
			trade.setPayAccount(soCmd.getPayAccount());
			trade.setPayNo(soCmd.getPayNo());
			trade.setIsSplit(Constants.NO);
			trade.setOrderCount(1);
		
		if (shop.getSkuSplitType() != null 
				&& (SkuSplitType.NO_SPLIT_BY_SKU_DEFAULT_WH.getValue() == shop.getSkuSplitType()
					|| SkuSplitType.SPLIT_BY_SKU_DEFAULT_WH.getValue() == shop.getSkuSplitType()
					|| SkuSplitType.SPLIT_BY_SKU_NUM.getValue() == shop.getSkuSplitType()
					|| SkuSplitType.O2O_SALES_SERVICE_TYPE.getValue() == shop.getSkuSplitType()
					|| SkuSplitType.DESIGNATED_WH_BY_SKU.getValue() == shop.getSkuSplitType()
			)) {
			//订单需要拆分fanht
			List<SalesOrderCommand> _socList = null;
			if (SkuSplitType.SPLIT_BY_SKU_NUM.getValue() == shop.getSkuSplitType()){
				//按商品数量拆
				_socList = salesOrderSplitManager.splitTradeBySkuNum(shop, soCmd);
			} else if (shop.getIsO2OShop() != null && shop.getIsO2OShop() && (soCmd.getIsO2oOrder() != null && soCmd.getIsO2oOrder()) && SkuSplitType.O2O_SALES_SERVICE_TYPE.getValue() == shop.getSkuSplitType()) {
				//按O2O订单服务类型拆
				_socList = salesOrderSplitManager.splitTradeByO2oSalesServiceType(shop, soCmd);
			} else if(SkuSplitType.DESIGNATED_WH_BY_SKU.getValue() == shop.getSkuSplitType()){
				_socList = salesOrderSplitManager.splitOrderDesignatedWhBySku(shop, soCmd);
			}else {
				//按店铺SKU默认仓拆分订单；按SKU指定仓库
				_socList = salesOrderManager.splitTradeByShopSkuWh(soCmd, shop);
			}
			
			trade.setIsSplit(_socList.size() > 1 ? Constants.YES : Constants.NO);
			trade.setOrderCount( _socList.size());
			tradeDao.insert(trade);
			
			// 销售模式设置为第一个订单行的第一个商品
			Sku sku = skuDao.findById(soCmd.getSoLineCommandList().get(0).getSku());
			Product byId = productDao.findById(sku.getProduct());
			SalesMode salesMode = salesOrderManager.retainSalesModeByShopAndProduct(byId.getSalesModesStr(), shop.getSalesModesStr());
			
			List<String> platformOrderCodeNs = new ArrayList<String>();
			List<String> soLineSkuList = new ArrayList<String>();
			for (int i = 1; i <= _socList.size(); i++) {
				SalesOrderCommand _soc = _socList.get(i - 1);
				_soc.setTrade(trade.getId());
				_soc.setPlatformOrderCodeN(_socList.size() == 1 ? platformOrderCode : platformOrderCode + Constants.PLATFORMORDERCODEN_TAG + i);
				soCmd.setSalesModesStr(salesMode.getValue() + "");
				SalesOrder newSo = new SalesOrder();
				Integer assignModel = Constants.NEEDED_ASSIGN;
				SalesOrder persistSo = salesOrderManager.generateSalesOrderByCommand(newSo, _soc, null, orderType, assignModel, sourceMap,skuAppointment);
				loggerSoCreate.debug("so created success:platform_order_code_n[" + persistSo.getPlatformOrderCodeN() + "],code[" + persistSo.getOmsOrderCode() + "]");
				if (Constants.YES.equals(trade.getIsSplit())) {
					salesOrderSplitManager.fillSpiltSmsParam(persistSo, platformOrderCodeNs, soLineSkuList, shop.getId());
				}
			}
			if (Constants.YES.equals(trade.getIsSplit())) {
				String platformOrderCodes = "{" + org.apache.commons.lang3.StringUtils.join(platformOrderCodeNs, "/") + "}";
				String skuListStr = "{" + org.apache.commons.lang3.StringUtils.join(soLineSkuList, "/") + "}";
//				smsDataParamManager.splitOrderSms(shop, trade.getPlatformOrderCode(), platformOrderCodes, skuListStr, _socList.size(), org.springframework.util.StringUtils.hasText(mobile) ? mobile : phone, Constants.AUTO);
			}
		}else if(SkuSplitType.SPLIT_BY_STORE.getValue() == shop.getSkuSplitType()){
			// 新增全渠道订单按照门店CODE拆单
			List<SalesOrderCommand> _socList = null;
			//如果开启全渠道开关且淘宝全渠道标示有数据，此时需要查看是否要拆单
			if(!StringUtils.isBlank(soLog.getOmnichannelParam())){
				//BY STORE 进行拆单主方法
				_socList = salesOrderStoreSplitManager.splitOrderByStore(shop, soCmd);
				splitOrder(shop, orderType, sourceMap, platformOrderCode, mobile, phone, skuAppointment, trade,
						_socList);
			}else{
				//订单不用拆分fanht
				trade.setIsSplit(Constants.NO);
				trade.setOrderCount(1);
				tradeDao.insert(trade);
				
				soCmd.setTrade(trade.getId());
				soCmd.setPlatformOrderCodeN(platformOrderCode);
				// 销售模式设置为第一个订单行的第一个商品 TODO 这个东西没啥用fanht
				SalesMode salesMode ;
					Sku sku = skuDao.findById(soCmd.getSoLineCommandList().get(0).getSku());
					Product byId = productDao.findById(sku.getProduct());
				try{
					salesMode = salesOrderManager.retainSalesModeByShopAndProduct(byId.getSalesModesStr(), shop.getSalesModesStr());
					soCmd.setSalesModesStr(salesMode.getValue() + "");
				}catch (Exception e) {
					log.error(soCmd.getSoLineCommandList().size() + " skuCode: " +sku.getCode() + "productCode: " +byId.getCode());
					throw new RuntimeException(e.getMessage());
				}

				SalesOrder newSo = new SalesOrder();
				Integer assignModel = Constants.NEEDED_ASSIGN;
				SalesOrder persistSo = salesOrderManager.generateSalesOrderByCommand(newSo, soCmd, null, orderType, assignModel, sourceMap,skuAppointment);
				loggerSoCreate.debug("so created success:platform_order_code_n[" + persistSo.getPlatformOrderCodeN() + "],code[" + persistSo.getOmsOrderCode() + "]");
			}
			
		}else if (Constants.YES.equals(shop.getIsAutoSplitOrder())) {
			//TODO 按经营模式拆单（已作废）fanht
			List<SalesOrderCommand> _socList = salesOrderManager.splitTrade(soCmd);
			trade.setIsSplit(_socList.size() > 1 ? Constants.YES : Constants.NO);
			trade.setOrderCount(_socList.size());
			tradeDao.insert(trade);

			List<String> platformOrderCodeNs = new ArrayList<String>();
			List<String> soLineSkuList = new ArrayList<String>();
			for (int i = 1; i <= _socList.size(); i++) {
				SalesOrderCommand _soc = _socList.get(i - 1);
				_soc.setTrade(trade.getId());
				_soc.setPlatformOrderCodeN(platformOrderCode + Constants.PLATFORMORDERCODEN_TAG + i);
				SalesOrder newSo = new SalesOrder();
				Integer assignModel = Constants.NEEDED_ASSIGN;
				SalesOrder persistSo = salesOrderManager.generateSalesOrderByCommand(newSo, _soc, null, orderType, assignModel, sourceMap,skuAppointment);
				loggerSoCreate.debug("so created success:platform_order_code_n[" + persistSo.getPlatformOrderCodeN() + "],code[" + persistSo.getOmsOrderCode() + "]");
				if (Constants.YES.equals(trade.getIsSplit())) {
					salesOrderSplitManager.fillSpiltSmsParam(persistSo, platformOrderCodeNs, soLineSkuList, shop.getId());
				}
			}
			if (Constants.YES.equals(trade.getIsSplit())) {
				String platformOrderCodes = "{" + org.apache.commons.lang3.StringUtils.join(platformOrderCodeNs, "/") + "}";
				String skuListStr = "{" + org.apache.commons.lang3.StringUtils.join(soLineSkuList, "/") + "}";
//				smsDataParamManager.splitOrderSms(shop, trade.getPlatformOrderCode(), platformOrderCodes, skuListStr, _socList.size(), org.springframework.util.StringUtils.hasText(mobile) ? mobile : phone, Constants.AUTO);
			}
		}else if(shop.getIsShippingMethods()){
			//按照配送方式拆单
			List<SalesOrderCommand> _socList = null;
			_socList = salesOrderStoreSplitManager.splitOrderByShippingMethods(shop, soCmd);
			splitOrder(shop, orderType, sourceMap, platformOrderCode, mobile, phone, skuAppointment, trade,
					_socList);
		}else {
			//订单不用拆分fanht
			trade.setIsSplit(Constants.NO);
			trade.setOrderCount(1);
			tradeDao.insert(trade);
			soCmd.setTrade(trade.getId());
			soCmd.setPlatformOrderCodeN(platformOrderCode);
			// 销售模式设置为第一个订单行的第一个商品 TODO 这个东西没啥用fanht
			SalesMode salesMode ;

			Sku sku = skuDao.findById(soCmd.getSoLineCommandList().get(0).getSku());
			Product byId = productDao.findById(sku.getProduct());
			try{
				salesMode = salesOrderManager.retainSalesModeByShopAndProduct(byId.getSalesModesStr(), shop.getSalesModesStr());
				soCmd.setSalesModesStr(salesMode.getValue() + "");
			}catch (Exception e) {
				log.error(soCmd.getSoLineCommandList().size() + " skuCode: " + sku.getCode() + "productCode: " +byId.getCode());
				throw new RuntimeException(e.getMessage());
			}

			SalesOrder newSo = new SalesOrder();
			Integer assignModel = Constants.NEEDED_ASSIGN;
			SalesOrder persistSo = salesOrderManager.generateSalesOrderByCommand(newSo, soCmd, null, orderType, assignModel, sourceMap,skuAppointment);
			loggerSoCreate.debug("so created success:platform_order_code_n[" + persistSo.getPlatformOrderCodeN() + "],code[" + persistSo.getOmsOrderCode() + "]");
		}

		loggerSoCreate.debug("trade created success:platform_order_code[" + platformOrderCode + "]");
		//创单成功修改状态fanht
		mqSoLogDao.updateStatusAndErrorMsgById(soLog.getId(), soLog.getShopId(), null, MqSoLogStatus.MQ_SO_STATUS_SUCCESS.getValue(), null, null);
	}

	private void splitOrder(CompanyShop shop, SalesOrderType orderType, Map<String, String> sourceMap,
                            String platformOrderCode, String mobile, String phone, SkuAppointment skuAppointment, Trade trade,
                            List<SalesOrderCommand> _socList) {
		trade.setIsSplit(_socList.size() > 1 ? Constants.YES : Constants.NO);
		trade.setOrderCount( _socList.size());
		tradeDao.insert(trade);
		List<String> platformOrderCodeNs = new ArrayList<String>();
		List<String> soLineSkuList = new ArrayList<String>();
		for (int i = 1; i <= _socList.size(); i++) {
			SalesOrderCommand _soc = _socList.get(i - 1);
			_soc.setTrade(trade.getId());
			_soc.setPlatformOrderCodeN(_socList.size() == 1 ? platformOrderCode : platformOrderCode + Constants.PLATFORMORDERCODEN_TAG + i);
			SalesOrder newSo = new SalesOrder();
			Integer assignModel = Constants.NEEDED_ASSIGN;
			SalesOrder persistSo = salesOrderManager.generateSalesOrderByCommand(newSo, _soc, null, orderType, assignModel, sourceMap,skuAppointment);
			loggerSoCreate.debug("so created success:platform_order_code_n[" + persistSo.getPlatformOrderCodeN() + "],code[" + persistSo.getOmsOrderCode() + "]");
			if (Constants.YES.equals(trade.getIsSplit())) {
				salesOrderSplitManager.fillSpiltSmsParam(persistSo, platformOrderCodeNs, soLineSkuList, shop.getId());
			}
		}
		if (Constants.YES.equals(trade.getIsSplit())) {
			String platformOrderCodes = "{" + org.apache.commons.lang3.StringUtils.join(platformOrderCodeNs, "/") + "}";
			String skuListStr = "{" + org.apache.commons.lang3.StringUtils.join(soLineSkuList, "/") + "}";
//			smsDataParamManager.splitOrderSms(shop, trade.getPlatformOrderCode(), platformOrderCodes, skuListStr, _socList.size(), org.springframework.util.StringUtils.hasText(mobile) ? mobile : phone, Constants.AUTO);
		}
	}

	/**
	 * 给订单行打标
	 * @param shopModel
	 * @param skuModel
	 * @param line
	 */
	private void markeSoLineModel(String[] shopModel, String[] skuModel, SalesOrderLine line){
		for(String str: shopModel){
			for(String s: skuModel){
				if(str.equals(s)){
					line.setSalesModel(Integer.parseInt(s));
					return;
				}
			}
		}
	}

	private Integer checkProductType(Sku sku, Integer productType) {

		Product byId = productDao.findById(sku.getProduct());
		Integer type = byId.getType();
		//判断虚拟商品有无库存
		if (ProductType.VIRTUAL_GOODS.getValue() == type) {
			productType = ProductType.VIRTUAL_GOODS.getValue();
		} else if (ProductType.VIRTUAL_NO_GOODS.getValue() == type) {
			productType = ProductType.VIRTUAL_NO_GOODS.getValue();
		}
		
		return productType;
	}

	/**
	 * 校验淘宝订单当前状态是否符合创建oms订单条件
	 * 淘宝普通/分销/经销/o2o
	 * @param platformStatus
	 * @param shop
	 * @param platefrom
	 * @param soLog
	 * @return
	 */
	private boolean validateStatusForMqSoLog(String platformStatus, CompanyShop shop, int platefrom, MqSoLog soLog){
		boolean flag = true;
		if (platefrom == PlatformType.TAOBAO_PLATFORM.getValue()) {	//淘宝普通订单
			
//			判断是否是电子凭证订单
			if(PLATFORM_TRADE_TYPE_ETICKET.equals(soLog.getPlatformOrderType())){
				//电子券订单  店铺开关,IsCreateEticket是否创建 电子凭证订单
				if(shop.getIsCreateEticket()){
					//解析电子券订单
					if ( !"WAIT_SELLER_SEND_GOODS".equals(platformStatus) && !"WAIT_BUYER_CONFIRM_GOODS".equals(platformStatus) && !"TRADE_FINISHED".equals(platformStatus)) {
						flag = false;
					}
				}else{
					flag = false;
				}
				
			}else{
				//非电子券订单
				//xin.feng 开启预售的店铺step_trade_status字段是FRONT_PAID_FINAL_NOPAID状态的订单也可以创单
				if (!("WAIT_SELLER_SEND_GOODS".equals(platformStatus) || 
						((shop.getIsPresale() || shop.getIsPrepackage()) && STEP_TRADE_STATUS_FRONT_PAID.equals(soLog.getStepTradeStatus())))) {
					flag = false;
				}
				
			}
			
			flag = validateAutoDeliveryOrder(flag, soLog.getPlatformOrderType(), platformStatus, shop);
			
		} else if (platefrom == PlatformType.TB_FENXIAO.getValue()) {//分销
			if (!"WAIT_SELLER_SEND_GOODS".equals(platformStatus) && !"WAIT_BUYER_CONFIRM_GOODS".equals(platformStatus)) {
				flag = false;
			}
		} else if (platefrom == PlatformType.TB_DEALER.getValue()) {//经销
			if (!"WAIT_FOR_SUPPLIER_DELIVER".equals(platformStatus) && !"WAIT_FOR_APPLIER_STORAGE".equals(platformStatus)) {
				flag = false;
			}
		} else if (platefrom == PlatformType.TAOBAO_TM_O2O.getValue()) {
			//新增淘宝天猫O2O类型
			if (platformStatus == null) {
				flag = false;
			} else if (platformStatus != null && !platformStatus.equalsIgnoreCase("WAIT_SEND_GOOD") && !platformStatus.equalsIgnoreCase("WAIT_CONFIRM_GOOD")) {
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;

	}

	/**
	 * 校验自动发货淘宝订单当前状态是否符合创建oms订单条件
	 * @author bacui.lu
	 * @date 2016年1月15日
	 * @param flag
	 * @param platformOrderType
	 * @param platformStatus
	 * @param shop 
	 * @return
	 */
	private boolean validateAutoDeliveryOrder(boolean flag, String platformOrderType,
                                              String platformStatus, CompanyShop shop) {
		if (!Constants.PLATFORM_TRADE_TYPE_AUTO_DELIVERY.equals(platformOrderType)) {
			return flag;
			
		}
		
		if (shop.getIsCreateAutoDelivery() != null && shop.getIsCreateAutoDelivery()) {
			//解析自动发货订单
			if ( "WAIT_BUYER_CONFIRM_GOODS".equals(platformStatus) || "TRADE_FINISHED".equals(platformStatus)) {
				flag = true;
			}
		} else {
			flag = false;
		}
		
		return flag;
	}

	private List<SalesOrderLinePackage> constructPackingInfo(List<MqSoPackingInfoLog> linePackingInfoList){
		List<SalesOrderLinePackage> results = new ArrayList<SalesOrderLinePackage>();
		for (MqSoPackingInfoLog pi : linePackingInfoList) {
			SalesOrderLinePackage packingInfo = new SalesOrderLinePackage();
			packingInfo.setPackageType(pi.getType());
			packingInfo.setRemark(pi.getMemo());
			packingInfo.setShopId(pi.getShopId());
			results.add(packingInfo);
		}
		return results;
	}

	/**
	 * 构建组合商品订单行信息
	 * @param comboSku
	 * @param soLineLog
	 * @param rQty
	 * @param shopId
	 * @param index
	 * @return
	 */
	private List<SalesOrderLineCommand> constructCmboSkuSoLine(ComboSku comboSku, MqSoLineLog soLineLog, Integer rQty, Long shopId, Integer index){
		List<SalesOrderLineCommand> solCmdList = new ArrayList<SalesOrderLineCommand>();
		// 根据extentionCode找到对应的组合comBoSku,根据comBoSku.getId()找到对应的comBoSkuLine信息,然后将comBoSkuLine解析成soLine

		List<ComboSkuDetail> cbLine = comboSkuDetailDao.findDetailByComBoId(comboSku.getId());
		//有金额的组合商品行
		List<ComboSkuDetail> havePriceList = new ArrayList<ComboSkuDetail>();
		//没用金额的组合商品行
		List<ComboSkuDetail> noPriceList = new ArrayList<ComboSkuDetail>();
		//分离有金额 和 无金额
		for(ComboSkuDetail bean : cbLine){
			if(bean.getUnitPrice().compareTo(BigDecimal.ZERO)>0){
				havePriceList.add(bean);
			}else{
				noPriceList.add(bean);
			}
		}
		
		// 按行的Qty*unitPric升序排列
        Collections.sort(havePriceList, new Comparator<ComboSkuDetail>() {
			@Override
			public int compare(ComboSkuDetail o1, ComboSkuDetail o2) {
				BigDecimal total1 = o1.getUnitPrice().multiply(new BigDecimal(o1.getQuantity()));
				BigDecimal total2 = o2.getUnitPrice().multiply(new BigDecimal(o2.getQuantity()));
				 int flag = total1.compareTo(total2);
                 return flag;
			}
        });
		
        //记录已分配金额
        BigDecimal usedTotal = new BigDecimal(0);
        for(int i=0 ; i<havePriceList.size() ; i++){
        	ComboSkuDetail csd = havePriceList.get(i);
        	if(i<havePriceList.size()-1){
        		SalesOrderLineCommand bean = this.processComboSkuDetail(csd, comboSku, soLineLog, rQty, shopId, index);
        		usedTotal= usedTotal.add(bean.getSubOrderTaxFee());
    			solCmdList.add(bean);
        	}else{
        		//处理最后一行，用减法
        		SalesOrderLineCommand bean = this.processComboSkuDetail(csd, comboSku, soLineLog, rQty, shopId, index);
        		bean.setSubOrderTaxFee(soLineLog.getSubOrderTaxFee().subtract(usedTotal));
        		solCmdList.add(bean);
        	}
        }
        
		//处理无金额 
		for (ComboSkuDetail cb : noPriceList) {
			SalesOrderLineCommand bean = this.processComboSkuDetail(cb, comboSku, soLineLog, rQty, shopId, index);
			solCmdList.add(bean);
		}
		return solCmdList;
	}
	
	private SalesOrderLineCommand processComboSkuDetail(ComboSkuDetail cb, ComboSku comboSku, MqSoLineLog soLineLog, Integer rQty, Long shopId, Integer index){
		Sku sku = cb.getInvSku();
		SalesOrderLineCommand solCmd = new SalesOrderLineCommand();
		solCmd.setIsComboSku(Boolean.TRUE);
		solCmd.setShopId(shopId);
		solCmd.setComboSkuExtCodeOrgin(comboSku.getCode());
		solCmd.setQuantity((new BigDecimal(cb.getQuantity()).multiply(new BigDecimal(rQty))).intValue());
		solCmd.setUnitPrice(cb.getUnitPrice());
		//按比例均摊 行 天猫国际税金fanht
		//组合商品金额占比 * 天猫国际官网直供子订单关税税费
		solCmd.setSubOrderTaxFee(cb.getUnitPrice().multiply(new BigDecimal(cb.getQuantity()).multiply(soLineLog.getSubOrderTaxFee()).divide(comboSku.getTotalPrice(), BigDecimal.ROUND_HALF_UP, 2)));
		
		solCmd.setIsNeededPacking(Boolean.FALSE);
		if(index != null){
			List<SalesOrderLinePackage> packagelist = ckGiftBoxPackage(shopId, index, comboSku.getCode());
			solCmd.setIsNeededPacking(Boolean.TRUE);
			solCmd.setSalesOrderLinePackage(packagelist);
		}
		SalesOrderLineCommand soLineCmd = constructCommonSoLine(solCmd, sku, soLineLog, rQty);
		
		return soLineCmd;
	}

	/**
	 * 构建订单行明细
	 * 通用部分
	 * @param solCmd
	 * @param sku
	 * @param soLineLog
	 * @param rQty
	 * @return
	 */
	private SalesOrderLineCommand constructCommonSoLine(SalesOrderLineCommand solCmd, Sku sku, MqSoLineLog soLineLog, Integer rQty){
			
			solCmd.setSku(sku.getId());
			BigDecimal listPrice = soLineLog.getListPrice();
			if (listPrice == null) {
				Product byId = productDao.findById(sku.getProduct());
				listPrice = byId.getListPrice();
			}
			solCmd.setListPrice(listPrice != null ? listPrice : BigDecimal.ZERO);
			solCmd.setPlatformSkuName(soLineLog.getPlatformSkuName());
			solCmd.setOrderLineType(OrderLineType.MAIN.getValue());
			solCmd.setPlatformOrderLineCode(soLineLog.getPlatformLineId());
			solCmd.setPlatformWhCode(soLineLog.getPlatformWhCode()); // 设定平台分仓编码
			
			BigDecimal totalAmount = solCmd.getUnitPrice().multiply(new BigDecimal(solCmd.getQuantity()));
			solCmd.setTotalAmount(totalAmount);
	
			// 初始化，平摊的时候覆盖
			solCmd.setLineDiscount(BigDecimal.ZERO);
			solCmd.setTotalDiscount(BigDecimal.ZERO);
			solCmd.setTotalAmountAfterDiscount(totalAmount);
			solCmd.setUnitPriceAfterDiscount(solCmd.getUnitPrice());
			solCmd.setVirtualAmount(BigDecimal.ZERO);
			solCmd.setInvoiceTotalAmount(totalAmount);
			solCmd.setInvoiceUnitPrice(solCmd.getUnitPrice());
			solCmd.setTotalPointUsed(BigDecimal.ZERO);
			solCmd.setSourceRemark(soLineLog.getPlatformSource());
			// 商家的预计发货时间
			solCmd.setEstConTime(soLineLog.getEstConTime());
			// 图片路径连接
			solCmd.setPicPath(soLineLog.getPicPath());
			// 淘宝捆绑oid
			solCmd.setAssemblyRela(soLineLog.getAssemblyRela());
			//平台total_fee:应付金额(商品价格*商品数量+手工调整金额 -子订单级订单优惠金额)*/
			solCmd.setTotalFee(soLineLog.getTotalFee());
			//新增全渠道门店CODE标示用作后续拆单
			solCmd.setTargetCode(soLineLog.getTargetCode());
			solCmd.setTargetType(soLineLog.getTargetType());
			//星盘全渠道派单号
			solCmd.setAllocationCode(soLineLog.getAllocationCode());
			
			//分期 add by chenping 20170629 begin
			solCmd.setFqgNum(soLineLog.getFqgNum());
			solCmd.setIsFqgSFee(soLineLog.getIsFqgSFee());
			//分期 add by chenping 20170629 end

//			FIXME 库存扣减
//			soInvFlowDao.updateSkuInv(sku.getCode(), solCmd.getQuantity());
		return solCmd;
	}
	
	/**
	 * 根据头信息和行信息获取订单类型
	 * @param soLog
	 * @param solCmdList
	 * @param shop 
	 * @param soCmd 
	 * @return SoSpecialType
	 */ 
	private SoSpecialType getSoSpecialType(MqSoLog soLog, List<SalesOrderLineCommand> solCmdList, SkuAppointment skuAppointment, CompanyShop shop, SalesOrderCommand soCmd){
		SoSpecialType soSpecialType = null;
		Integer productType = 0;
		
		//是否报价学生价商品
		Boolean isExistsStudentPriceSku = Boolean.FALSE;
		//是否包含虚拟实物商品
		Boolean isEticketActualSku = Boolean.FALSE;
		
		if (soLog.getSpecialType() != null) {
			soSpecialType = SoSpecialType.valueOf(soLog.getSpecialType());
		}
		//如果预约订单有数据，则为预约订单类型
		if(skuAppointment!=null){
			soSpecialType = SoSpecialType.APPOINTMENT_ORDER;
		}
		
		for(SalesOrderLineCommand solCmd:solCmdList){
			Sku sku = skuDao.findById(solCmd.getSku());
			// 判断是否存在学生价商品
			if (sku.getSpecialType() != null && sku.getSpecialType().equals(SkuSpecialType.STUDENT_PRICE_SKU)) {
				isExistsStudentPriceSku = Boolean.TRUE;
			}
			//判断虚拟实物商品
			Product byId = productDao.findById(sku.getProduct());
			if(Product.SP_TYPE_ETICKET_ACTUAL.equals(byId.getSpType())){
				isEticketActualSku = Boolean.TRUE;
			}
			productType = checkProductType(sku, productType);
		}
		
		
		// apple学生价订单 根据特殊商品而来fanht 
		if (isExistsStudentPriceSku) {
			soSpecialType = SoSpecialType.STUDENT_PRICE_ORDER;
		}
		//电子券订单
		if(PLATFORM_TRADE_TYPE_ETICKET.equals(soLog.getPlatformOrderType())){
			soSpecialType = SoSpecialType.ETICKET_ORDER;
		}
		//电子券实物订单
		if(PLATFORM_TRADE_TYPE_ETICKET.equals(soLog.getPlatformOrderType())&&isEticketActualSku){
			soSpecialType = SoSpecialType.ETICKET_ACTUAL_ORDER;
		}
		//平台自动发货订单
		if(Constants.PLATFORM_TRADE_TYPE_AUTO_DELIVERY.equals(soLog.getPlatformOrderType())){
			if (productType == ProductType.VIRTUAL_GOODS.getValue()) {
				soSpecialType = SoSpecialType.AUTO_DELIVERY_INV_ORDER;
			} else if (productType == ProductType.VIRTUAL_NO_GOODS.getValue()) {
				soSpecialType = SoSpecialType.AUTO_DELIVERY_ORDER;
			}
		}
		
		//xin.feng 预售、预包装订单
		if (shop.getIsPresale() && STEP_TRADE_STATUS_FRONT_PAID.equals(soLog.getStepTradeStatus())) {
			if (shop.getIsPrepackage() && shop.getIsOrderRoute() && shop.getIsOpenDirectWms() == SysWmsStatus.BZWMS) {
				soSpecialType =  SoSpecialType.PRE_PACKAGE_ORDER;
				//预包装的订单才需要查找库位
				OrderLocationMapping location = orderLocationMappingDao.findByBusinessType(shop.getId(), null, SoSpecialType.PRE_SALES_ORDER.getValue());
				if (null != location){
					soCmd.setWhLocationCode(location.getLocationCode());
				}
			} else {
				soSpecialType =  SoSpecialType.PRE_SALES_ORDER;
			}
			soCmd.setFinanceStatus(SoFinanceStatus.PARTPAYMENT.getValue());
		}
		return soSpecialType;
	}
	
	
	private List<SalesOrderLineCommand> constructSoLine(MqSoLog soLog, CompanyShop shop){
		String errorMsg = null;
		Collection<MqSoLineLog> soLineLogs = mqSoLineLogDao.getMqMqSoLineLogsBySoLogId(soLog.getId(),shop.getId());
		if (soLineLogs == null || soLineLogs.isEmpty()) {
			errorMsg = "创建订单出错：订单行不存在";
			mqSoLogDao.updateStatusAndErrorMsgById(soLog.getId(), soLog.getShopId(), null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
			loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", soLog.getShopId(), soLog.getCode(), errorMsg);
			
			return null;
		}

		List<SalesOrderLineCommand> solCmdList = new ArrayList<SalesOrderLineCommand>();

		for (MqSoLineLog soLineLog : soLineLogs) {
			String extentionCode = soLineLog.getExtentionCode();
			if (StringUtils.isBlank(extentionCode)) {
				errorMsg = "创建订单出错,extentionCode[ " + extentionCode + " ]对应的商品不存在";
				mqSoLogDao.updateStatusAndErrorMsgById(soLog.getId(), soLog.getShopId(), null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
				loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", soLog.getShopId(), soLog.getCode(), errorMsg);
				return null;
			}

			Integer rQty = soLineLog.getQty() == null ? 0: soLineLog.getQty();
			if(rQty == 0){
				errorMsg = "创建订单出错,订单行soLineLog_id:"+soLineLog.getId()+"数量为0";
				mqSoLogDao.updateStatusAndErrorMsgById(soLog.getId(), soLog.getShopId(), null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
				loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", soLog.getShopId(), soLog.getCode(), errorMsg);
				return null;
			}
			
			//组合商品订单行解析
			ComboSku comboSku = comBoSkuDao.findByCodeAndShopId(extentionCode, shop.getId());
			List<SalesOrderLineCommand> soLineCommands = new ArrayList<SalesOrderLineCommand>();
			if(comboSku != null){
				//构建组合商品订单行信息
				Boolean isPackage = comboSku.isPackage();
				if(isPackage != null && isPackage){			
					// 组合商品-----需要包装的组合商品
					for(int i=0; i < rQty; i++){
						//行级包装信息
						List<SalesOrderLineCommand> lineCommands = constructCmboSkuSoLine(comboSku ,soLineLog, 1, shop.getId(), i);
						soLineCommands.addAll(lineCommands);
					}
				}else{										
					// 组合商品-----不需要包装的组合商品
					soLineCommands = constructCmboSkuSoLine(comboSku ,soLineLog, rQty, shop.getId(), null);
				}
			} else {										
				// 非组合商品
				List<MqSoPackingInfoLog> linePackingInfoList = mqSoPackingInfoLogDao.findBySoLineLogIdAndLevel(soLineLog.getId(), MqSoPackingInfoLevel.BY_PRODUCT.getValue(), shop.getId());
				//查询商品引入缓存机制fanht
				//Sku sku = skuDao.findSkuByExtCodeBrtCode(extentionCode, shop.getBusinessRegionType(),shop.getWhCustomerCode());
				Sku sku = skuManager.findSkuByExtCodeBrtCode(extentionCode, shop.getBusinessRegionType(),shop.getWhCustomerCode());
				if (sku == null) {
					errorMsg = "创建订单出错,extentionCode[ " + extentionCode + " ]对应的商品不存在";
					mqSoLogDao.updateStatusAndErrorMsgById(soLog.getId(), soLog.getShopId(), null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), errorMsg, null);
					loggerSoCreate.error("create so with error: shopId is {} order code {} errorMsg {}", soLog.getShopId(), soLog.getCode(), errorMsg);
					return null;
				}
				// 构建非组合商品订单行信息
				SalesOrderLineCommand solCmd = new SalesOrderLineCommand();
				solCmd.setIsComboSku(Boolean.FALSE);
				solCmd.setShopId(shop.getId());
				solCmd.setQuantity(soLineLog.getQty());
				solCmd.setUnitPrice(soLineLog.getUnitPrice());
				solCmd.setSubOrderTaxFee(soLineLog.getSubOrderTaxFee());
				// 行级包装信息
				List<SalesOrderLinePackage> piList = constructPackingInfo(linePackingInfoList);
				if (piList.size() > 0) {
					solCmd.setIsNeededPacking(Boolean.TRUE);
					solCmd.setSalesOrderLinePackage(piList);
				}
				SalesOrderLineCommand soLineCmd = constructCommonSoLine(solCmd, sku, soLineLog, rQty);
				
				soLineCommands.add(soLineCmd);
			}
			solCmdList.addAll(soLineCommands);
		}
		return solCmdList;
	}
	
	/**
	 * ck礼品盒包装信息
	 * @param shopId
	 * @param i
	 * @return
	 */
	private List<SalesOrderLinePackage> ckGiftBoxPackage(Long shopId, int i, String comb){
		List<SalesOrderLinePackage> piList = new ArrayList<SalesOrderLinePackage>();
		SalesOrderLinePackage packingInfo = new SalesOrderLinePackage();
			packingInfo.setPackageType(PackageType.CK_GIFT_BOX.getValue());
			packingInfo.setRemark(comb+ "-"+ (i+1));
			packingInfo.setShopId(shopId);
			piList.add(packingInfo);
		return piList;
	}
}