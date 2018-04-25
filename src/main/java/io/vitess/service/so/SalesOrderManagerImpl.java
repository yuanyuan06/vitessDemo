package io.vitess.service.so;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baozun.pacssi.entity.order.BzOrder;
import com.baozun.pacssi.entity.order.BzOrderLine;
import io.vitess.command.*;
import io.vitess.common.*;
import io.vitess.constants.AppleConstants;
import io.vitess.constants.Constants;
import io.vitess.constants.SysWmsStatus;
import io.vitess.dao.base.*;
import io.vitess.dao.so.*;
import io.vitess.enums.*;
import io.vitess.exception.WorkFlowException;
import io.vitess.model.base.*;
import io.vitess.model.so.*;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import io.vitess.service.common.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

@Service("salesOrderManager")
public class SalesOrderManagerImpl extends BaseManagerImpl implements SalesOrderManager {

    private static final long serialVersionUID = 9142178192133868546L;

    @Autowired
    private SalesOrderTaskFactory factory;

    @Autowired
    private SalesOrderDao salesOrderDao;

    @Autowired
    private SalesOrderLineDao soLineDao;
    
    @Autowired
    private SoServiceLineDao soServiceLineDao;
    
    @Autowired
    private BaseInfoManager baseInfoManager;

    @Autowired
    private CompanyShopDao companyShopDao;

    @Autowired
    private SoDeliveryInfoDao soDeliveryInfoDao;
    
    @Autowired
    private SalesOrderOnebyOneTowhDao salesOrderOnebyOneTowhDao;

    @Autowired
    private ShopWhDao shopWhDao;

    @Autowired
    private OrderMemberDao orderMemberDao;

    @Autowired
    private SalesOrderLinePackageDao salesOrderLinePackageDao;

    @Autowired
    private SoPromotionApplyLogDao soPromotionApplyLogDao;

    @Autowired
    private ComboSkuDetailDao comboSkuDetailDao;

    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private SendSoDao sendSoDao;
    
    @Autowired
    private OrderAutoTaskInfoDao orderAutoTaskInfoDao;
    
    @Autowired
    private SoWfTaskDao soWfTaskDao;

    @Autowired
    private SoManager soManager;
    
	@Autowired
	private SoAppointmentToWhTaskDao soAppointmentToWhTaskDao;
    
    // 物流宝订单物流商
    private final static String OTHER_LOGISTICS = "OTHER";
    // 物流宝集货物流服务商
    private final static String COLLECTION_LOGISTICS = "CNJH";

    @Autowired
    private DefaultTransTempleteManager defaultTransTempleteManager;
    
    @Autowired
    private ShopWhManager shopWhManager;
    @Autowired
    private ShopSkuWhRefManager shopSkuWhRefManager;
    @Autowired
    private SalesOrderSplitManager salesOrderSplitManager;
    @Autowired
    private BranchWarehouseManager branchWarehouseManager;
    @Autowired
    private ServiceInstallCompanyManager serviceInstallCompanyManager;
    @Autowired
    private PlatformPromotionDao platformPromotionDao;
    @Autowired
    private PlatformPromotionManager platformPromotionManager;
    @Autowired
    ShoOutLetInfoDao shoOutLetInfoDao;
    @Autowired
    private SkuDao skuDao;
    
    private static final int ORDER_TO_WH_PAUSE_START_TIME = 0;
    private static final int ORDER_TO_WH_PAUSE_END_TIME = 22;
    
    private static Collection<String> unApplyEMSProvinces = new ArrayList<String>();
    static {
        unApplyEMSProvinces.add("北京");
        unApplyEMSProvinces.add("天津");
        unApplyEMSProvinces.add("重庆");
        unApplyEMSProvinces.add("上海");
        unApplyEMSProvinces.add("江苏省");
        unApplyEMSProvinces.add("浙江省");
    }

    /**
     * 发票处理 ： 如果店铺设置为默认开票,那么该店铺进系统,订单上是否需要发票字段设置为true
     * 电子发票按单开，如果天猫给的发票类型是电子发票，就开，否则不开
     * */
    private void dealInvoiceInfo(SalesOrder newSo, SalesOrderCommand soCmd, CompanyShop shop) {
    	// 物流宝订单不需要开票,店铺开票字段未设置开启的不开票
    	// 店铺订单类型
    	int specialType = newSo.getSpecialType();
    	// 店铺是否设置开票
    	Boolean defaultInvoice = shop.getIsDefaultInvoice();
    	if(specialType == SoSpecialType.WLB_ORDER.getValue() && !shop.getInvoiceKind()){
    		// 物流宝订单 纸质发票 不开发票
    		newSo.setNeedInvoice(Constants.NO);						
    	}else{														
    		// 非物流宝订单		
    		if(defaultInvoice != null && defaultInvoice){			
    			// 	店铺设置是都为开票
        		newSo.setNeedInvoice(Constants.YES);
    		}else{
    			newSo.setNeedInvoice(Constants.NO);
    		}
    	}
    	
        addInvoiceTitle(newSo,shop);
     
        // 发票类型
        try {
            newSo.setInvoiceType(soCmd.getInvoiceTypeValue() == null ? InvoiceType.ORDINARY_COMMERCIAL.getValue() : soCmd.getInvoiceTypeValue());
        } catch (IllegalArgumentException e) {
            newSo.setInvoiceType(InvoiceType.ORDINARY_COMMERCIAL.getValue());
        }
        
        //放到发票处理里面fanht  如果订单上是否开具发票明细为空，则判断店是否默认开明细,如果“是”则订单上是否开具发票明细设置为true，否则设置为false
        if (null == newSo.getIsBillingInvoiceDetail()) {
            Boolean isDefaultInvoiceDetail = shop.getIsBillingInvoiceDetail() == null ? Boolean.FALSE : shop.getIsBillingInvoiceDetail();
            newSo.setIsBillingInvoiceDetail(isDefaultInvoiceDetail ? 1 : 0);
        }
        
        //发票形式fanht
        newSo.setInvoiceKind(shop.getInvoiceKind());
        //电子发票按单开
        if(shop.getInvoiceKind()){
        	if (shop.getInvoiceKind().equals(soCmd.getInvoiceKind())) {
        		newSo.setInvoiceKind(soCmd.getInvoiceKind());
			} else {
				newSo.setNeedInvoice(Constants.NO);
			}
        }
    }

    /**
     * 设置发票抬头
     * 逻辑：销售订单发票抬头信息若未取到，则依据店铺设置进行取值。即：店铺设置上，【默认发票抬头】=“个人”，则抬头取值默认为“个人”；【默认发票抬头】=“收件人姓名”，则抬头取值默认为 销售订单中的【收货人】
	 * @param newSo
	 * @param shop
	 */
	private void addInvoiceTitle(SalesOrder newSo, CompanyShop shop) {
		 // 发票头
        String invoiceTitle = newSo.getInvoiceTitle();
        if(!StringUtils.hasText(invoiceTitle)){
			if (null!=shop.getDefInvoiceTitleType()&&shop.getDefInvoiceTitleType().equals(DefInvoiceTitleType.RECEIVER)) {

				SoDeliveryInfo delivery = soDeliveryInfoDao.findById(newSo.getSoDeliveryInfo());;
				invoiceTitle = delivery.getReceiver();
			} else {
				invoiceTitle = Constants.COMMON_SO_INVOICE_TAX_TITLE;
        	}
        }
	    newSo.setInvoiceTitle(invoiceTitle);
	}

	private void dealSoLineSourceType(SalesOrder so, SalesOrderLine line) {
        if (so.getSourceType() != null) {
            line.setSourceType(so.getSourceType());
            // 赠品行变为空
            if (line.getOrderLineType() == null || OrderLineType.GIFT.equals(line.getOrderLineType())) {
                line.setSourceType(null);
            }
        }
    }

    /** 创建订单   save动作的执行 */
    @Override
    public SalesOrder generateSalesOrderByCommand(SalesOrder newSo, SalesOrderCommand soCmd, User creator, SalesOrderType orderType, Integer isNeededAssign, Map<String, String> sourceMap, SkuAppointment skuAppointment) {
    	
    	if(soCmd.getIsAssignWh() != null){
    		newSo.setIsAssignWh(soCmd.getIsAssignWh());
    	}

        CompanyShop shop = companyShopDao.findById(soCmd.getCompanyShop());;
    	specialProcessSoDeliveryInfo(soCmd);
    	
    	//电话 手机 校验 fanht
    	specialProcessAutoDeliverySo(soCmd);
    	String phone = soCmd.getReceiverPhone();
        String mobile = soCmd.getMobile();
//        if (!StringUtils.hasText(phone) && !StringUtils.hasText(mobile) ) {
//            throw new BusinessException(ErrorCode.SO_MOBILE_PHONE_NOT_NULL);
//        }
        
        List<SalesOrderLineCommand> solCmdList = soCmd.getSoLineCommandList();
        //o2o订单检查
        checkO2oPostSalesServiceInfo(shop, soCmd, solCmdList);
        // 企业税号
        newSo.setBuyerTaxNO(soCmd.getBuyerTaxNO());
        soCmd.generateSalesOrder(newSo);
        newSo.setOrderType(orderType.getValue());
        // omsOrderCode与子订单号同值
        newSo.setOmsOrderCode(newSo.getPlatformOrderCodeN());
        newSo.setOrderStatus(SalesOrderStatus.CREATED.getValue());
        newSo.setCreator(creator == null ? null: creator.getId());
        newSo.setCreateTime(Calendar.getInstance().getTime());
        //直连库存共享逻辑fanht
        if(!(shop.getIsOpenDirectWms()==null||shop.getIsOpenDirectWms()==SysWmsStatus.UNWMS)&&shop.getIsShareInventory()){
        	//toms端是否关闭库存共享（关闭意味着不在从pac获取出库outOwner）
        	if(shop.getIsOwnWms()){
        		newSo.setIsCloudStackSo(Boolean.TRUE);
        	}else{
        		newSo.setIsCloudStackSo(Boolean.FALSE);
        	}
        }else{
        	newSo.setIsCloudStackSo(Boolean.TRUE);
        }
        //zhiyong.shi 如果开启订单路由则此订单需要访问路由服务进行发货
        if(shop.getIsOrderRoute()){
        	newSo.setIsOrderRoute(Boolean.TRUE);
        	newSo.setIsCloudStackSo(Boolean.FALSE);
        }
        // 构造配货信息
        SoDeliveryInfo soDef = new SoDeliveryInfo();
        soDef.setMemberEmail(soCmd.getMemberEmail());
        soDef.setCountry("中国");
        soDef.setProvince(soCmd.getProvince());
        soDef.setDistrict(soCmd.getDistrict());
        String city = soCmd.getCity();
        if (!StringUtils.hasText(city)) {
            city = soCmd.getDistrict(); // 淘宝进来的订单县级市被置于district中同时city为空，针对该情况，将district值设置到city上
        }
        soDef.setCity(city);
        soDef.setAddress(soCmd.getAddress());
        soDef.setZipcode(soCmd.getZipcode());
        soDef.setContactPerson(soCmd.getContactPerson());
        soDef.setReceiver(soCmd.getReceiver());
        soDef.setReceiverMobile(soCmd.getMobile());
        soDef.setReceiverPhone(soCmd.getReceiverPhone());
        soDef.setRemark(soCmd.getDeliveryRemark());
        soDef.setTransServiceType(soCmd.getTransServiceType() == null ? 1 : soCmd.getTransServiceType());// 设置运送方式(快递附加服务)
        soDef.setTransTimeType(soCmd.getTransTimeType() == null ? 1 : soCmd.getTransTimeType());// 设置当快递时间限制
        soDef.setShopId(shop.getId());
        String transExpCode = soCmd.getTransExpCode();
        if (StringUtils.hasText(transExpCode)) {
            log.info("transExpCode:" + transExpCode);
            Transportator tp = baseInfoManager.findTransportatorByCode(transExpCode.toUpperCase());
            if (tp != null) {
                soDef.setTransExpCode(tp.getExpCode());
                soDef.setTransName(tp.getName());
            }
        }


        // construct soline
        List<SalesOrderLine> slList = new ArrayList<SalesOrderLine>();
        BigDecimal lineSumTotal = BigDecimal.ZERO;
        // 包装信息
        List<SalesOrderLinePackage> platformSLPackingInfos = new ArrayList<SalesOrderLinePackage>();
        //行促销信息
        List<PlatformPromotion> linePlatformPromotions = new ArrayList<PlatformPromotion>();

        // 订单行信息
        for (SalesOrderLineCommand solCmd : solCmdList) {
            Sku sku = skuDao.findById(solCmd.getSku());
            SalesOrderLine sl = solCmd.generateSoLine();
            sl.setSkuName(sku.getName());
            sl.setBarCode(sku.getBarCode());
            sl.setSkuCode(sku.getCode());
            sl.setExtentionCode(sku.getExtensionCode1());
            sl.setKeyProperties(sku.getKeyProperties());
            lineSumTotal = lineSumTotal.add(sl.getUnitPrice().multiply(new BigDecimal(sl.getQuantity())));
            //直连库存共享逻辑fanht
            if(!(shop.getIsOpenDirectWms()==null||shop.getIsOpenDirectWms()==SysWmsStatus.UNWMS)&&shop.getIsShareInventory()){
            	//toms端是否关闭库存共享（关闭意味着不在从pac获取出库outOwner）fanht
            	if(shop.getIsOwnWms()){
            		sl.setOutOwner(shop.getOwnerCode());
            	}else{
            		sl.setOutOwner(null);
            	}
            }else{
            	sl.setOutOwner(shop.getInterfaceShopCode());
            }
            sl.setSalesOrder(newSo.getId());

            this.dealSoLineSourceType(newSo, sl);

            sl.setSalesModel(solCmd.getSalesModel());
            sl.setPlatformWhCode(solCmd.getPlatformWhCode());
            sl.setInitQty(sl.getQuantity());
            setOrderLineType(sl);
            slList.add(sl);
            // 封装包装信息
            genratorPlatformSlPackingInfo(platformSLPackingInfos, solCmd, sl, newSo);
            
            //行促销信息
            platformPromotionManager.genratorPlatformLinePromotion(linePlatformPromotions, solCmd, sl, newSo);
        }
        
        // 发票处理
        this.dealInvoiceInfo(newSo, soCmd, shop);

        //选仓逻辑里面 若订单工作流分支标识字段为空则基于店铺仓储模式设置该字段值,同一家店铺基于商品或其它逻辑进行拆单时，所拆订单分别走不同工作流分支，则这类订单在进入该创单统一入口前已维护好wfBranch字段
        if (null == newSo.getWfBranch()) {
            if (CompanyShopWhModel.USE_BAOZUN_WMS.getValue() == shop.getWhModel()) { // 使用宝尊wms
                newSo.setWfBranch(CompanyShopWhModel.USE_BAOZUN_WMS.getValue());
            } else {
                newSo.setWfBranch(CompanyShopWhModel.NOT_USE_BAOZUN_WMS.getValue());
            }
        }

        //创单选仓逻辑 fanht
        // 有仓模式需维护发货仓库(若外部已指定仓库，则不再指定默认仓)
        if (newSo.getWarehouseCode() == null && CompanyShopWhModel.USE_BAOZUN_WMS.getValue() == newSo.getWfBranch()) {
        	String whCode = null;
        	if (soCmd.getSpecialType() != null && soCmd.getSpecialType().equals(SoSpecialType.WLB_ORDER)) {
        		/**
        		 * 物流宝订单在创单，选择平台默认发货仓，若找不到店铺绑定的【平台默认发货仓库】，则直接创建订单失败
        		 */
        		ShopWh platformDefaultWh = shopWhManager.findPlatformDefaultWh(shop.getId());
                if (null == platformDefaultWh) {
                	log.error("shop_id: "+ shop.getId());
                    throw new BusinessException(ErrorCode.CUSTOMIZATION_TIP, new Object[]{"订单异常: 单号"+soCmd.getPlatformOrderCodeN()+" 基础信息维护错误：该订单为物流宝订单，店铺[" + shop.getId() + "]没有绑定[平台默认发货仓库]" });
                }
                whCode = platformDefaultWh.getWhCode();
        	} else {
        		//ShopWh wsf = shopWhDao.findDefaultByShopId(shop.getId(), true);
        		ShopWh wsf = shopWhManager.getDefaultWarehouse(shop.getId());
                if (null == wsf) {
                	log.error("shop_id: "+ shop.getId());
                    throw new BusinessException(ErrorCode.BASEINFO_ERROR_WAREHOUSE_NOT_EXIST);
                }
                whCode = wsf.getWhCode();
                
                // 匹配当前店铺是否为特殊店铺
                if (shop.getId().equals(Constants.GODIVA_BS_SHOP_ID) || shop.getId().equals(Constants.TMALL_GDV_BS_SHOP_ID)) {
                    // GODIVA淘宝店及GODIVA官方商城店推荐最佳物理发货仓
                    ShopWhCommand reCommentWh = recommendSendWh(newSo.getCompanyShop(), soCmd.getProvince());
                    if (reCommentWh != null) {
                        whCode = reCommentWh.getWhCode();
                    }
                }
        	}
        	
        	//自动发货有库存订单，仓库自动指定为平台发货仓
        	whCode = autoDeliveryOrderWh(newSo, whCode);
        	
        	// 分库,根据天猫指定仓库设置发货仓库(哈根达斯)
        	String warehouseCode = designatedWh(shop, solCmdList);
        	if(warehouseCode != null){
        		whCode = warehouseCode;
        	}
        	
            newSo.setWarehouseCode(whCode);
        }

        // 订单来源解析
        if (null != sourceMap && (orderType.getValue() == SalesOrderType.PLATFORM_ONLINE_TB.getValue() || orderType.getValue() == SalesOrderType.PLATFORM_ONLINE_TB_DISTRIBUTION.getValue())) {
            splitSourceForSo(newSo, slList, sourceMap);
        }

        // 支付明细
        List<SalesOrderPaymentInfo> salesOrderPaymentInfoList = new ArrayList<SalesOrderPaymentInfo>();
        BigDecimal amountAfterDiscount = newSo.getAmountAfterDiscount();
        BigDecimal transFee = newSo.getTransFee();
        BigDecimal virtualAmount = newSo.getVirtualAmount();

        // 现金
        SalesOrderPaymentInfo pf1 = new SalesOrderPaymentInfo();
        pf1.setSalesOrder(newSo);
        pf1.setIsCompleted(Constants.YES);
        pf1.setPayAmount(amountAfterDiscount.add(transFee).subtract(virtualAmount));
        pf1.setPaymentTime(newSo.getPlatformPaymentTime());
        pf1.setPaymentType(newSo.getMainPaymentType());
        pf1.setPayNo(newSo.getPayNo());
        pf1.setPaymentAccount(newSo.getPayAccount());
        pf1.setShopId(shop.getId());
        salesOrderPaymentInfoList.add(pf1);
        
        //预售定金
        newSo.setStepPaidFee(soCmd.getStepPaidFee());

        // 积分
        if (virtualAmount != null && virtualAmount.floatValue() != 0) {
            SalesOrderPaymentInfo pf2 = new SalesOrderPaymentInfo();
            pf2.setSalesOrder(newSo);
            pf2.setIsCompleted(Constants.YES);
            pf2.setPayAmount(virtualAmount);
            pf2.setPaymentTime(newSo.getPlatformPaymentTime());
            pf2.setPaymentType(PaymentType.INTEGRAL_PAYMENT.getValue());
            pf2.setPaymentAccount(newSo.getPayAccount());
            pf2.setShopId(shop.getId());
            salesOrderPaymentInfoList.add(pf2);
        }

        newSo.setSalesOrderPaymentInfoList(salesOrderPaymentInfoList);
        //自动过仓初始化 fanht 
        newSo.setIsAutoWh(Boolean.FALSE);
        //如果是SHOPDOG 取 订单本身的 配置 
        if(soCmd.getIsDirectWmsOrder() == SysWmsStatus.SHOPDOG ){
        	newSo.setIsDirectWmsOrder(soCmd.getIsDirectWmsOrder());
        }else{
            newSo.setIsDirectWmsOrder(shop.getIsOpenDirectWms());//is direct join wms    hailiang.jiang
        }
        //挂起判断前置fanht
        SoSuspend suspend = soManager.suspendCheck(shop, newSo, slList, soDef, soCmd.getOrderMember());
        if(suspend.getIsSuspend()){
        	newSo.setSuspendReasonType(suspend.getSuspendReasonType());
        }
        // 订单保存
        salesOrderDao.insert(newSo);
        SalesOrder s = newSo;
        //zhiyong.shi 如果开启依次过仓
        //zhiyong.shi 全渠道订单开启依次过仓需要插入，依次过仓表数据
        if(soCmd.getIsOmnichannelParam() && shop.getIsOneByOneToWh()){
        	//插入依次过仓表数据
		 	SalesOrderOnebyOneTowh toWh = new SalesOrderOnebyOneTowh();
  			toWh.setSoId(s.getId());
  		 	 if(!(s.getIsDirectWmsOrder() == SysWmsStatus.SHOPDOG)){
      			toWh.setWarehouseCode(WmsConstants.SHOP_DOG_WHCODE);
  		 	 }
  			toWh.setShopId(shop.getId());
  			salesOrderOnebyOneTowhDao.insert(toWh);
        }

        // 基于订单类型维护平台来源
        // 订单工作流实例
        try {
            soDef.setSalesOrder(s.getId());

            OrderMember orderMember = soCmd.getOrderMember();
            if (orderMember != null) {
                OrderMember om = new OrderMember();
                BeanUtils.copyProperties(orderMember, om);
                om.setSalesOrder(s.getId());
                orderMemberDao.insert(om);
            }
            
            // 订单行保存
            for (SalesOrderLine sl : slList) {
                sl.setSalesOrder(s.getId());
                soLineDao.insert(sl);
                if (sl.getProLog() != null) {
                    for (SoPromotionApplyLog promLog : sl.getProLog()) {
                        promLog.setSoLine(sl);
                        soPromotionApplyLogDao.insert(promLog);
                    }
                }
            }
            
            // save包装信息
            savePlatformSLPackingInfo(platformSLPackingInfos);
            
            // save服务信息
            List<SoServiceLine> soServiceLineList = saveSoServiceLine(shop.getId(), s, soDef, soCmd.getSoServiceLineCmdList());
            
            //save promotion_detail
            platformPromotionManager.savePlatformPromotions(newSo, soCmd, linePlatformPromotions);
            
            // 应用oms物流推荐
             String currentTrans = soDef.getTransExpCode();
             String paymentType = soCmd.getPaymentTypeStr();
            // 物流宝订单统一使用 其他物流商
             if (SoSpecialType.WLB_ORDER.equals(soCmd.getSpecialType())) {
            	 soDef.setTransNumber(soCmd.getPlatformOrderCodeN());
            	 Transportator tp = baseInfoManager.findTransportatorByCode(OTHER_LOGISTICS);
            	 if (tp != null) {
            		 soDef.setTransExpCode(tp.getExpCode());
            		 soDef.setTransName(tp.getName());
            	 }
             }else if(SoSpecialType.WLB_COLLECT_ORDER.equals(soCmd.getSpecialType())){
            	 Transportator tp = baseInfoManager.findTransportatorByCode(COLLECTION_LOGISTICS);
            	 if (tp != null) {
            		 soDef.setTransExpCode(tp.getExpCode());
            		 soDef.setTransName(tp.getName());
            	 }
             }else {
	             if (!StringUtils.hasText(currentTrans)) {
	                    // OMS物流推荐走老的物流推荐流程
	                    if (paymentType != null && paymentType == "1") { // COD订单物流推荐
	                        Transportator recommendTrans = recommendTransByTemplatForCod(shop);
	                        if (recommendTrans != null) {
	                            soDef.setTransExpCode(recommendTrans.getExpCode());
	                            soDef.setTransName(recommendTrans.getName());
	                        }
	                    } else {// 非COD订单物流推荐
	                        recommendTransportator(newSo, shop);
	                    }
	             }
             }
             //保存发货信息迁移到这里fanht
             soDeliveryInfoDao.insert(soDef);
            newSo.setSoDeliveryInfo(soDef.getId());
           //新增shopId fanht
//            WorkTask task = factory.createTask(Constants.TASK_CODE_SO, newSo,shop.getId());
//
////           约过仓订单，向预约过仓表插入预约过仓配置数据
//            if(newSo.getSpecialType() == SoSpecialType.APPOINTMENT_ORDER.getValue()){
//            	saveSoAppointmentToWhTask(newSo, shop,skuAppointment);
//            }
//            processSalesOrderWorkflowNode(s, soCmd.getOrderMember(), soDef, shop, slList,
//					platformSLPackingInfos, salesOrderPaymentInfoList,
//					soServiceLineList, task);
        } catch (Exception e) {
            log.error("创单异常!!", e);
            throw new BusinessException(ErrorCode.SALES_ORDER_CREATE_TASK_ERROR);
        }

        return s;
    }
	/**
	 * 微软自动发货订单，收货人/联系电话/手机字段均为空，做如下调整：
	 * 自动填充其手机和电话
	 * @author bacui.lu
	 * @date 2016年2月23日
	 * @param soCmd
	 */
	private void specialProcessAutoDeliverySo(SalesOrderCommand soCmd) {
		if (!SoSpecialType.isAutoDeliverySo(SoSpecialType.valueOf(soCmd.getSpecialType()))) {
			return;
		}
		
		if (!StringUtils.hasText(soCmd.getReceiverMobile()) && !StringUtils.hasText(soCmd.getReceiverPhone())) {
			soCmd.setMobile("0");
			soCmd.setReceiverPhone("0");
    	}
	}

	/**
	 * @author bacui.lu
	 * @date 2016年1月15日
	 * @param newSo
	 * @param whCode 
	 * @return
	 */
	private String autoDeliveryOrderWh(SalesOrder newSo, String whCode) {
		if (newSo.getWarehouseCode() == null && CompanyShopWhModel.USE_BAOZUN_WMS.getValue() == newSo.getWfBranch()) {
			if (newSo.getSpecialType().equals(SoSpecialType.AUTO_DELIVERY_INV_ORDER)) {
				ShopWh shopWh = shopWhManager.findPlatformDefaultWh(newSo.getCompanyShop());
				if (shopWh != null) {
					whCode = shopWh.getWhCode();
				} else {
					 throw new BusinessException(ErrorCode.CUSTOMIZATION_TIP, new Object[]{"基础信息维护错误：该订单为微软自动发货订单，店铺[" + newSo.getCompanyShop() + "]没有绑定[平台默认发货仓库]" });
				}
			}
		}
		return whCode;
	}

	
	public void processSalesOrderWorkflowNode(SalesOrder newSo,
                                              OrderMember orderMember, SoDeliveryInfo soDef, CompanyShop shop,
                                              List<SalesOrderLine> slList,
                                              List<SalesOrderLinePackage> platformSLPackingInfos,
                                              List<SalesOrderPaymentInfo> salesOrderPaymentInfoList, List<SoServiceLine> soServiceLineList, WorkTask task)
			throws WorkFlowException {
		
		Boolean isSuspend = Boolean.FALSE;
		if(newSo.getSuspendReasonType()!=null){
			isSuspend = Boolean.TRUE;
		}
		
		//判断是否自动过仓
		Boolean isAutoWh = null;
		if(SoSpecialType.isO2oOrder(newSo.getSpecialType())){
			//o2o订单
			isAutoWh = shop.getIsAutoCommitToWhForO2o() == null ? Boolean.FALSE : shop.getIsAutoCommitToWhForO2o();
		}else{
			//其他订单
			isAutoWh = soManager.getOrderAutoToWh(shop, soDef.getTransExpCode(), SoSpecialType.valueOf(newSo.getSpecialType()));
		}
		/**
		 * 非挂起,自动过仓,非预约过仓订单 
		 */
		if(!isSuspend&&isAutoWh&&!(newSo.getSpecialType()==SoSpecialType.APPOINTMENT_ORDER.getValue())){
			newSo.setIsAutoWh(Boolean.TRUE);
		}
		
		//生成过单pac的数据fanht
		//苹果数据不用发给pac 、非直连订单发给pac，其他订单都不用发给pac fanht
		if((!AppleConstants.APPLE_SHOP_ID.equals(shop.getId())) && (newSo.getIsDirectWmsOrder().equals(SysWmsStatus.UNWMS))){
		    //订单 s/整单包装信息为null/支付信息 salesOrderPaymentInfoList/配送信息 soDef/会员信息 soCmd.getOrderMember();
		    //平台促销信息为null
			
			//新的平台整单促销信息 FIXME 这里需要优化
			List<PlatformPromotion> platformPromotions = platformPromotionDao.findSoPlatformPromotionBySoId(newSo.getId(), shop.getId());
			//封装行数据
		    List<BzOrderLine> bzLines = new ArrayList<BzOrderLine>(slList.size());
		    for (SalesOrderLine line : slList) {
		    	//新的平台促销行信息  FIXME 这里需要优化
				List<PlatformPromotion> linePlatformPromotions = platformPromotionDao.findBySoLineId(newSo.getId(), line.getId(), shop.getId());
				// 一个订单行会有多条包装信息
				List<SalesOrderLinePackage> packages = new ArrayList<SalesOrderLinePackage>();
				for(SalesOrderLinePackage linePackage: platformSLPackingInfos){
					if(line.getId().equals(linePackage.getSalesOrderLine().getId())){
						packages.add(linePackage);
					}
				}
				bzLines.add(OrderUtil.toBzOrderLine(line, linePlatformPromotions, packages));
		    }
		    //保存数据
		    BzOrder bzOrder = OrderUtil.toBzOrder(newSo, bzLines, platformPromotions, null, salesOrderPaymentInfoList, orderMember, soDef, soServiceLineList);
		    SendSoMsg msg = new SendSoMsg();
		    msg.setContent(JSON.toJSONString(bzOrder, SerializerFeature.WriteMapNullValue));
		    msg.setMsgType(InterfaceCodeConstants.O2_1);
		    msg.setExt1(bzOrder.getOmsOrderCode());
		    msg.setShopId(shop.getId());
		    msg.setShopCode(String.valueOf(shop.getId()));
		    sendSoDao.insert(msg);
		}
		
		//生成库存占用流水 fanht
		//占用库存
    	Boolean isPlatformControlInv = Boolean.TRUE;
    	/** 拆单产生的新单, 复制产生的新单 **/
        if (StringUtils.hasText(newSo.getSplitSourceOmsOrderCode()) || StringUtils.hasText(newSo.getCopySourceOmsOrderCode())) {
        	isPlatformControlInv = Boolean.FALSE;
        }

        //插入OrderAutoTaskInfo fanht
        this.createOrderAutoTask(task);
		//处理1001指令问题
		process1001(isSuspend,isAutoWh,task,newSo);
	}

	private void saveSoAppointmentToWhTask(SalesOrder newSo, CompanyShop shop,SkuAppointment skuAppointment) {
		SoAppointmentToWhTask sat = new SoAppointmentToWhTask();
		sat.setVersion(1);
		sat.setSalesOrderId(newSo.getId());
		sat.setFromWhCode(newSo.getWarehouseCode());
		sat.setToWhCode(newSo.getWarehouseCode());
		sat.setToWhTime(DateUtil.getChangeDate(newSo.getCreateTime(), 0, 0, 0, skuAppointment.getToWhTime()));
		sat.setProcessStatus(TaskProcessStatus.PROCESS_WAIT);
		sat.setCreateUserNo("system");
		sat.setCreateTime(DateUtil.now());
		sat.setShopId(shop.getId());
		sat.setIsAutoTo(true);
		sat.setProcessResult("等待处理");
		soAppointmentToWhTaskDao.insert(sat);
	}
	
	/**
	 * 
	 * @param task
	 */
    private void createOrderAutoTask(WorkTask task) {
        OrderAutoTaskInfo info = new OrderAutoTaskInfo();
        info.setOrderId(task.getRefSlipId());
        info.setTask(task.getId());
        info.setShopId(task.getRefSlipShopId());
        this.orderAutoTaskInfoDao.insert(info);
    }
    
    /**
     * 处理1001指令问题
     * @param isSuspend
     * @param isAutoWh
     * @param task
     * @param newSo
     * @throws WorkFlowException
     */
    private void process1001(Boolean isSuspend, Boolean isAutoWh, WorkTask task, SalesOrder newSo) throws WorkFlowException {
    	
    	SoWfTask soTask = new SoWfTask();
    	soTask.setShopId(task.getRefSlipShopId());
    	soTask.setTaskId(task.getId());
    	
    	//直接到待提交预约订单过仓节点
    	if(SoSpecialType.APPOINTMENT_ORDER.equals(newSo.getSpecialType())){
    		soTask.setCmd(SoTransitionType.DIRECT_APPOINTMENT.toString());
        	soTask.setLog("预约过仓订单直接到预约过仓节点");
        	soWfTaskDao.insert(soTask);
        	return;
        }
    	
    	//直接到完成（电子券订单）
    	if(SoSpecialType.ETICKET_ORDER.equals(newSo.getSpecialType())){
        	soTask.setCmd(SoTransitionType.DIRECT_FINISH.toString());
        	soTask.setLog("直接到完成（电子券订单）");
        	
        	soWfTaskDao.insert(soTask);
        	return;
        }
    	
    	//直接到完成（自动发货订单）
    	if(SoSpecialType.AUTO_DELIVERY_ORDER.equals(newSo.getSpecialType())){
        	soTask.setCmd(SoTransitionType.DIRECT_FINISH.toString());
        	soTask.setLog("直接到完成（自动发货订单）");
        	
        	soWfTaskDao.insert(soTask);
        	return;
        }
    	
    	
    	//直接到挂起节点
        if(isSuspend){
        	soTask.setCmd(SoTransitionType.DIRECT_SUSPEND.toString());
        	soTask.setLog("直接到挂起节点");

        	soWfTaskDao.insert(soTask);
        	return;
        }
        
    	//直接到手动过仓
        if(!isSuspend&&!isAutoWh){
        	soTask.setCmd(SoTransitionType.DIRECT_MANUAL_WH.toString());
        	soTask.setLog("直接到手动过仓");
        	
        	soWfTaskDao.insert(soTask);
        	return;
        }
        
        //直接到待ERP过仓至WMS（非直连）FIXME 为什么为空  newSo.getIsDirectWmsOrder() == null
        if(newSo.getIsAutoWh() && (newSo.getIsDirectWmsOrder() == null || SysWmsStatus.UNWMS == newSo.getIsDirectWmsOrder())){	
        	soTask.setCmd(SoTransitionType.DIRECT_WAIT_ERP_TO_WH.toString());
        	soTask.setLog("直接到待ERP过仓至WMS（非直连）");
        	
        	soWfTaskDao.insert(soTask);
        	return;
        }
        
        //直接到待同步过仓指令至ERP（直连）
       	if(newSo.getIsAutoWh() && (!(SysWmsStatus.UNWMS == newSo.getIsDirectWmsOrder()))){
        	soTask.setCmd(SoTransitionType.DIRECT_WAIT_CMD_TO_WH.toString());
        	soTask.setLog("直接到待同步过仓指令至ERP（直连）");
        	soWfTaskDao.insert(soTask);
        	return;
        }
    	
    	
//    	//直接到完成（电子券订单）
//    	if(SoSpecialType.ETICKET_ORDER.equals(newSo.getSpecialType())){
//        	factory.transit(task, task.getCurrentNodeNo(), SoTransitionType.DIRECT_FINISH.toString(), null, "直接到完成（电子券订单）");
//        	return;
//        }
//    	
//    	//直接到完成（自动发货订单）
//    	if(SoSpecialType.AUTO_DELIVERY_ORDER.equals(newSo.getSpecialType())){
//        	factory.transit(task, task.getCurrentNodeNo(), SoTransitionType.DIRECT_FINISH.toString(), null, "直接到完成（自动发货订单）");
//        	return;
//        }
//    	
//    	//直接到挂起节点
//        if(isSuspend){
//        	factory.transit(task, task.getCurrentNodeNo(), SoTransitionType.DIRECT_SUSPEND.toString(), null, "直接到挂起节点");
//        	return;
//        }
//        
//        if(!isSuspend&&!isAutoWh){
//        	//直接到手动过仓
//        	factory.transit(task, task.getCurrentNodeNo(), SoTransitionType.DIRECT_MANUAL_WH.toString(), null, "直接到手动过仓");
//        	return;
//        }
//        
//        //直接到待ERP过仓至WMS（非直连）
//        if(newSo.getIsAutoWh()&&!newSo.getIsDirectWmsOrder()){	
//        	factory.transit(task, task.getCurrentNodeNo(), SoTransitionType.DIRECT_WAIT_ERP_TO_WH.toString(), null, "直接到待ERP过仓至WMS（非直连）");
//        	return;
//        }
//        
//        //直接到待同步过仓指令至ERP（直连）
//    	if(newSo.getIsAutoWh()&&newSo.getIsDirectWmsOrder()){
//        	factory.transit(task, task.getCurrentNodeNo(), SoTransitionType.DIRECT_WAIT_CMD_TO_WH.toString(), null, "直接到待同步过仓指令至ERP（直连）");
//        	return;
//        }
    	
    }


    /**
     * serviceType表示查询所有的支持服务类型的服务商。
	 *		家装干线服务     11
	 *		家装干支服务     12
	 *		家装干支装服务   13
	 *		卫浴大件干线     14
	 *		卫浴大件干支     15
	 *		卫浴大件安装     16
	 *		地板干线         17
	 *		地板干支         18
	 *		地板安装         19
	 *		灯具安装         20
	 *		卫浴小件安装     21
	 *		
	 *
	 *  （注：同一个服务商针对不同类型的serviceType是具有不同的tpCode的）
	 *		干支：送货上门
	 *		干线：自提
	 *		安装：上门安装
     * @methodName com.jumbo.manager.sales.SalesOrderManagerImpl.saveSoServiceLine
     * @author hailiang.jiang
     * @date 2015年3月5日 下午4:47:22
     * @version: v1.0.0
     */
    private List<SoServiceLine> saveSoServiceLine(Long shopId, SalesOrder so, SoDeliveryInfo soDeli, List<SoServiceLineCommand> soServiceLineCmdList) {
    	List<SoServiceLine> soServiceLineList = new ArrayList<SoServiceLine>();
    	if (soServiceLineCmdList == null || soServiceLineCmdList.isEmpty()) {
    		return soServiceLineList;
    	}
    	
    	//送货上门、自提逻辑判断
    	StringBuilder errorMsg = new StringBuilder();
    	boolean isCustomersFromcCarrying = false;
    	boolean isHomeDeliveryService = false; //是否需要送货上门服务
    	boolean isInstallService = false; //是否需要安装服务标识
    	BigDecimal sumHomeDeliveryFee = BigDecimal.ZERO;
    	BigDecimal sumInstallFee = BigDecimal.ZERO;
    	List<SoServiceLineCommand> filterSoServiceLineCmdList = new ArrayList<SoServiceLineCommand>();
    	for (SoServiceLineCommand sslCmd : soServiceLineCmdList) {
    		SoServiceType serviceType = SoServiceType.getServiceType(sslCmd.getTmserSpuCode());
    		if (serviceType == null && (sslCmd.getTotalActual() == null || BigDecimal.ZERO.compareTo(sslCmd.getTotalActual()) == 0)) { //如果不能识别服务订单，且服务费用为0，则本行不纳入订单服务行(故事来源：零元的质保服务)
    			continue;
    		}
    		if (serviceType == null) {
    			errorMsg.append("订单服务信息存在不能识别的服务类型(即：字段值中不存在【干支】、【干线】、【安装】关键字或存在【干支装】服务)");
    			break;
    		}
    		if (!isHomeDeliveryService && SoServiceType.HOME_DELIVERY_SERVICE.getValue() == serviceType.getValue()) {
    			isHomeDeliveryService = true;
    		}
    		if (!isInstallService && SoServiceType.HOME_INSTALL_SERVICE.getValue() == serviceType.getValue()) {
    			isInstallService = true;
    		}
    		if (!isCustomersFromcCarrying && SoServiceType.CUSTOMERS_FROM_CARRYING.getValue() == serviceType.getValue()) {
    			isCustomersFromcCarrying = true;
    		}
    		
    		if (SoServiceType.HOME_DELIVERY_SERVICE.getValue() == serviceType.getValue()) {
    			sumHomeDeliveryFee = sumHomeDeliveryFee.add(sslCmd.getTotalActual() == null ? BigDecimal.ZERO : sslCmd.getTotalActual());
    		}
    		
    		if (SoServiceType.HOME_INSTALL_SERVICE.getValue() == serviceType.getValue()) {
    			sumInstallFee = sumInstallFee.add(sslCmd.getTotalActual() == null ? BigDecimal.ZERO : sslCmd.getTotalActual());
    		}
    		
    		filterSoServiceLineCmdList.add(sslCmd);
    	}
    	
    	if (StringUtils.hasText(errorMsg.toString())) {
    		throw new BusinessException(ErrorCode.CUSTOMIZATION_TIP, new Object[] {errorMsg.toString()});
    	}
    	
    	so.setHomeDeliveryFee(sumHomeDeliveryFee);
    	so.setInstallFee(sumInstallFee);
    	so.setDeliveryType(isHomeDeliveryService ? SoDeliveryType.HOME_DELIVERY_SERVICE.getValue() : (isCustomersFromcCarrying ? SoDeliveryType.CUSTOMERS_FROM_CARRYING.getValue() : SoDeliveryType.DEFAULT.getValue()));
    	so.setIsInstall(isInstallService);
    	ServiceInstallCompany sic = null;
    	//如果是安装服务，需要查到安装服务的公司
    	if (isInstallService) {
    		sic = serviceInstallCompanyManager.findServiceInstallCompany(shopId, soDeli.getProvince(), soDeli.getCity(), soDeli.getDistrict());
    		so.setInstallCompanyCode(sic.getCompanyCode());
    		so.setInstallCompanyName(sic.getCompanyName());
    	}
    	
    	for (SoServiceLineCommand sslCmd : filterSoServiceLineCmdList) {
    		SoServiceLine ssl = new SoServiceLine();
        	BeanUtils.copyProperties(sslCmd, ssl, new String[] {"serviceType"});
        	ssl.setId(null);
        	ssl.setServiceType(SoServiceType.getServiceType(sslCmd.getTmserSpuCode()));
        	ssl.setServiceTypeDesc(ssl.getServiceType().getName());
        	SoServiceLineManagerImpl.applySoServiceProvider(ssl, sic);
        	ssl.setSalesOrder(so);
        	ssl.setShopId(shopId);
        	this.soServiceLineDao.insert(ssl);
        	soServiceLineList.add(ssl);
    	}
    	return soServiceLineList;
	}

	private void checkO2oPostSalesServiceInfo(CompanyShop shop, SalesOrderCommand soCmd, List<SalesOrderLineCommand> solCmdList) {
    	if (shop.getIsO2OShop() != null && shop.getIsO2OShop() && (soCmd.getIsO2oOrder() != null && soCmd.getIsO2oOrder())) {//O2O店铺且为O2O订单
    		//如果订单明细行中，包含O2O有售后服务的商品和无售后服务的商品，则创单失败
    		Set<String> postSalesServiceSet = new HashSet<String>();
    		for (SalesOrderLineCommand solCmd : solCmdList) {
    			if (solCmd.getOrderLineType() != null && OrderLineType.isNotTmallPlatformGift(solCmd.getOrderLineType())) {//如果是赠品则表示不做检查
    				continue;
    			}
    			if (solCmd.getIsPostSalesServiceSku() != null && solCmd.getIsPostSalesServiceSku()) {//有售后服务
    				postSalesServiceSet.add("postSalesService");
    			} else {
    				postSalesServiceSet.add("noPostSalesService");
    			}
    		}
    		if (postSalesServiceSet.size() == 1) {
    			String postSalesService = postSalesServiceSet.iterator().next();
    			soCmd.setSpecialType("postSalesService".equals(postSalesService) ? SoSpecialType.O2O_ORDER_POST_SALES_SERVICE.getValue() : SoSpecialType.O2O_ORDER_NOT_POST_SALES_SERVICE.getValue());
    		} else {
    			throw new BusinessException(ErrorCode.CUSTOMIZATION_TIP, new Object[] {"该单为O2O订单，由于同时存在售后服务和无售后服务的商品，故需要进行拆单，请将店铺开关设置为【按O2O订单服务类型拆】"});
    		}
    	}
	}

	/**
     *  天猫服务站订单抓进系统后，收货人/联系电话/手机字段均为空，先做如下调整：
     *    1）OMS-Tmall订单创建时，将校验【收货人/联系电话/手机】三个字段是否均为空，若为空，则进入 2）；
     *    2）校验【收货地址】字段是否包含【天猫服务站】字眼，若包含，则将【收货人】置为【天猫服务站】，【联系电话】置为0。
     *
     * @methodName com.jumbo.manager.sales.SalesOrderManagerImpl.specialProcessSoDeliveryInfo
     * @author hailiang.jiang
     * @date 2014年10月28日 上午10:29:12
     * @param soCmd
     */
	@Deprecated
    private void specialProcessSoDeliveryInfo(SalesOrderCommand soCmd) {
    	if (!StringUtils.hasText(soCmd.getReceiver()) && !StringUtils.hasText(soCmd.getReceiverMobile()) && !StringUtils.hasText(soCmd.getReceiverPhone())) {
    		String text = "天猫服务站";
    		if (StringUtils.hasText(soCmd.getAddress()) && soCmd.getAddress().contains(text)) {
    			soCmd.setReceiver(text);
    			soCmd.setMobile("0");
    			soCmd.setReceiverPhone("0");
    		}
    	}
	}

	/**
     * 从店铺上获取默认的COD物流
     * 
     * @param shop
     * @return
     */
    private Transportator recommendTransByTemplatForCod(CompanyShop shop) {
        Transportator recommendTrans = null;
        DefaultTransTemplete transTemplate = shop.getDeftransTemp();
        if (transTemplate != null) {
            recommendTrans = transTemplate.getCodTrans();
        }
        return recommendTrans;
    }

    /**
     * 
     * @param so
     * @param shop
     */
    // FIXME 物流模板
    private void recommendTransportator(SalesOrder so, CompanyShop shop) {
        Boolean isUseDefTrans = shop.getIsUseDefTrans() == null ? Boolean.FALSE : shop.getIsUseDefTrans();
        DefaultTransTemplete tp = shop.getDeftransTemp();
        if (isUseDefTrans && tp != null) {
            tp = defaultTransTempleteManager.findDefaultTransTemplete(shop.getDeftransTemp().getId());
            Long transTempId = tp.getId();

            SoDeliveryInfo soDeliveryInfo = soDeliveryInfoDao.findBySoId(so.getSoDeliveryInfo());;
            if (!StringUtils.hasText(soDeliveryInfo.getTransExpCode())) {
                String province = soDeliveryInfo.getProvince();
                String city = soDeliveryInfo.getCity();
                String address = soDeliveryInfo.getAddress();
                String transCode = null;
                // 1.非特定省份且address中包含关键字[乡，监狱，油田，农场，旗，盟，法院，部队，机关]则默认应用EMS fanht
                if ((tp.getIsExcludeEMS() == null || !tp.getIsExcludeEMS())
                        && !unApplyEMSProvinces.contains(province)
                        && ((address.contains("乡") && !address.contains("监狱")) || address.contains("油田") || address.contains("农场") || address.contains("旗") 
                        		|| address.contains("盟") || address.contains("法院") || address.contains("部队") || address.contains("机关"))) {
                    transCode = Transportator.EMS;
                } else { // 2.应用物流模板
                    Map<String, DefaultTransTempleteDetailCommand> provinceTransMap = loadAllDefaultTrans(transTempId);
                    DefaultTransTempleteDetailCommand templateDetailHK = provinceTransMap.get("香港特别行政区");
                    String reachableAreaHK = "";
                    if (templateDetailHK != null) {
                        reachableAreaHK = templateDetailHK.getReachableArea();
                    }
                    if (reachableAreaHK == null || !reachableAreaHK.equals("无")) { // 香港特别行政区配送区域维护成“无”则表示应用oms物流推荐但不应用物流模板
                        DefaultTransTempleteDetailCommand templateDetail = provinceTransMap.get(province);
                        if (templateDetail != null) {
                            String tCode = templateDetail.getExpCode();
                            String reachableArea = templateDetail.getReachableArea();
                            String unReachableArea = templateDetail.getUnReachableArea();
                            boolean flag = true; // 是否取默认物流标识
                            if (reachableArea != null && !reachableArea.trim().equals("")) { // 存在指定开通城市
                                if (!reachableArea.contains(city)) {
                                    flag = false; // 配送城市不在[指定的开通城市]中则flag=false
                                }
                            } else if (unReachableArea != null && !unReachableArea.trim().equals("")) {// 存在指定未开通城市
                                if (unReachableArea.contains(city)) {
                                    flag = false; // 配送城市在[指定的未开通城市]中则flag=false
                                }
                            }
                            if (flag) {
                                transCode = tCode;
                            }
                        }
                        if ((tp.getIsExcludeEMS() == null || !tp.getIsExcludeEMS()) && !StringUtils.hasText(transCode) && !unApplyEMSProvinces.contains(province)) {// 3.非特定省份的订单应用物流模板后仍未能匹配上物流,则默认置EMS
                            transCode = Transportator.EMS; // EMS
                        }
                    }
                }
                if (StringUtils.hasText(transCode)) {
                    Transportator trans = baseInfoManager.findTransportatorByCode(transCode);
                    if (trans != null) {
                        soDeliveryInfo.setTransExpCode(trans.getExpCode());
                        soDeliveryInfo.setTransName(trans.getName());
                    }
                }
            }
        }
    }

    private Map<String, DefaultTransTempleteDetailCommand> loadAllDefaultTrans(Long tempId) {
        List<DefaultTransTempleteDetailCommand> tempDetailList = defaultTransTempleteManager.findDTTDByTempId(tempId);
        Map<String, DefaultTransTempleteDetailCommand> provinceTransMap = new HashMap<String, DefaultTransTempleteDetailCommand>();
        for (DefaultTransTempleteDetailCommand tempDetail : tempDetailList) {
            String province = tempDetail.getProvince();
            provinceTransMap.put(province, tempDetail);
        }
        return provinceTransMap;
    }

    /**
     * 验证分仓编码
     * @param shop
     * @param soCmd
     * @param soLineCommandList
     * @return
     */
    @Override
    public String validateBranchWarehouseCode(CompanyShop shop, SalesOrderCommand soCmd, List<SalesOrderLineCommand> soLineCommandList) {
    	Set<String> currentSoLinePlatformWhCodeList = new HashSet<String>();
    	
    	String platformWhCode = null;
    	for (int i = 0; i < soLineCommandList.size(); i++) {
    		platformWhCode = soLineCommandList.get(i).getPlatformWhCode();
    		//需求变更：分仓编码为空的情况，统一默认使用IC
    		if (!StringUtils.hasText(platformWhCode)) {
    			platformWhCode = BranchWarehouseManager.IC_WAREHOUSE_CODE;
    		}
    		currentSoLinePlatformWhCodeList.add(platformWhCode);
    	}
    	
    	String errorMsg = checkWhCode(shop.getId(), currentSoLinePlatformWhCodeList);
    	if (StringUtils.hasText(errorMsg)) {
    		return errorMsg;
    	}
    	
    	//O2O发货分仓列表
    	List<BranchWarehouse> o2oWhCodeList = this.branchWarehouseManager.findBranchWarehouseList(shop.getId(), BranchWarehouseWmsType.O2O_SYSTEM, BranchWarehouseStatus.ENABLE);
    	
    	Set<String> intersectionWhCodeSet = this.intersectionWhCodeForCollection(currentSoLinePlatformWhCodeList, o2oWhCodeList);
    	if (intersectionWhCodeSet.size() == 0) {//交集为0(即订单明细行中的所以分仓编码，都不在[O2O发货分仓列表]中), 都使用IC分仓
    		//WMS发货
    		soCmd.setMainBranchWhCode(BranchWarehouseManager.IC_WAREHOUSE_CODE);
    		soCmd.setIsO2oOrder(Boolean.FALSE);
    	} else if (intersectionWhCodeSet.size() == 1 && intersectionWhCodeSet.size() == currentSoLinePlatformWhCodeList.size()) {//订单明细行的所有分仓编码都在[O2O发货仓列表]中
    		//O2O发货
    		String mainBranchWhCode = intersectionWhCodeSet.iterator().next();
			soCmd.setMainBranchWhCode(mainBranchWhCode);
			soCmd.setIsO2oOrder(Boolean.TRUE);
    	} else {//部分在[O2O发货分仓列表]中、 全在[O2O发货分仓列表]中，且交集大于0  ==> 则属于混合仓库，订单挂起
    		//o2o混合仓，订单挂起
    		soCmd.setMainBranchWhCode(BranchWarehouseManager.MIX_WAREHOUSE_CODE);
    		soCmd.setIsO2oOrder(Boolean.TRUE);
    	}
    	
    	return "";
	}
    
    /**
     * 验证分仓编码是否有效
     */
    private String checkWhCode(Long shopId, Set<String> currentSoLinePlatformWhCodeList) {
    	StringBuilder errorMsgBuffer = new StringBuilder();
    	//分仓去重
    	Set<String> soLineWhCodeList = new HashSet<String>();
    	for (String whCode : currentSoLinePlatformWhCodeList) {
    		soLineWhCodeList.add(whCode);
    	}
    	List<BranchWarehouse> dbWhCodeList = this.branchWarehouseManager.findBranchWarehouseList(shopId, soLineWhCodeList, BranchWarehouseStatus.ENABLE);
    	if (dbWhCodeList != null && soLineWhCodeList.size() != dbWhCodeList.size()) {
    		errorMsgBuffer.append("订单分仓编码无效，请分别在OMS-Tmall，集团OMS、O2O系统维护");
    	}
		return errorMsgBuffer.toString();
	}

	/**
     * 求两个集合中分仓编码的交集
     * @param currentSoLinePlatformWhCodeList
     * @param o2oWhCodeList
     * @return 交集结果
     */
    private Set<String> intersectionWhCodeForCollection(Set<String> currentSoLinePlatformWhCodeList, List<BranchWarehouse> o2oWhCodeList) {
    	Set<String> intersectioinSet = new HashSet<String>();
    	
    	if (currentSoLinePlatformWhCodeList == null || currentSoLinePlatformWhCodeList.isEmpty() || o2oWhCodeList == null || o2oWhCodeList.isEmpty()) {
    		return intersectioinSet;
    	}
    	
    	for (String soLineWhCode : currentSoLinePlatformWhCodeList) {
    		for (BranchWarehouse branchWarehouse : o2oWhCodeList) {
        		if (soLineWhCode.equals(branchWarehouse.getWhCode())) {
        			intersectioinSet.add(soLineWhCode);
        			break;
        		}
        	}
    	}
    	
    	return intersectioinSet;
    }

    /** 封装行级别包装信息 */
    private void genratorPlatformSlPackingInfo(List<SalesOrderLinePackage> platformSLPackingInfos, SalesOrderLineCommand slCommand, SalesOrderLine sol, SalesOrder so) {
    	
    	//由于前两年的活动需求，所以在建立此逻辑时是排除组合编码操作特殊行包装的，今天月饼券的活动机制有所改动，会使用到组合编码的情况，所以麻烦特殊行包装逻辑不排除组合编码，谢谢 fanht
//        // 哈根达斯非特供卷禁用特殊包装，防止天猫组合商品中包含特供商品与非特供商品均打上特殊包装标识
//        if (Constants.HD_INNER_SHOP_CODE.equalsIgnoreCase(so.getCompanyShop().getInnerShopCode()) && !Constants.HD_SKU.contains(sol.getSku().getExtensionCode1())) {
//            return;
//        }
        
        if (slCommand.getSalesOrderLinePackage() != null && slCommand.getSalesOrderLinePackage().size() > 0) {
            for (SalesOrderLinePackage packingInfo : slCommand.getSalesOrderLinePackage()) {
                packingInfo.setSalesOrderLine(sol);
                packingInfo.setSalesOrder(so);
                platformSLPackingInfos.add(packingInfo);
            }
        }
    }

    private ShopWhCommand recommendSendWh(Long shopId, String province) {
        if (StringUtils.hasText(province)) {
            province = "%" + province + "%";
        }
        // 初始化数据t_wh_delivery_area
        List<ShopWhCommand> whList = shopWhDao.findByShopIdAndProvince(shopId, province);
        return (whList != null && whList.size() > 0) ? whList.get(0) : null;
    }

    /**
     * 订单来源解析
     * 
     * @param so
     * @param soLines
     * @param sourceMap
     */
    private void splitSourceForSo(SalesOrder so, Collection<SalesOrderLine> soLines, Map<String, String> sourceMap) {
        String SO_SOURCE_CATEGORY_TERMINAL = "terminalSource";
        String SO_SOURCE_CATEGORY_PLATFORM = "platformSource";
        String SO_SOURCE_CATEGORY_ACTIVITY = "activitySource";
        // SO
        String sourceRemark = so.getSourceRemark();
        if (sourceRemark != null && !sourceRemark.trim().equals("")) {
            String[] sourcekArr = sourceRemark.split(",");
            for (String sourceCode : sourcekArr) {
                String sourceCategory = sourceMap.get(sourceCode);
                if (sourceCategory == null) {
                    continue;
                } else if (SO_SOURCE_CATEGORY_TERMINAL.equals(sourceCategory)) {
                    so.setTerminalSource(sourceCode);
                } else if (SO_SOURCE_CATEGORY_PLATFORM.equals(sourceCategory)) {
                    so.setPlatformSource(sourceCode);
                } else if (SO_SOURCE_CATEGORY_ACTIVITY.equals(sourceCategory)) {
                    so.setActivitySource(sourceCode);
                }
            }
        }
        if (so.getTerminalSource() == null || so.getTerminalSource().trim().equals("")) {
            so.setTerminalSource("PC");
        }
        // SoLine
        for (SalesOrderLine sl : soLines) {
            String lineSourceRemark = sl.getSourceRemark();
            if (lineSourceRemark != null && !lineSourceRemark.trim().equals("")) {
                String[] lineSourceArr = lineSourceRemark.split(",");
                for (String lineSourceCode : lineSourceArr) {
                    String sourceCategory = sourceMap.get(lineSourceCode);
                    if (sourceCategory == null) {
                        continue;
                    } else if (SO_SOURCE_CATEGORY_ACTIVITY.equals(sourceCategory)) {
                        sl.setActivitySource(lineSourceCode);
                    }
                }
            }
        }
    }


    private void savePlatformSLPackingInfo(List<SalesOrderLinePackage> platformSLPackingInfos) {
        for (SalesOrderLinePackage platformSLPackingInfo : platformSLPackingInfos) {
           salesOrderLinePackageDao.insert(platformSLPackingInfo);
        }
    }

    @Override
    // 自动拆单
    public List<SalesOrderCommand> splitTrade(SalesOrderCommand originalSoCmd) {
        List<SalesOrderCommand> res = new ArrayList<SalesOrderCommand>();

        CompanyShop shop = companyShopDao.findById(originalSoCmd.getCompanyShop());

        // 按product销售模式拆
        Map<SalesMode, List<SalesOrderLineCommand>> map = new HashMap<SalesMode, List<SalesOrderLineCommand>>();
        for (SalesOrderLineCommand originalSolCmd : originalSoCmd.getSoLineCommandList()) {


            Sku sku = skuDao.findById(originalSolCmd.getSku());
            Product byId = productDao.findById(sku.getProduct());
            SalesMode salesMode = retainSalesModeByShopAndProduct(byId.getSalesModesStr(), shop.getSalesModesStr());
            List<SalesOrderLineCommand> solCmdListInMap = map.get(salesMode);
            if (solCmdListInMap == null) {
                solCmdListInMap = new ArrayList<SalesOrderLineCommand>();
                map.put(salesMode, solCmdListInMap);
            }
            solCmdListInMap.add(originalSolCmd);
        }

        // 付款经销，非付款经销区分
        Map<String, List<SalesOrderLineCommand>> map2 = new HashMap<String, List<SalesOrderLineCommand>>();
        List<SalesOrderLineCommand> payment = map.get(SalesMode.PAYMENT);
        if (payment != null) {
            map2.put(SalesMode.PAYMENT.getValue() + "", payment);
            map.remove(SalesMode.PAYMENT);
        }
        StringBuilder _otherSalesModesStr = new StringBuilder();
        List<SalesOrderLineCommand> otherSalesModesList = new ArrayList<SalesOrderLineCommand>();
        for (Entry<SalesMode, List<SalesOrderLineCommand>> entry : map.entrySet()) {
            _otherSalesModesStr.append(entry.getKey().getValue()).append(",");
            otherSalesModesList.addAll(entry.getValue());
        }
        if (_otherSalesModesStr.length() != 0) {
            map2.put(_otherSalesModesStr.substring(0, _otherSalesModesStr.length() - 1), otherSalesModesList);
        }

        BigDecimal totalPercent = BigDecimal.ZERO;
        int i = 0;
        BigDecimal totalAmountBeforeDiscount = BigDecimal.ZERO;
        BigDecimal totalAmountAfterDiscount = BigDecimal.ZERO;
        BigDecimal totalDiscountTotal = BigDecimal.ZERO;
        BigDecimal totalLinesDiscount = BigDecimal.ZERO;
        BigDecimal totalHeadDiscount = BigDecimal.ZERO;
        BigDecimal totalPaymentDiscount = BigDecimal.ZERO;
        BigDecimal totalTotalOuterPoint = BigDecimal.ZERO;
        BigDecimal totalTotalInnerPoint = BigDecimal.ZERO;
        BigDecimal totalUsePoint = BigDecimal.ZERO;
        BigDecimal totalVirtualAmount = BigDecimal.ZERO;

        for (Entry<String, List<SalesOrderLineCommand>> entry : map2.entrySet()) {
            SalesOrderCommand soCmd = new SalesOrderCommand();
            try {
                // BeanUtils.copyProperties(soCmd, originalSoCmd);
                PropertyUtil.copyProperties(originalSoCmd, soCmd);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(e);
            }

            soCmd.setSalesModesStr(entry.getKey());
            List<SalesOrderLineCommand> solCmdListInMap = entry.getValue();
            BigDecimal sumTotalAmountOfLine = BigDecimal.ZERO; // 订单行unitPrice*qty总和

            for (SalesOrderLineCommand solCmd : solCmdListInMap) {
                sumTotalAmountOfLine = sumTotalAmountOfLine.add(solCmd.getTotalAmount());
            }

            // 重新计算头金额
            i++;
            soCmd.setTransFee((i == 1) ? originalSoCmd.getTransFee() : BigDecimal.ZERO);
            if (i != map.size()) {
                BigDecimal originalAmountBeforeDiscount = originalSoCmd.getAmountBeforeDiscount();
                BigDecimal percent = (!BigDecimal.ZERO.equals(originalAmountBeforeDiscount)) ? (sumTotalAmountOfLine.divide(originalSoCmd.getAmountBeforeDiscount()).setScale(2, BigDecimal.ROUND_DOWN)) : BigDecimal.ZERO;
                totalPercent.add(percent);

                soCmd.setAmountBeforeDiscount(originalSoCmd.getAmountBeforeDiscount().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                soCmd.setAmountAfterDiscount(originalSoCmd.getAmountAfterDiscount().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                soCmd.setDiscountTotal(originalSoCmd.getDiscountTotal().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                soCmd.setLinesDiscount(originalSoCmd.getLinesDiscount().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                soCmd.setHeadDiscount(originalSoCmd.getHeadDiscount().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                soCmd.setPaymentDiscount(originalSoCmd.getPaymentDiscount().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                // 积分
                soCmd.setTotalInnerPoint(originalSoCmd.getTotalInnerPoint().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                soCmd.setTotalOuterPoint(originalSoCmd.getTotalOuterPoint().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                soCmd.setUsePoint(originalSoCmd.getUsePoint().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));
                soCmd.setVirtualAmount(originalSoCmd.getVirtualAmount().multiply(percent).setScale(2, BigDecimal.ROUND_DOWN));

                // 汇总
                totalAmountBeforeDiscount.add(soCmd.getAmountBeforeDiscount());
                totalAmountAfterDiscount.add(soCmd.getAmountAfterDiscount());
                totalDiscountTotal.add(soCmd.getDiscountTotal());
                totalLinesDiscount.add(soCmd.getLinesDiscount());
                totalHeadDiscount.add(soCmd.getHeadDiscount());
                totalPaymentDiscount.add(soCmd.getPaymentDiscount());
                totalTotalInnerPoint.add(soCmd.getTotalInnerPoint());
                totalTotalOuterPoint.add(soCmd.getTotalOuterPoint());
                totalUsePoint.add(soCmd.getUsePoint());
                totalVirtualAmount.add(soCmd.getVirtualAmount());
            } else {
                soCmd.setAmountBeforeDiscount(originalSoCmd.getAmountBeforeDiscount().subtract(totalAmountBeforeDiscount));
                soCmd.setAmountAfterDiscount(originalSoCmd.getAmountAfterDiscount().subtract(totalAmountAfterDiscount));
                soCmd.setDiscountTotal(originalSoCmd.getDiscountTotal().subtract(totalDiscountTotal));
                soCmd.setLinesDiscount(originalSoCmd.getLinesDiscount().subtract(totalLinesDiscount));
                soCmd.setHeadDiscount(originalSoCmd.getHeadDiscount().subtract(totalHeadDiscount));
                soCmd.setPaymentDiscount(originalSoCmd.getPaymentDiscount().subtract(totalPaymentDiscount));
                // 积分
                soCmd.setTotalInnerPoint(originalSoCmd.getTotalInnerPoint().subtract(totalTotalInnerPoint));
                soCmd.setTotalOuterPoint(originalSoCmd.getTotalOuterPoint().subtract(totalTotalOuterPoint));
                soCmd.setUsePoint(originalSoCmd.getUsePoint().subtract(totalUsePoint));
                soCmd.setVirtualAmount(originalSoCmd.getVirtualAmount().subtract(totalVirtualAmount));
            }

            soCmd.setSubOrderSource(Constants.AUTO);
            soCmd.setSoLineCommandList(solCmdListInMap);
            res.add(soCmd);
        }

        return res;
    }

    @Override
    public List<SalesOrderCommand> splitTradeByShopSkuWh(SalesOrderCommand srcSoCmd, CompanyShop shop) {
        List<SalesOrderCommand> resultList = new ArrayList<SalesOrderCommand>();
        Map<String, List<SalesOrderLineCommand>> whSoMap = shopSkuWhRefManager.matchWarehouse(shop, srcSoCmd.getSoLineCommandList());
        if (!existMultipleWarehouse(whSoMap)) {
        	//不存在多仓：变成一个选仓的逻辑了fanht
            srcSoCmd.setWarehouseCode((whSoMap != null && whSoMap.size() == 1) ? (whSoMap.keySet().iterator().next()) : (srcSoCmd.getWarehouseCode()));
            resultList.add(srcSoCmd);
            return resultList;
        }

        // 存在商品分布在多仓的情况，且按SKU指定仓库（SkuSplitType.NO_SPLIT_BY_SKU_DEFAULT_WH）时，使用默认仓库
        ShopWh shopWh = shopWhManager.getDefaultWarehouse(shop.getId());
        if (existMultipleWarehouse(whSoMap) && shop.getSkuSplitType() != null && SkuSplitType.NO_SPLIT_BY_SKU_DEFAULT_WH.getValue() == shop.getSkuSplitType()) {
            srcSoCmd.setWarehouseCode(shopWh.getWhCode());
            resultList.add(srcSoCmd);
            return resultList;
        }

        // 否则按店铺SKU默认仓拆分订单SkuSplitType.SPLIT_BY_SKU_DEFAULT_WH
        resultList.addAll(salesOrderSplitManager.splitTradeByShopSkuWh(srcSoCmd, whSoMap));

        return resultList;
    }

    private boolean existMultipleWarehouse(Map<String, List<SalesOrderLineCommand>> whSoMap) {
        if (whSoMap == null || whSoMap.isEmpty() || whSoMap.size() == 1) {
            return false;
        }

        return true;
    }

    @Override
    public SalesMode retainSalesModeByShopAndProduct(String salesModesStr1, String salesModesStr2) {
        return SalesMode.PAYMENT;
    }

    @Override
    public void assignPromotionfee(SalesOrderCommand soCmd) {
    	//订单折扣
        BigDecimal soDiscountTotal = soCmd.getDiscountTotal();
        //订单折前金额
        BigDecimal soAmountBeforeDiscount = soCmd.getAmountBeforeDiscount();
        //使用的积分
        BigDecimal soUsePoint = soCmd.getUsePoint();
        //商品行
        Collection<SalesOrderLineCommand> soLines = soCmd.getSoLineCommandList();
        //是否使用积分
        boolean pointFlag = false;
        if (soUsePoint != null && soUsePoint.doubleValue() != 0) {
            pointFlag = true;
        }

        // 平摊让利与积分/订单是否有折扣
        if (soDiscountTotal.compareTo(BigDecimal.ZERO) == 0) {
        	//订单无折扣：分摊积分金额
            List<SalesOrderLineCommand> subList = new ArrayList<SalesOrderLineCommand>(); // 非赠品明细行
            for (SalesOrderLineCommand soLine : soLines) {
                if (soLine.getUnitPrice() == null || soLine.getUnitPrice().doubleValue() == 0) {
                	//赠品行金额处理
                    soLine.setLineDiscount(BigDecimal.ZERO);
                    soLine.setTotalDiscount(BigDecimal.ZERO);
                    soLine.setTotalAmountAfterDiscount(BigDecimal.ZERO);
                    soLine.setUnitPriceAfterDiscount(BigDecimal.ZERO);
                    soLine.setVirtualAmount(BigDecimal.ZERO);
                    soLine.setInvoiceTotalAmount(BigDecimal.ZERO);
                    soLine.setInvoiceUnitPrice(BigDecimal.ZERO);
                } else {
                	//非赠品明细行
                    subList.add(soLine);
                }
            }

            // 按行的requestQty*unitPric升序排列
            Collections.sort(subList, new Comparator<SalesOrderLineCommand>() {
                public int compare(SalesOrderLineCommand sl0, SalesOrderLineCommand sl1) {
                    BigDecimal total0 = sl0.getUnitPrice().multiply(new BigDecimal(sl0.getQuantity()));
                    BigDecimal total1 = sl1.getUnitPrice().multiply(new BigDecimal(sl1.getQuantity()));
                    int flag = total0.compareTo(total1);
                    return flag;
                }
            });
            //均摊积分金额：确定开票金额
            int size = subList.size();
            int index = 1;
            BigDecimal usedPoint = BigDecimal.ZERO;
            for (SalesOrderLineCommand sl : subList) {
                sl.setLineDiscount(BigDecimal.ZERO);
                sl.setTotalDiscount(BigDecimal.ZERO);
                sl.setTotalAmountAfterDiscount(sl.getTotalAmount());
                sl.setUnitPriceAfterDiscount(sl.getUnitPrice());

                if (pointFlag) {
                    if (index == size) {
                    	//最后剩余的积分
                        BigDecimal lastPoint = soUsePoint.subtract(usedPoint);
                        sl.setTotalPointUsed(lastPoint);
                    } else {
                    	//按金额占比分摊积分
                        sl.setTotalPointUsed(sl.getTotalAmount().multiply(soUsePoint).divide(soAmountBeforeDiscount, 0, BigDecimal.ROUND_DOWN));
                        usedPoint = usedPoint.add(sl.getTotalPointUsed());
                    }
                    index++;
                } else {
                    sl.setTotalPointUsed(BigDecimal.ZERO);
                }
                // 计算订单行的最终行金额，与最终行单价
                sl.setVirtualAmount(sl.getTotalPointUsed().divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN));
                sl.setInvoiceTotalAmount(sl.getTotalAmountAfterDiscount().subtract(sl.getVirtualAmount()));
                sl.setInvoiceUnitPrice(sl.getInvoiceTotalAmount().divide(new BigDecimal(sl.getQuantity()), 2, BigDecimal.ROUND_DOWN));
                if (sl.getInvoiceUnitPrice().multiply(BigDecimal.valueOf(sl.getQuantity())).subtract(sl.getInvoiceTotalAmount()).doubleValue() > 10e-5) {
                    log.error("------- 平摊存在误差订单  1 -------");
                    log.error("platformOrderCode:" + soCmd.getPlatformOrderCode() + "  promotionAmt:" + soDiscountTotal.doubleValue());
                }
            }
        } else {
        	//订单有折扣
            BigDecimal usedFee = BigDecimal.ZERO;
            BigDecimal usedPoint = BigDecimal.ZERO;
            List<SalesOrderLineCommand> subList = new ArrayList<SalesOrderLineCommand>();
            for (SalesOrderLineCommand soLine : soLines) {
                if (soLine.getUnitPrice() == null || soLine.getUnitPrice().doubleValue() == 0) {
                    soLine.setLineDiscount(BigDecimal.ZERO);
                    soLine.setTotalDiscount(BigDecimal.ZERO);
                    soLine.setTotalAmountAfterDiscount(BigDecimal.ZERO);
                    soLine.setUnitPriceAfterDiscount(BigDecimal.ZERO);
                    soLine.setVirtualAmount(BigDecimal.ZERO);
                    soLine.setInvoiceTotalAmount(BigDecimal.ZERO);
                    soLine.setInvoiceUnitPrice(BigDecimal.ZERO);
                } else{
                    subList.add(soLine);

                }
            }

            // 按行的requestQty*unitPric升序排列
            Collections.sort(subList, new Comparator<SalesOrderLineCommand>() {
                public int compare(SalesOrderLineCommand sl0, SalesOrderLineCommand sl1) {
                    BigDecimal total0 = sl0.getUnitPrice().multiply(new BigDecimal(sl0.getQuantity()));
                    BigDecimal total1 = sl1.getUnitPrice().multiply(new BigDecimal(sl1.getQuantity()));
                    int flag = total0.compareTo(total1);
                    return flag;
                }
            });

            int size = subList.size();
            int index = 1;
            for (SalesOrderLineCommand sl : subList) {
                if (index == size) {
                	//最后一行特殊处理
                    BigDecimal slTotal = sl.getTotalAmount();
                    BigDecimal totalDiscount = soDiscountTotal.subtract(usedFee);
                    BigDecimal totalAmountAfterDiscount = slTotal.subtract(totalDiscount);
                    BigDecimal unitPriceAfterDiscount = totalAmountAfterDiscount.divide(new BigDecimal(sl.getQuantity()), 2, BigDecimal.ROUND_DOWN);
                    sl.setTotalAmountAfterDiscount(totalAmountAfterDiscount);
                    sl.setUnitPriceAfterDiscount(unitPriceAfterDiscount);
                    sl.setTotalDiscount(totalDiscount);

                    //分摊积分数
                    if (pointFlag) {
                        BigDecimal lastPoint = soUsePoint.subtract(usedPoint);
                        sl.setTotalPointUsed(lastPoint);
                    } else {
                        sl.setTotalPointUsed(BigDecimal.ZERO);
                    }
                } else {
                	//非最后一行通用处理
                    BigDecimal slTotal = sl.getTotalAmount();
                    // 行折前金额/折前总金额 * 总折扣 = 行折扣
                    BigDecimal totalDiscount = slTotal.multiply(soDiscountTotal).divide(soAmountBeforeDiscount, 2, BigDecimal.ROUND_DOWN);
                    BigDecimal totalAmountAfterDiscount = slTotal.subtract(totalDiscount);
                    BigDecimal unitPriceAfterDiscount = totalAmountAfterDiscount.divide(new BigDecimal(sl.getQuantity()), 2, BigDecimal.ROUND_DOWN);
                    sl.setTotalAmountAfterDiscount(totalAmountAfterDiscount);
                    sl.setUnitPriceAfterDiscount(unitPriceAfterDiscount);
                    sl.setTotalDiscount(totalDiscount);
                    usedFee = usedFee.add(totalDiscount);
                    
                    //分摊积分数
                    if (pointFlag) {
                        sl.setTotalPointUsed(slTotal.multiply(soUsePoint).divide(soAmountBeforeDiscount, 0, BigDecimal.ROUND_DOWN));
                        usedPoint = usedPoint.add(sl.getTotalPointUsed());
                    } else {
                        sl.setTotalPointUsed(BigDecimal.ZERO);
                    }
                }

                // 计算订单行的最终行金额，与最终行单价
                sl.setVirtualAmount(sl.getTotalPointUsed().divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN));
                sl.setInvoiceTotalAmount(sl.getTotalAmountAfterDiscount().subtract(sl.getVirtualAmount()));
                sl.setInvoiceUnitPrice(sl.getInvoiceTotalAmount().divide(new BigDecimal(sl.getQuantity()), 2, BigDecimal.ROUND_DOWN));
                index++;
                if (sl.getInvoiceUnitPrice().multiply(BigDecimal.valueOf(sl.getQuantity())).subtract(sl.getInvoiceTotalAmount()).doubleValue() > 10e-5) {
                    log.error("------- 平摊存在误差订单  2 -------");
                    log.error("platformOrderCode:" + soCmd.getPlatformOrderCode() + "  promotionAmt:" + soDiscountTotal.doubleValue());
                }
            }
        }
        
        //均摊完毕，检查开票金额合计是否为零
        BigDecimal invoiceSum = BigDecimal.ZERO;
        for(SalesOrderLineCommand bean : soCmd.getSoLineCommandList()){
        	invoiceSum = invoiceSum.add(bean.getInvoiceTotalAmount());
        }
        //如果金额为零，重置开票金额为零 <=0 fanht
        if(invoiceSum.compareTo(BigDecimal.ZERO)<=0){
        	for(SalesOrderLineCommand bean : soCmd.getSoLineCommandList()){
            	bean.setInvoiceTotalAmount(BigDecimal.ZERO);
            	bean.setInvoiceUnitPrice(BigDecimal.ZERO);
            }
        }
        
    }
	
	private String designatedWh(CompanyShop shop, List<SalesOrderLineCommand> solCmdList){
		
		SkuSplitType splitType = SkuSplitType.valueOf(shop.getSkuSplitType());
		String whCode = null;
    	if(splitType != null && splitType.getValue() == SkuSplitType.DESIGNATED_WH.getValue()){
    		
			//校验行分仓编码是否为混合仓
			Set<String> set = new HashSet<String>();
			for(SalesOrderLineCommand soLineLog: solCmdList){
				set.add(soLineLog.getPlatformWhCode());
			}
			
			if(set.size() > 1){
				throw new BusinessException(ErrorCode.CUSTOMIZATION_TIP, new Object[] {"根据前台指定仓库创单失败(不允许存在混合仓)!"});
			}
			whCode = set.toArray(new String[] {})[0];
		
			if (whCode == null) {
				ShopWh defauleShopWh = shopWhManager.getDefaultWarehouse(shop.getId());
				whCode = defauleShopWh.getWhCode();
			}else{
				boolean flag = true;
				List<ShopWh> whList = shopWhManager.findShopWhList(shop.getId());
				for(ShopWh wh: whList){
					if(whCode.equals(wh.getWhCode())){
						flag = false;
						break;
					}
				}
				if(flag){
					throw new BusinessException(ErrorCode.CUSTOMIZATION_TIP, new Object[] {"该仓库编码不存在oms系统中!"});
				}
			}
    	}
    	return whCode;
	}
	
	private void setOrderLineType(SalesOrderLine sl) {
		// 当订单行为平台订单，且销售单价(折前单价)为0时，默认设为原订单赠品
		if (sl.getOrderLineType() != null && OrderLineType.MAIN.getValue() == sl.getOrderLineType() && BigDecimal.ZERO.compareTo(sl.getUnitPrice()) == 0) {
			sl.setOrderLineType(OrderLineType.TMALL_PLATFORM_GIFT.getValue());
		}
	}
}
