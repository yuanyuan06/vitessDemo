package io.vitess.service.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.PromotionDetail;
import com.taobao.api.domain.ServiceOrder;
import com.taobao.api.domain.Trade;
import io.vitess.constants.AppleConstants;
import io.vitess.constants.Constants;
import io.vitess.constants.PlatformConstants;
import io.vitess.dao.mq.*;
import io.vitess.enums.*;
import io.vitess.exception.SoGetTradeException;
import io.vitess.model.mq.*;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.common.CompanyShopManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("createTradeScheduler")
@Transactional
public class TbCreateSoManagerImpl extends BaseManagerImpl implements TbCreateSoManager {
		private static final long serialVersionUID = -8557389186447242809L;
		private static final Logger loggerSoGet = LoggerFactory.getLogger(TbCreateSoManagerImpl.class);
		
		@Autowired
		private MqPlatformMemberLogDao mqPlatformMemberLogDao;
		@Autowired
		private MqSoLogDao mqSoLogDao;
		@Autowired
		private MqSoLineLogDao mqSoLineLogDao;
		@Autowired
		private MqDeliveryInfoLogDao mqDeliveryInfoLogDao;
		@Autowired
		private MqSoPromotionLogDao mqSoPromotionLogDao;
		@Autowired
		private MqSoPackingInfoLogDao mqSoPackingInfoLogDao;
		@Autowired
		private MqSoServiceLineLogDao mqSoServiceLineLogDao;
		@Autowired
		private CompanyShopManager shopManager;
		private static final String E_INVOICE_KIND = "1";
		private final long BBR_SHOP_ID = 4402L;						// bbr 店铺id,围巾定制
		
		// 物流宝发货店铺fanht(gnc健安喜官方海外旗舰店)
//		private static final Long[] WlB_SHOP = {6016L, 6111L, 6946L, 9241L, 9240L};
		
		@Override
		public MqSoLog convertTradeToMqSo(com.taobao.api.domain.Trade tbTrade, Long shopId) throws SoGetTradeException {
			CompanyShop shopInfo = shopManager.findShopInfoByShopId(shopId);
			MqSoLog soLog = new MqSoLog();
				soLog.setShopId(shopId);
				soLog.setRemark(tbTrade.getBuyerMessage());	 // 买家备注
			// 一, 数据封装
				// 1. 行 & 行包装
				List<MqSoLineLog> slLogs = new ArrayList<MqSoLineLog>();
				constructMqLine(tbTrade, soLog, slLogs, shopInfo);
				// 2. 促销活动 依赖行, 需后置
				// 3.delivery
				MqDeliveryInfoLog deliveryInfo = new MqDeliveryInfoLog();
				constructDelivery(tbTrade, soLog, deliveryInfo);
				// 4. 会员
				MqPlatformMemberLog memLog = new MqPlatformMemberLog();
				constructMqMember(tbTrade, soLog, memLog);
				// 5. 服务
				List<MqSoServiceLineLog> serviceOrderLineList = new ArrayList<MqSoServiceLineLog>();
				constructMqService(tbTrade, soLog, serviceOrderLineList);
				// 6. 头
				constructMqSo(tbTrade, soLog);

			// 二, 数据存储
				// save mq
                mqSoLogDao.insert(soLog);
				// save mqline
				for (MqSoLineLog slLog : slLogs) {
					slLog.setSoLog(soLog.getId());
                    mqSoLineLogDao.insert(slLog);
					MqSoPackingInfoLog mqSoPackingInfoLog = slLog.getMqSoPackingInfoLog();
					if(mqSoPackingInfoLog != null){
							mqSoPackingInfoLog.setMqSoLineLog(slLog.getId());
							mqSoPackingInfoLog.setMqSoLog(soLog.getId());
                        mqSoPackingInfoLogDao.insert(mqSoPackingInfoLog);
					}
				}
				// save promotion
				convertTradeToMqSoPromotion(slLogs, soLog, tbTrade);
				// save delivery
				deliveryInfo.setSoLog(soLog.getId());
				mqDeliveryInfoLogDao.insert(deliveryInfo);
				// save member
				memLog.setId(soLog.getId());
				mqPlatformMemberLogDao.insert(memLog);
				// save service
				for(MqSoServiceLineLog service: serviceOrderLineList){
					service.setSoLog(soLog.getId());
					mqSoServiceLineLogDao.insert(service);
				}
			return soLog;
		}

    private void constructDelivery(com.taobao.api.domain.Trade tbTrade, MqSoLog soLog, MqDeliveryInfoLog  dfLog){
		dfLog.setReceiver(tbTrade.getReceiverName());
		dfLog.setContactPerson(tbTrade.getReceiverName());
		dfLog.setReceiverPhone(tbTrade.getReceiverPhone() == null ? "" : tbTrade.getReceiverPhone());
		dfLog.setReceiverMobile(tbTrade.getReceiverMobile() == null ? "" : tbTrade.getReceiverMobile());
		// 平台电子券订单
		if("eticket".equals(tbTrade.getType())){
		    dfLog.setReceiverMobile(tbTrade.getReceiverAddress());
		}else{
		    dfLog.setReceiverMobile(tbTrade.getReceiverMobile() == null ? "" : tbTrade.getReceiverMobile());
		}
		dfLog.setCountry(PlatformConstants.COUNTRY_CN);
		dfLog.setProvince(tbTrade.getReceiverState());
		dfLog.setCity(tbTrade.getReceiverCity());
		//区为null时，设置成全境fanht 这个逻辑不需要了回退到之前fanht
		dfLog.setDistrict(tbTrade.getReceiverDistrict());
		dfLog.setAddress(tbTrade.getReceiverAddress());
		dfLog.setZipCode(tbTrade.getReceiverZip());

		/**
		 * 物流方式:shipping_type
		 * free(卖家包邮), post(平邮), express(快递), ems(EMS), virtual(虚拟发货), 25(次日必达), 26(预约配送).
		 */
		dfLog.setLpCode(tbTrade.getShippingType());
		dfLog.setShopId(soLog.getShopId());
		dfLog.setSoLog(soLog.getId());
		//针对自动发货订单的定制
		specialDeliveryInfo(soLog, dfLog);
	}

	private void constructMqSo(com.taobao.api.domain.Trade tbTrade, MqSoLog soLog){
		soLog.setCode(tbTrade.getTid().toString());
		soLog.setShopId(soLog.getShopId());
		soLog.setStatus(MqSoLogStatus.MQ_SO_STATUS_WAITING.getValue());
		soLog.setCreateTime(Calendar.getInstance().getTime());
		soLog.setOuterCreateTime(tbTrade.getCreated());
		soLog.setPaymentTime(tbTrade.getPayTime());
		soLog.setPayAccount(tbTrade.getBuyerAlipayNo());
		soLog.setPayNo(tbTrade.getAlipayNo());
		// //淘宝暂时无此字段信息
		soLog.setInvoiceTitle(tbTrade.getInvoiceName());
		soLog.setInvoiceKind(E_INVOICE_KIND.equals(tbTrade.getInvoiceKind()));
		soLog.setAcutalTransFee(new BigDecimal(tbTrade.getPostFee()));
		//zhiyong.shi 交易头新增全渠道标示
		if(StringUtils.hasText(tbTrade.getOmnichannelParam())){
			soLog.setOmnichannelParam(tbTrade.getOmnichannelParam().substring(0, 1000));
		}
		soLog.setTotalOuterPoint(tbTrade.getPointFee() != null ? new BigDecimal(tbTrade.getPointFee()) : BigDecimal.ZERO);
		BigDecimal alipayPoint = tbTrade.getAlipayPoint() != null ? new BigDecimal(tbTrade.getAlipayPoint()) : BigDecimal.ZERO; //支付宝积分支付金额,单位分，比如返回1，则为1分钱
		BigDecimal pccAf = tbTrade.getPccAf() != null ? new BigDecimal(tbTrade.getPccAf()) : BigDecimal.ZERO; //天猫点券卡实付款金额,单位分
		//创单的时候会转换成元 fanht
		soLog.setExtVc1(alipayPoint);
		soLog.setExtVc2(pccAf);
		soLog.setSellerMemo(tbTrade.getSellerMemo());
		soLog.setSource(tbTrade.getTradeFrom()); // 订单平台来源
		soLog.setIsLgtype(tbTrade.getIsLgtype());
		soLog.setPlatformOrderType(tbTrade.getType());
		soLog.setPlatformType(PlatformType.TAOBAO_PLATFORM.getValue());
		soLog.setPlatformOrderStatus(tbTrade.getStatus());
		soLog.setAlipayId(tbTrade.getAlipayId());
		soLog.setCreditCardFee(tbTrade.getCreditCardFee());
		soLog.setOrderTaxFee(new BigDecimal(tbTrade.getOrderTaxFee()== null ? "0" : tbTrade.getOrderTaxFee()));
		soLog.setCrossBondedDeclare(tbTrade.getCrossBondedDeclare() == null ? false: tbTrade.getCrossBondedDeclare());

		if(StringUtils.hasText(tbTrade.getTradeAttr())){
            JSONObject tradeAttr = JSON.parseObject(tbTrade.getTradeAttr());
            soLog.setBuyerTaxNO(StringUtils.hasText(tradeAttr.getString("buyerTaxNO")) ?  tradeAttr.getString("buyerTaxNO"): null);
        }

		//预售
		soLog.setStepTradeStatus(tbTrade.getStepTradeStatus());
		soLog.setStepPaidFee(null == tbTrade.getStepPaidFee() ? BigDecimal.ZERO : new BigDecimal(tbTrade.getStepPaidFee()));
	}

	private void constructMqService(com.taobao.api.domain.Trade tbTrade, MqSoLog soLog, List<MqSoServiceLineLog> serviceOrderLineList){
		BigDecimal totalActual = new BigDecimal(tbTrade.getPayment()).subtract(new BigDecimal(tbTrade.getPostFee()));
		BigDecimal serviceItemFee = BigDecimal.ZERO;
		//商城虚拟服务
		List<ServiceOrder> tbServiceOrders = tbTrade.getServiceOrders();
		if (CollectionUtils.isNotEmpty(tbServiceOrders)){
			for (com.taobao.api.domain.ServiceOrder serviceOrder : tbServiceOrders) {
				MqSoServiceLineLog serviceOrderLine = new MqSoServiceLineLog();
					serviceOrderLine.setSoLog(soLog.getId());
					serviceOrderLine.setPlatformLineId("" + serviceOrder.getOid());
					serviceOrderLine.setServiceId("" + serviceOrder.getServiceId());
					serviceOrderLine.setQty(serviceOrder.getNum() == null ? 0 : serviceOrder.getNum().intValue());
					serviceOrderLine.setUnitPrice(new BigDecimal(serviceOrder.getPrice()));
					serviceOrderLine.setTotalActual(new BigDecimal(serviceOrder.getTotalFee()));
					serviceOrderLine.setPayment(new BigDecimal(serviceOrder.getPayment()));
					serviceOrderLine.setTitle(serviceOrder.getTitle());
					serviceOrderLine.setTmserSpuCode(serviceOrder.getTmserSpuCode());
					serviceOrderLine.setShopId(soLog.getShopId());
				serviceOrderLineList.add(serviceOrderLine);

				BigDecimal totalServiceFee = new BigDecimal(serviceOrder.getTotalFee());
				totalActual = totalActual.subtract(totalServiceFee);
				serviceItemFee = serviceItemFee.add(totalServiceFee);
			}
		}
		soLog.setServiceItemFee(serviceItemFee);
		soLog.setTotalActual(totalActual); // 实际货款
	}

	private void constructMqMember(com.taobao.api.domain.Trade tbTrade, MqSoLog soLog, MqPlatformMemberLog memLog){
			//完善会员信息fanht
			memLog.setCreateTime(Calendar.getInstance().getTime());
			memLog.setLoginName(tbTrade.getBuyerNick());
			memLog.setRealName(tbTrade.getReceiverName());
			memLog.setCountry("中国");
			memLog.setProvince(tbTrade.getReceiverState());
			memLog.setCity(tbTrade.getReceiverCity());
			memLog.setDistrict(tbTrade.getReceiverDistrict());
			memLog.setZipCode(tbTrade.getReceiverZip());
			memLog.setAddress(tbTrade.getReceiverAddress());
			memLog.setTelephone(tbTrade.getReceiverPhone() == null ? "" : tbTrade.getReceiverPhone());
			memLog.setMobile(tbTrade.getReceiverMobile() == null ? "" : tbTrade.getReceiverMobile());
			memLog.setEmail(tbTrade.getBuyerEmail());
			memLog.setShopId(soLog.getShopId());
			// FIXME 主键是否保留
//			memLog.setSoLog(soLog.getId());
			memLog.setId(soLog.getId());
    }

    private void constructMqLine(com.taobao.api.domain.Trade tbTrade, MqSoLog soLog, List<MqSoLineLog> slLogs, CompanyShop shopInfo){
		List<Order> tbLines = tbTrade.getOrders(); // 平台订单明细行消息信息
		
		/**
		 *平台发货类型标识								0,菜鸟; 1,3w; 2,保税; 3,集货
		 * boolean[] platformTypeFlag = new boolean[]{false,true,true,true};
		 */
		boolean[] platformTypeFlag = new boolean[]{false, true, true, true};
		
		boolean isBundle = false;
		
		// 是否捆绑销售的订单
		if("1".equals(tbTrade.getAssembly())){
		    isBundle = true;
		}
		
		soLog.setIsBundle(isBundle);
		StringBuffer cusTex = new StringBuffer();
		Boolean crossBondedDeclare = tbTrade.getCrossBondedDeclare();
		for (com.taobao.api.domain.Order tbLine: tbLines) {
			String orderStatus = tbLine.getStatus(); // 订单行状态
			if ("TRADE_CLOSED_BY_TAOBAO".equals(orderStatus)) { // TRADE_CLOSED_BY_TAOBAO不要对此状态商品行进行发货
					continue;
			}
			// 2.1 construct soLineLog
			MqSoLineLog soLineLog = new MqSoLineLog();
			
			if(isBundle && AppleConstants.APPLE_SHOP_ID.equals(soLog.getShopId())){
				if(!StringUtils.hasText(tbLine.getAssemblyRela())){
					continue;
				}
				soLineLog.setAssemblyRela(tbLine.getAssemblyRela());
			}
			
			soLineLog.setExtentionCode(tbLine.getOuterSkuId() == null ? tbLine.getOuterIid(): tbLine.getOuterSkuId());
			soLineLog.setQty(tbLine.getNum().intValue());
			
			// 整单分摊到行上的折扣
			soLineLog.setPartMjzDiscount(StringUtils.hasText(tbLine.getPartMjzDiscount())? new BigDecimal(tbLine.getPartMjzDiscount()): BigDecimal.ZERO);
			// 平台行应付金额: (商品价格*商品数量 +手工调整金额 -子订单级订单优惠金额)
            soLineLog.setTotalFee(StringUtils.hasText(tbLine.getTotalFee())? new BigDecimal(tbLine.getTotalFee()): BigDecimal.ZERO);
            soLineLog.setSubOrderTaxFee(new BigDecimal(tbLine.getSubOrderTaxFee()== null ? "0" : tbLine.getSubOrderTaxFee()));
            // 原逻辑
//          soLineLog.setTotalActual(new BigDecimal(tbLine.getTotalFee()));
            // total_actual的重新赋值逻辑：total_fee - part_mjz_discount + sub_order_tax_fee
            soLineLog.setTotalActual(soLineLog.getTotalFee().subtract(soLineLog.getPartMjzDiscount()).add(soLineLog.getSubOrderTaxFee())); 
			
			// 单价, if 非捆绑,自己计算(原逻辑) else 取 AssemblyPrice 
            // 原逻辑
//			soLineLog.setUnitPrice(new BigDecimal(tbLine.getTotalFee()).divide(new BigDecimal(tbLine.getNum()), BigDecimal.ROUND_HALF_UP, 2));
            // 调整 total_fee - part_mjz_discount + sub_order_tax_fee
            soLineLog.setUnitPrice(soLineLog.getTotalActual().divide(new BigDecimal(soLineLog.getQty()), BigDecimal.ROUND_HALF_UP, 2));
            
			soLineLog.setListPrice(new BigDecimal(tbLine.getPrice()== null ? "0" : tbLine.getPrice()));
			if(isBundle && AppleConstants.APPLE_SHOP_ID.equals(soLog.getShopId())){
				soLineLog.setUnitPrice(new BigDecimal(tbLine.getAssemblyPrice()));
				soLineLog.setListPrice(new BigDecimal(tbLine.getAssemblyPrice()));
			}
			
			soLineLog.setPlatformSkuName(tbLine.getTitle() == null ? "" : tbLine.getTitle().trim());
			soLineLog.setPlatformLineId(tbLine.getOid() == null ? null : tbLine.getOid().toString());
			soLineLog.setSoLog(soLog.getId());
			soLineLog.setPlatformSource(tbLine.getOrderFrom()); // 订单行平台来源
			soLineLog.setPlatformWhCode(tbLine.getStoreCode()); // 增加[平台分仓编码]
			// 商家预计发货时间
			soLineLog.setEstConTime(tbLine.getEstimateConTime());
			//新增图片路径
			soLineLog.setPicPath(tbLine.getPicPath());
			soLineLog.setShopId(soLog.getShopId());
			
			//添加分期信息 add by chenping 20170629 begin
			soLineLog.setFqgNum(tbLine.getFqgNum());
			soLineLog.setIsFqgSFee(tbLine.getIsFqgSFee());
			//添加分期信息 add by chenping 20170629 end
			
			//zhiyong.shi 全渠道新增参数
			//新增是否是全渠道订单标示orderType:STORE_DELIVER,allocationCode:68002,subOrderCode:194220750565000,targetType:WAREHOUSE,targetCode:toptest_015_warehouse01,orderState:X_SHOP_CANCEL; 
			//orderType:STORE_DELIVER,allocationCode:68003,subOrderCode:194220750565001,targetType:STORE,targetCode:toptest_015_warehouse02,orderState:X_SHOP_CANCEL
			if(tbTrade.getOmnichannelParam()!=null){
				String param = tbTrade.getOmnichannelParam();
				String[] params = param.split(";");
				for(String signParam : params){
					 Pattern p = Pattern.compile(tbLine.getOid().toString());
					 Matcher m = p.matcher(signParam);
					 boolean result= m.find();
					 if(result){
						String map[] = signParam.split(",");
						for(String ff : map){
							String tt[] = ff.split(":");
							if(tt[0].equals("targetCode")){
								soLineLog.setTargetCode(tt[1]);
							}else if(tt[0].equals("targetType")){
								soLineLog.setTargetType(tt[1]);
							}else if(tt[0].equals("allocationCode")){
								soLineLog.setAllocationCode(tt[1]);
							}
						}
					 }
				}
			}
			//全渠道相关新增代码 结束
			
			try{
				if(BBR_SHOP_ID == soLog.getShopId()){ // 博柏利官方旗舰店 定制
					JSONObject customization = JSON.parseObject(tbLine.getCustomization());
					JSONArray cusTextArr = customization.getJSONArray("text");
					cusTex.append(cusTextArr.getJSONObject(0).getString("content"));
				}
			}catch(Exception e){
				String errorMsg = String.format("解析定制化信息异常: 订单code[ %s ]", tbTrade.getTid());
				log.error(errorMsg, e);
			}
			slLogs.add(soLineLog);
			getSpecialType(tbLine, shopInfo, crossBondedDeclare, platformTypeFlag);
			// 行包装
			packageInfo(tbLine, soLog, soLineLog);
		}
		
		if(StringUtils.hasText(cusTex)){
			soLog.setCustomizationMemo(cusTex.toString());
		}
		
//		if(platformTypeFlag[3]){																			                                                    // cj 集货订单
	    if(platformTypeFlag[3] || Integer.valueOf(4).equals(shopInfo.getIsCaiNiao())){																			// cj 集货订单
			soLog.setIsPlatformDelivery(Boolean.TRUE);
			soLog.setSpecialType(SoSpecialType.WLB_COLLECT_ORDER.getValue());
		}else if(platformTypeFlag[0] || platformTypeFlag[1] || platformTypeFlag[2] || Integer.valueOf(2).equals(shopInfo.getIsCaiNiao())){						// 非集货, 全店物流宝
			soLog.setIsPlatformDelivery(Boolean.TRUE);
			soLog.setSpecialType(SoSpecialType.WLB_ORDER.getValue());// 物流宝订单
		}
    }
    
    /**
	 *平台发货类型标识								0,菜鸟; 1,3w; 2,保税; 3,集货
	 * boolean[] platformTypeFlag = new boolean[]{false,false,false,false};
	 */
    private void getSpecialType(com.taobao.api.domain.Order tbLine, CompanyShop shopInfo, Boolean crossBondedDeclare, boolean[] platformTypeFlag){
    	
    	// 菜鸟订单(一行是整单是), 只能开不能关
		if(Integer.valueOf(1).equals(shopInfo.getIsCaiNiao()) &&  BooleanUtils.isTrue(tbLine.getIsShShip())){					 								// 菜鸟店铺 && 菜鸟订单
			platformTypeFlag[0] = true;
		}
    	
		// 保税订单& 集货订单, 一行非,整单非, 只能关,不能开
		if(Integer.valueOf(3).equals(shopInfo.getIsCaiNiao()) &&  BooleanUtils.isTrue(crossBondedDeclare)){														// cj店铺
			if(tbLine.getStoreCode() == null){																													// 集货订单
				platformTypeFlag[2] = false;																													
			}else{																																				// 保税
				platformTypeFlag[3] = false;
			}
		}else{
			platformTypeFlag[3] = false;
			platformTypeFlag[2] = false;
		}
		
		// 3w 订单, 需平台订单行全部符合条件(一行非整单非), 只能关,不能开
		if(BooleanUtils.isNotTrue(tbLine.getIsWww())){																												// 一行非3w, 整单非3w
			platformTypeFlag[1] = false;
		}
    }
    
    private void packageInfo(com.taobao.api.domain.Order tbLine, MqSoLog soLog, MqSoLineLog soLineLog){
    	// 包装信息
		String duihuanCity = tbLine.getSkuPropertiesName();
		if (StringUtils.hasText(duihuanCity) && duihuanCity.contains("兑换城市")) {
			String oldMemo = soLog.getRemark() == null ? "" : soLog.getRemark();
			Integer lineQty = Integer.valueOf(tbLine.getNum().intValue());
			String duihuanRemark = duihuanCity + "*" + lineQty;
			// 记录兑换城市到买家备注中
			soLog.setRemark(duihuanRemark + " / " + oldMemo);
			MqSoPackingInfoLog packingInfo = new MqSoPackingInfoLog();
				packingInfo.setPiLevel(MqSoPackingInfoLevel.BY_PRODUCT.getValue());
				packingInfo.setType(PackageType.HAAGENDAZS_EXCHANGE.getValue());
				packingInfo.setMemo(duihuanRemark);
				packingInfo.setShopId(soLog.getShopId());
				packingInfo.setMqSoLog(soLog.getId());
				packingInfo.setMqSoLineLog(soLineLog.getId());
			// 行包装保存
			soLineLog.setMqSoPackingInfoLog(packingInfo);
//		mqSoPackingInfoLogDao.save(packingInfo);
		}
    }
    
	private void specialDeliveryInfo(MqSoLog soLog, MqDeliveryInfoLog dfLog) {
		String defaultStr = "0";
		if (Constants.PLATFORM_TRADE_TYPE_AUTO_DELIVERY.equals(soLog.getPlatformOrderType())) {
			if(isEmptyWithProvinceAndCityAndDistinct(dfLog.getProvince(), dfLog.getCity(), dfLog.getDistrict(), dfLog.getAddress())){
				dfLog.setProvince(defaultStr);
				dfLog.setCity(defaultStr);
				dfLog.setDistrict(defaultStr);
				dfLog.setAddress(defaultStr);
			}
		}
	}
	
	private boolean isEmptyWithProvinceAndCityAndDistinct(String province, String city, String distinct, String address) {
		if (!StringUtils.hasText(province)) {
			return true;
		}
		if (!StringUtils.hasText(city)) {
			return true;
		}
		if (!StringUtils.hasText(address)) {
			return true;
		}
		return false;
	}


	// 保存促销信息
	private void convertTradeToMqSoPromotion(List<MqSoLineLog> mqSoLineLogList, MqSoLog soLog, Trade tbTrade) {
		List<PromotionDetail> tbPromotionDetails = tbTrade.getPromotionDetails(); // 平台金额满减消息信息
		if (tbPromotionDetails == null || tbPromotionDetails.isEmpty()) {
			return;
		}
		for (com.taobao.api.domain.PromotionDetail proMsg : tbPromotionDetails) {
			MqSoPromotionLog pmLog = new MqSoPromotionLog();
			pmLog.setDiscountFee(new BigDecimal(proMsg.getDiscountFee()));
			pmLog.setDescription(proMsg.getPromotionDesc());
			pmLog.setGiftItemId(proMsg.getGiftItemId());
			pmLog.setGiftItemName(proMsg.getGiftItemName());
			pmLog.setShopId(soLog.getShopId());
			Integer itemNum = 0;
			if (StringUtils.hasLength(proMsg.getGiftItemNum())) {
				itemNum = Integer.parseInt(proMsg.getGiftItemNum());
			}
			pmLog.setGiftItemNum(itemNum);
			pmLog.setPlatformOrderCode(tbTrade.getTid().toString());
			pmLog.setPromotionId(proMsg.getPromotionId());
			pmLog.setPromotionName(proMsg.getPromotionName());
			Long relatedOrderId = proMsg.getId();
			boolean flag = false;
			MqSoLineLog mqSoLineLog = null;
			
			// 校验是否整单促销
			boolean compareOrderCode = relatedOrderId.toString().equals(soLog.getCode());
			if(!compareOrderCode){
				flag = true;			//不一致 一定 行级促销
				pmLog.setPlatformLineId(proMsg.getId());
			}
			for(MqSoLineLog mqLineLog: mqSoLineLogList){
				// promotion 的id == 平台行id && != 平台id
				boolean compareOrderLineCode = mqLineLog.getPlatformLineId().equals(relatedOrderId.toString());
				if(compareOrderLineCode){
					flag = true;			// order && line 一致   行级促销
					mqSoLineLog = mqLineLog;
					pmLog.setPlatformLineId(proMsg.getId());
					break;
				}
			}
			if(flag){
				// 行级促销
				pmLog.setScopeType(MqSoPromotionLog.SO_PLATFORM_PROMOTION_SCOPE_TYPE_LINE);
				pmLog.setSoLineLog(mqSoLineLog == null? null: mqSoLineLog.getId());
			}else{
				// 整单促销
				pmLog.setScopeType(MqSoPromotionLog.SO_PLATFORM_PROMOTION_SCOPE_TYPE_ORDER);
			}
			pmLog.setSoLog(soLog.getId());
            mqSoPromotionLogDao.insert(pmLog);
		}
	}
}