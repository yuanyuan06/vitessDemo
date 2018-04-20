package io.vitess.service.so;

import com.jumbo.AppleConstants;
import com.jumbo.Constants;
import com.jumbo.dao.baseinfo.CareUserDao;
import com.jumbo.dao.sales.OrderMemberDao;
import com.jumbo.dao.sales.SalesOrderDao;
import com.jumbo.dao.sales.SoDeliveryInfoDao;
import com.jumbo.model.baseinfo.CareUser;
import com.jumbo.model.baseinfo.CompanyShop;
import com.jumbo.model.sales.OrderMember;
import com.jumbo.model.sales.SalesOrder;
import com.jumbo.model.sales.SalesOrderLine;
import com.jumbo.model.sales.SoDeliveryInfo;
import com.jumbo.model.sales.cond.SoSuspend;
import com.jumbo.model.sales.enums.SalesOrderStatus;
import com.jumbo.model.sales.enums.SalesOrderSuspendReasonType;
import com.jumbo.model.sales.enums.SoFinanceStatus;
import com.jumbo.model.sales.enums.SoSpecialType;
import com.jumbo.model.workflow.WorkTask;
import com.jumbo.tmalloms.manager.baseinfo.BranchWarehouseManager;
import com.jumbo.tmalloms.manager.baseinfo.SuspendSpecialSkuManager;
import com.jumbo.tmalloms.manager.platform.SuspendManager;
import com.jumbo.workflow.exception.WorkFlowException;
import com.jumbo.workflow.exception.WrongCurrentTaskNodeException;
import io.vitess.command.SoSuspend;
import io.vitess.constants.AppleConstants;
import io.vitess.dao.so.OrderMemberDao;
import io.vitess.dao.so.SalesOrderDao;
import io.vitess.dao.so.SoDeliveryInfoDao;
import io.vitess.enums.SoSpecialType;
import io.vitess.exception.WorkFlowException;
import io.vitess.model.base.WorkTask;
import io.vitess.model.mq.CompanyShop;
import io.vitess.model.so.OrderMember;
import io.vitess.model.so.SalesOrder;
import io.vitess.model.so.SalesOrderLine;
import io.vitess.model.so.SoDeliveryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("soManager")
public class SoManagerImpl implements SoManager {
	
    @Autowired
    private SalesOrderDao salesOrderDao;
    
//    @Autowired
//    private SalesOrderLineDao salesOrderLineDao;

    @Autowired
    private SoDeliveryInfoDao soDeliveryInfoDao;
    
    @Autowired
    private OrderMemberDao omDao;

    @Autowired
    private CareUserDao cuDao;
    
    @Autowired
    private SuspendSpecialSkuManager suspendSpecialSkuManager;
    
    @Autowired
    private SuspendManager suspendManager;
    
    //必须处理的挂起
	private Integer[] suspendTypes =  {null,15,10,1,9,11};
	
	private final static String PROVINCE= "上海,云南省,内蒙古自治区,北京,吉林省,四川省,天津,宁夏回族自治区,安徽省,山东省,山西省,广东省,广西壮族自治区,新疆维吾尔自治区,江苏省,江西省,河北省,河南省,浙江省,海南省,湖北省,湖南省,甘肃省,福建省,西藏自治区,贵州省,辽宁省,重庆,陕西省,青海省,黑龙江省";
    
//    @Autowired
//    private CloudStackSoTaskInfoDao cloudStackSoTaskInfoDao;
//    @Autowired
//    private CloudStackSoLogDao cloudStackSoLogDao;
//    @Autowired
//    private O2OServiceSoTaskInfoDao o2OServiceSoTaskInfoDao;
//    @Autowired
//    private CompanyShopCloudStackTransportatorRefDao companyShopCloudStackTransportatorRefDao;

	@Override
	public Boolean getOrderAutoToWh(CompanyShop shop, String transExpCode, SoSpecialType soSpecialType) {
		//apple订单不会挂起fanht
		if(shop.getId().equals(AppleConstants.APPLE_SHOP_ID) || SoSpecialType.isWlbOrder(soSpecialType)){
			return Boolean.TRUE;
		}
		Boolean result = Boolean.FALSE;
		//订单自动过仓、物流商必须得有值
		boolean isRequiredTrans = shop.getIsRequiredTrans() == null ? false : shop.getIsRequiredTrans();
		boolean canAutoCommit = isRequiredTrans ? StringUtils.hasText(transExpCode) : true;
		if(shop.getIsAutoCommit() && canAutoCommit){
			result = Boolean.TRUE;
		}
		return result;
	}

	@Override
	public Boolean needSuspend(WorkTask task, Integer nodeNo)throws WorkFlowException {
		//当前节点判断
        if (task.getCurrentNodeNo() != nodeNo) {
            throw new WrongCurrentTaskNodeException(task.getWorkFlow().getCode(), task.getTaskNo(), nodeNo, task.getCurrentNodeNo());
        }
        SalesOrder so = salesOrderDao.findSoByCodeShopId(task.getRefSlipCode(), task.getRefSlipShopId());
        SoDeliveryInfo sodi = soDeliveryInfoDao.findBySoIdShopId(so.getId(), task.getRefSlipShopId());
        OrderMember om = omDao.findBySoId(so.getId(), task.getRefSlipShopId());
        CompanyShop shop = so.getCompanyShop();
        List<SalesOrderLine> soLine = so.getSalesOrderLineList();
        
        // 商品模式打标	验证店铺开关是否开启
        Boolean isApplySalesMode = shop.getIsApplySalesMode();
        if(isApplySalesMode != null && isApplySalesMode){
        	Boolean signStatus = suspendManager.signSoLineSaleModel(soLine, shop);
        	if(!signStatus){
        		//so.setSuspendReasonType(SalesOrderSuspendReasonType.SALES_MODE.getValue());
        		salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.SALES_MODE.getValue());
        		return Boolean.TRUE;
        	}
        }
        
        SoSuspend supend = this.suspendCheck(shop, so, soLine, sodi, om);
        if(supend.getIsSuspend()){
        	
        	salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), supend.getSuspendReasonType());
        }
        
        return supend.getIsSuspend();
	}
	
	@Override
	public Boolean suspendCheck(WorkTask task, Integer nodeNo, SalesOrder so, List<SalesOrderLine> slList, SoDeliveryInfo sdi, OrderMember om)throws WorkFlowException {
		//当前节点判断
        if (task.getCurrentNodeNo() != nodeNo) {
            throw new WrongCurrentTaskNodeException(task.getWorkFlow().getCode(), task.getTaskNo(), nodeNo, task.getCurrentNodeNo());
        }

        CompanyShop shop = so.getCompanyShop();
        //自动过仓订单直接跳过fanht	//apple订单不会挂起fanht//物流宝订单不会挂起fanht
        if((so.getIsAutoWh()!=null&&so.getIsAutoWh())||shop.getId().equals(AppleConstants.APPLE_SHOP_ID)||SoSpecialType.isWlbOrder(so.getSpecialType())){
            return Boolean.FALSE;
        }

       // 商品模式打标	验证店铺开关是否开启
        Boolean isApplySalesMode = shop.getIsApplySalesMode();
        if(isApplySalesMode != null && isApplySalesMode){
        	Boolean signStatus = suspendManager.signSoLineSaleModel(slList, shop);
        	if(!signStatus){
        		//so.setSuspendReasonType(SalesOrderSuspendReasonType.SALES_MODE.getValue());
        		salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.SALES_MODE.getValue());
        		return Boolean.TRUE;
        	}
        }
        
//      /** 周期购订单预计到货时间-6天>当前日期（ 此类订单不可手动提交，仅由系统到达时间后自动提交） **/
//      if (so.getIsCycle() != null && Constants.YES.intValue() == so.getIsCycle().intValue()) {
//          Date estArrivalTimeDate = DateUtil.parseDate(DateUtil.formatDate(so.getEstArrivalTime(), DateUtil.PATTERN_SIMPLE), DateUtil.PATTERN_SIMPLE);
//          Date nowDate = DateUtil.parseDate(DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_SIMPLE), DateUtil.PATTERN_SIMPLE);
//          if (so.getEstArrivalTime() == null || DateUtil.addDays(estArrivalTimeDate, -6).compareTo(nowDate) > 0) {
//              so.setSuspendReasonType(SalesOrderSuspendReasonType.PURCHASE_CYCLE_ORDER.getValue());
//              return Boolean.TRUE;
//          }
//      }

        //SoDeliveryInfo sdi = this.soDeliveryInfoDao.findBySoIdShopId(so.getId(), shop.getId());
        //必须处理的挂起
    	//电子券实物订单挂起fanht/复制的订单不用再挂起fanht/已获取送货地址的订单不用挂起
    	if(checkEticketActualOrder(so,sdi)){
    		//so.setSuspendReasonType(SalesOrderSuspendReasonType.ETICKET_ACTUAL_ORDER.getValue());
    		salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.ETICKET_ACTUAL_ORDER.getValue());
			return Boolean.TRUE;
    	}
    	
        /**地址需要翻译**/
        if (shop != null && shop.getNeedVerifyEnAddress() != null && shop.getNeedVerifyEnAddress()) {
            if (sdi != null && isEmptyWithProvinceAndCityAndDistinctForEnglish(sdi.getProvinceEn(), sdi.getCityEn(), sdi.getDistrictEn(), sdi.getAddressEn())) {
                //so.setSuspendReasonType(SalesOrderSuspendReasonType.EN_PROVINCE_CITY_DISTINECT_IS_EMPTY.getValue());
                salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.EN_PROVINCE_CITY_DISTINECT_IS_EMPTY.getValue());
                return Boolean.TRUE;
            }
        }

        /** 省市区为空 **/
        if (isEmptyWithProvinceAndCityAndDistinct(sdi.getProvince(), sdi.getCity(), sdi.getDistrict(), sdi.getAddress())) {
            //so.setSuspendReasonType(SalesOrderSuspendReasonType.PROVINCE_CITY_DISTINECT_IS_EMPTY.getValue());
            salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.PROVINCE_CITY_DISTINECT_IS_EMPTY.getValue());
            return Boolean.TRUE;
        }

        /** O2O订单属于混合仓 **/
		if (SoSpecialType.isO2oOrder(so.getSpecialType()) && StringUtils.hasText(so.getMainBranchWhCode()) && BranchWarehouseManager.MIX_WAREHOUSE_CODE.equals(so.getMainBranchWhCode())) {
			//so.setSuspendReasonType(SalesOrderSuspendReasonType.O2O_MIX_WAREHOUSE.getValue());
			salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.O2O_MIX_WAREHOUSE.getValue());
			return Boolean.TRUE;
		}
		
    	//必须处理的挂起/新建状态加进去fanht
        if (Arrays.asList(suspendTypes).contains(so.getSuspendReasonType()) || SalesOrderStatus.CREATED.equals(so.getOrderStatus())) {
    		//特殊关怀订单判断fanht
            if (checkIsCareUser(so, shop, sdi, om)&&!Integer.valueOf(SalesOrderSuspendReasonType.CARE_ORDER.getValue()).equals(so.getSuspendReasonType())) {
                return Boolean.TRUE;
            }
        	
            /** 含特殊商品挂起 **/
    		if (checkIsSuspendForSpecialSku(shop, so, slList)) {
    			//so.setSuspendReasonType(SalesOrderSuspendReasonType.SO_CONTAIN_SPECIAL_SKU.getValue());
    			salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.SO_CONTAIN_SPECIAL_SKU.getValue());
    			return Boolean.TRUE;
    		}

            /** 港澳台订单 **/
            if (isHkOrMoOrTwSalesOrder(sdi.getProvince())) {
                //so.setSuspendReasonType(SalesOrderSuspendReasonType.HK_TW_MC_ORDER.getValue());
                salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.HK_TW_MC_ORDER.getValue());
                return Boolean.TRUE;
            }

            /** 自动过仓至ERP且有备注且不忽略备注 fanht**/
            if (shop != null && shop.getIsAutoCommit() != null && shop.getIsIgnoreMemo() != null && shop.getIsAutoCommit() && !shop.getIsIgnoreMemo() && 
            		(StringUtils.hasText(so.getBuyerMemo())|| StringUtils.hasText(so.getSellerMemo()))) {
                //so.setSuspendReasonType(SalesOrderSuspendReasonType.MEMO_SO.getValue());
                salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.MEMO_SO.getValue());
                return Boolean.TRUE;
            }

            /** 复制产生的新单 **/
            if (StringUtils.hasText(so.getCopySourceOmsOrderCode())) {
                //so.setSuspendReasonType(SalesOrderSuspendReasonType.COPY_SO.getValue());
                salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.COPY_SO.getValue());
                return Boolean.TRUE;
            }

            /** 拆单产生的新单 **/
            if (StringUtils.hasText(so.getSplitSourceOmsOrderCode())) {
                //so.setSuspendReasonType(SalesOrderSuspendReasonType.SPLIT_SO.getValue());
                salesOrderDao.updateSuspendReasonType(so.getId(), shop.getId(), SalesOrderSuspendReasonType.SPLIT_SO.getValue());
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
	}
	
	/**
	 * 关怀订单判断
	 * @param so
	 * @return
	 */
	private Boolean checkIsCareUser(SalesOrder so, CompanyShop shop, SoDeliveryInfo sodi, OrderMember om){
		//OrderMember om = omDao.findBySoId(so.getId(), shop.getId());
        //SoDeliveryInfo sodi = soDeliveryInfoDao.findBySoIdShopId(so.getId(), shop.getId());
        List<CareUser> list = cuDao.findCareUserList();
        
        int careGrade = 0;
        for (CareUser bean : list) {
            StringBuilder careMomo = new StringBuilder();
            // 用户
            if (StringUtils.hasText(om.getPlatformMemberCode()) && om.getPlatformMemberCode().equals(bean.getNikiName())) {
                careGrade++;
                careMomo.append("用户名;");
            }
            // 手机
            if (StringUtils.hasText(sodi.getReceiverMobile()) && sodi.getReceiverMobile().equals(bean.getMobile())) {
                careGrade++;
                careMomo.append("手机;");
            }
            // 电话
            if (StringUtils.hasText(sodi.getReceiverPhone()) && sodi.getReceiverPhone().equals(bean.getPhone())) {
                careGrade++;
                careMomo.append("电话;");
            }
            // 地址
            if (StringUtils.hasText(sodi.getAddress()) && sodi.getAddress().equals(bean.getAddress())) {
                careGrade++;
                careMomo.append("地址;");
            }
            if(careGrade>0){
                // 收货人
                if (StringUtils.hasText(sodi.getReceiver()) && sodi.getReceiver().equals(bean.getReceiver())) {
                    careGrade++;
                    careMomo.append("收货人;");
                }
            }
            
        }
        
        if (careGrade > 0) {
            return Boolean.TRUE;
        }else{
        	return Boolean.FALSE;
        }
        
	}
	
    /**
     * 特殊商品挂起
     *
     * @methodName com.jumbo.workflow.taskfactory.impl.SalesOrderTaskFactoryImpl.checkIsSuspendForSpecialSku
     * @author hailiang.jiang
     * @date 2014年11月1日 上午11:40:44
     * @param shop
     * @param salesOrderLineList
     * @return
     */
    private boolean checkIsSuspendForSpecialSku(CompanyShop shop, SalesOrder so, List<SalesOrderLine> salesOrderLineList) {
    	if (shop.getIsSuspendForSpecialSku() == null || !shop.getIsSuspendForSpecialSku()) {
    		return false;
    	}
    	
//    	//List<SalesOrderLine> salesOrderLineList = this.salesOrderLineDao.querySalesOrderLineBySoIdShopId(so.getId(), shop.getId());
//    	List<String> extCodeList = getExtCodeList(salesOrderLineList);
//    	List<SuspendSpecialSkuCommand> suspendSpecialSkuCmdList = suspendSpecialSkuManager.findSuspendSpecialSkuList(shop.getId(), extCodeList);
//    	if (suspendSpecialSkuCmdList.isEmpty()) {
//    		return false;
//    	}
    	
    	//配置挂起的商品
    	List<String> maExtCode = suspendSpecialSkuManager.findSuspendSpecialSkuListByShopId(shop.getId());
    	if(maExtCode.size()==0){
    		return false;
    	}
    	//订单行商品
    	List<String> orderExtCode = getExtCodeList(salesOrderLineList);
    	for(String bean : orderExtCode){
    		for(String each : maExtCode){
    			if(each.equals(bean)){
    				return true;
    			}
    		}
    	}
    	
		return false;
	}
    
    private List<String> getExtCodeList(List<SalesOrderLine> salesOrderLineList) {
    	List<String> result = new ArrayList<String>();
    	if (salesOrderLineList != null) {
    		for (SalesOrderLine line : salesOrderLineList) {
        		result.add(line.getExtentionCode());
        	}
    	}
    	return result;
    }
    
    /**
     * 验证省市区是否为空
     * 
     * @param province
     * @param city
     * @param distinct
     * @return
     */
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

    /**
     * 验证英文送货地址
     * 
     * @param province
     * @param city
     * @param distinct
     * @return
     */
    private boolean isEmptyWithProvinceAndCityAndDistinctForEnglish(String provinceEn, String cityEn, String distinctEn, String addressEn) {
        if (!StringUtils.hasText(provinceEn)) {
            return true;
        }

        if (!StringUtils.hasText(cityEn)) {
            return true;
        }

        if (!StringUtils.hasText(distinctEn)) {
            return true;
        }

        if (!StringUtils.hasText(addressEn)) {
            return true;
        }

        return false;
    }

    /**
     * 验证  店铺海外订单配置是否开启和是否为海外订单  add by chenping 20170607 
     * @param province
     * @return
     */
    private boolean isHangByAbroadOrder(String province, CompanyShop shop) {
        if (!SoManagerImpl.PROVINCE.contains(province) && shop.getIsHangByAbroadOrder()) {
            return true;
        }
        return false;
    }
    
    /**
     * 港澳台订单
     * 
     * @param province
     * @return
     */
    private boolean isHkOrMoOrTwSalesOrder(String province) {
        if (province != null && (province.contains(Constants.PROVINCE_HK) || province.contains(Constants.PROVINCE_MO) || province.contains(Constants.PROVINCE_TW))) {
            return true;
        }
        return false;
    }
    
    /**
     * 电子券实物订单挂起判断
     * @param so
     * @param sdi
     * @return
     */
    private boolean checkEticketActualOrder(SalesOrder so,SoDeliveryInfo sdi){
    	//电子券实物订单&&没有获取到外部地址fanht
    	if(SoSpecialType.ETICKET_ACTUAL_ORDER.equals(so.getSpecialType())&&!StringUtils.hasText(sdi.getCity())&&!StringUtils.hasText(sdi.getDistrict())){
			return true;
    	}
		return false;
    }

	@Override
	public SoSuspend suspendCheck(CompanyShop shop, SalesOrder so, List<SalesOrderLine> slList, SoDeliveryInfo sdi, OrderMember om){
		SoSuspend suspend = new SoSuspend();
		//xin.feng 预售订单挂起
        if (SoSpecialType.PRE_SALES_ORDER.equals(so.getSpecialType()) && !SoFinanceStatus.FULLPAYMENT.equals(so.getFinanceStatus())) {
        	suspend.setIsSuspend(true);
        	suspend.setSuspendReasonType(SalesOrderSuspendReasonType.PRE_SALES_ORDER.getValue());
        	return suspend;
		}		
		
        //自动过仓订单直接跳过fanht	//apple订单不会挂起fanht//物流宝订单不会挂起fanht//ag拆单不挂起
        if((so.getIsAutoWh()!=null&&so.getIsAutoWh())||shop.getId().equals(AppleConstants.APPLE_SHOP_ID)||SoSpecialType.isWlbOrder(so.getSpecialType())||Constants.AGAUTO.equals(so.getSubOrderSource())){
            suspend.setIsSuspend(Boolean.FALSE);
            return suspend;
        }
        
       // 商品模式打标	验证店铺开关是否开启
        Boolean isApplySalesMode = shop.getIsApplySalesMode();
        if(isApplySalesMode != null && isApplySalesMode){
        	//判断每行是否打标成功fanht
        	for(SalesOrderLine bean : slList){
        		if(bean.getSalesModel()==null){
        			
        			suspend.setIsSuspend(Boolean.TRUE);
        			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.SALES_MODE.getValue());
            		return suspend;
        		}
        	}
        	
        }

        //必须处理的挂起
    	//电子券实物订单挂起fanht/复制的订单不用再挂起fanht/已获取送货地址的订单不用挂起
    	if(checkEticketActualOrder(so,sdi)){
    		
			suspend.setIsSuspend(Boolean.TRUE);
			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.ETICKET_ACTUAL_ORDER.getValue());
    		return suspend;
    	}
    	
        /**地址需要翻译**/
        if (shop != null && shop.getNeedVerifyEnAddress() != null && shop.getNeedVerifyEnAddress()) {
            if (sdi != null && isEmptyWithProvinceAndCityAndDistinctForEnglish(sdi.getProvinceEn(), sdi.getCityEn(), sdi.getDistrictEn(), sdi.getAddressEn())) {

    			suspend.setIsSuspend(Boolean.TRUE);
    			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.EN_PROVINCE_CITY_DISTINECT_IS_EMPTY.getValue());
        		return suspend;
            }
        }

        /** 省市区为空 **/
        if (isEmptyWithProvinceAndCityAndDistinct(sdi.getProvince(), sdi.getCity(), sdi.getDistrict(), sdi.getAddress())) {
        	
			suspend.setIsSuspend(Boolean.TRUE);
			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.PROVINCE_CITY_DISTINECT_IS_EMPTY.getValue());
    		return suspend;
        }

        /** O2O订单属于混合仓 **/
		if (SoSpecialType.isO2oOrder(so.getSpecialType()) && StringUtils.hasText(so.getMainBranchWhCode()) && BranchWarehouseManager.MIX_WAREHOUSE_CODE.equals(so.getMainBranchWhCode())) {
			
			suspend.setIsSuspend(Boolean.TRUE);
			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.O2O_MIX_WAREHOUSE.getValue());
    		return suspend;
		}
		//海外订单是否挂起 add by chenping 20170607 begin
		if (isHangByAbroadOrder(sdi.getProvince(),shop)) {
			
			suspend.setIsSuspend(Boolean.TRUE);
			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.HANG_BY_ABROAD_ORDER.getValue());
    		return suspend;
		}
		//海外订单是否挂起 add by chenping 20170607 end 
		
    	//必须处理的挂起/新建状态加进去fanht
        if (Arrays.asList(suspendTypes).contains(so.getSuspendReasonType()) || SalesOrderStatus.CREATED.equals(so.getOrderStatus())) {
    		//特殊关怀订单判断fanht
            if (checkIsCareUser(so, shop, sdi, om)&&!Integer.valueOf(SalesOrderSuspendReasonType.CARE_ORDER.getValue()).equals(so.getSuspendReasonType())) {
                
                suspend.setIsSuspend(Boolean.TRUE);
    			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.CARE_ORDER.getValue());
        		return suspend;
            }
        	
            /** 含特殊商品挂起 **/
    		if (checkIsSuspendForSpecialSku(shop, so, slList)) {

                suspend.setIsSuspend(Boolean.TRUE);
    			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.SO_CONTAIN_SPECIAL_SKU.getValue());
        		return suspend;
    		}

    		//-----------add by chenping 注释掉 非大陆订单自动挂起逻辑 取缔 begin
            /** 港澳台订单 *
            if (isHkOrMoOrTwSalesOrder(sdi.getProvince())) {

                suspend.setIsSuspend(Boolean.TRUE);
    			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.HK_TW_MC_ORDER.getValue());
        		return suspend;
            }*/
          //-----------add by chenping 注释掉 非大陆订单自动挂起逻辑 取缔 end
            
            /** 自动过仓至ERP且有备注且不忽略备注 fanht**/
            if (shop != null && shop.getIsAutoCommit() != null && shop.getIsIgnoreMemo() != null && shop.getIsAutoCommit() && !shop.getIsIgnoreMemo() && 
            		(StringUtils.hasText(so.getBuyerMemo())|| StringUtils.hasText(so.getSellerMemo()))) {

                suspend.setIsSuspend(Boolean.TRUE);
    			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.MEMO_SO.getValue());
        		return suspend;
            }

            /** 复制产生的新单 **/
            if (StringUtils.hasText(so.getCopySourceOmsOrderCode())) {

                suspend.setIsSuspend(Boolean.TRUE);
    			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.COPY_SO.getValue());
        		return suspend;
            }

            /** 拆单产生的新单 **/
            if (StringUtils.hasText(so.getSplitSourceOmsOrderCode())) {
                
                suspend.setIsSuspend(Boolean.TRUE);
    			suspend.setSuspendReasonType(SalesOrderSuspendReasonType.SPLIT_SO.getValue());
        		return suspend;
            }
        }

        suspend.setIsSuspend(Boolean.FALSE);
        return suspend;
	
	}

	@Override
    public int  checkHandCreateOrder(String platformOrderCode, Long shopId) {
        List<Integer> salesOrderList = salesOrderDao.queryCheckHandCreateOrder(platformOrderCode,shopId);
        if (salesOrderList.size() != 0){
            return 0;
        }
        return 1;
    }
}