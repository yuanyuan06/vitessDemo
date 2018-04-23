package io.vitess.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baozun.pacssi.entity.order.*;
import io.vitess.constants.SysWmsStatus;
import io.vitess.enums.*;
import io.vitess.model.so.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class OrderUtil {
    
    /**
     * 完整转换订单行信息
     * 
     * @author 李光辉
     * @date 2014年7月26日 下午5:00:00
     * @param line
     * @param promList
     * @param packList
     * @return
     * @since
     */
    public static BzOrderLine toBzOrderLine(SalesOrderLine line, List<PlatformPromotion> promList, List<SalesOrderLinePackage> packList) {
        BzOrderLine bz = toBzOrderLine(line);
        if (promList != null && promList.size() > 0) {
            List<BzPlatformPromotion> pfPromotions = new ArrayList<BzPlatformPromotion>(promList.size());
            for (PlatformPromotion pp : promList) {
                pfPromotions.add(toBzPlatformPromotion(pp));
            }
            bz.setPfPromotions(pfPromotions);
        }
        if (packList != null && packList.size() > 0) {
            List<BzProductPackage> productPackages = new ArrayList<BzProductPackage>(packList.size());
            for (SalesOrderLinePackage pack : packList) {
                productPackages.add(toBzProductPackage(pack));
            }
            bz.setProductPackages(productPackages);
        }
        
        return bz;
    }
    
    /**
     * 完整转换订单信息
     * 
     * @author 李光辉
     * @date 2014年7月26日 下午5:00:15
     * @param so
     * @param orderLines
     * @param promList
     * @param packList
     * @param payList
     * @param mem
     * @param deli
     * @return
     * @since
     */
    public static BzOrder toBzOrder(SalesOrder so, List<BzOrderLine> orderLines,
                                    List<PlatformPromotion> promList, List<SalesOrderLinePackage> packList,
                                    List<SalesOrderPaymentInfo> payList, OrderMember mem, SoDeliveryInfo deli, List<SoServiceLine> soServiceLineList) {
    	so.setSoDeliveryInfo(deli);
        BzOrder bz = toBzOrder(so);
        bz.setOrderLines(orderLines);
        if (promList != null && promList.size() > 0) {
            List<BzPlatformPromotion> pfPromotions = new ArrayList<BzPlatformPromotion>(promList.size());
            for (PlatformPromotion pp : promList) {
                pfPromotions.add(toBzPlatformPromotion(pp));
            }
            bz.setPromotions(pfPromotions);
        }
        if (packList != null && packList.size() > 0) {
            List<BzProductPackage> productPackages = new ArrayList<BzProductPackage>(packList.size());
            for (SalesOrderLinePackage pack : packList) {
                productPackages.add(toBzProductPackage(pack));
            }
            bz.setProductPackageInfos(productPackages);
        }
        if (payList != null && payList.size() > 0) {
            List<BzPaymentInfo> paymentInfos = new ArrayList<BzPaymentInfo>(payList.size());
            for (SalesOrderPaymentInfo pay : payList) {
                paymentInfos.add(toBzPaymentInfo(pay, so));
            }
            bz.setPaymentInfos(paymentInfos);
        }
        bz.setOrderMember(toBzOrderMemeber(mem));
        bz.setDeliveryInfo(toBzDeliveryInfo(so, deli));
        bz.setTotalServiceFee((so.getInstallFee() == null ? BigDecimal.ZERO : so.getInstallFee()).add(so.getHomeDeliveryFee() == null ? BigDecimal.ZERO : so.getHomeDeliveryFee()));
        bz.setOrderServiceLines(toBzOrderServiceLine(so, soServiceLineList));
        
        return bz;
    }
    
    public static BzOrder toBzOrder(SalesOrder so) {
        BzOrder bz = new BzOrder();
        // 企业税号
        bz.setBuyerRegisterNo(so.getBuyerTaxNO());
        bz.setBusinessType(so.getBusinessType());
        bz.setOmsTradeCode(so.getPlatformOrderCode());
        bz.setOmsOrderCode(so.getOmsOrderCode());
        bz.setPfOrderCode(so.getPlatformOrderCodeN());
        bz.setPfOrderCodeSource(so.getPlatformOrderCode());
        bz.setOrderType(so.getOrderType().getValue());
        bz.setBzShopCode(so.getCompanyShop().getInnerShopCode());
        bz.setPfCreateTime(so.getPlatformCreateTime());
        bz.setTotalActualBfDisc(so.getAmountBeforeDiscount());
        bz.setTotalActualAfDisc(so.getAmountAfterDiscount());
        bz.setAcutalTransFee(so.getTransFee());
        bz.setCodAmt(so.getAmountAfterDiscount());
        
        bz.setOrderDiscFee(so.getHeadDiscount()); 
        bz.setProductDiscFee(so.getLinesDiscount());
        bz.setTotalDiscFee(so.getDiscountTotal());
        
        bz.setPayDiscountFee(so.getPaymentDiscount());
        bz.setUsedInnerPoint(so.getTotalInnerPoint());
        bz.setUsedOuterPoint(so.getTotalOuterPoint());
        bz.setUsedTotalPoint(so.getTotalInnerPoint().add(so.getTotalOuterPoint()));
        bz.setUsedInnerPointAmt(so.getTotalInnerPoint().multiply(Constants.POINT_PRICE).setScale(4)); 
        bz.setUsedOuterPointAmt(so.getTotalOuterPoint().multiply(Constants.POINT_PRICE).setScale(4));
        bz.setUsedTotalPointAmt(bz.getUsedInnerPointAmt().add(bz.getUsedOuterPointAmt()).setScale(4)); 
        
        bz.setVirtualAmt(so.getVirtualAmount());
        bz.setMainPaymentType(so.getMainPaymentType().getValue());
        // 给到pacs 的买家备注包含订单备注& 定制信息
        bz.setBuyerMemo((so.getCustomizationMemo() == null? "": so.getCustomizationMemo()) + (so.getBuyerMemo() == null? "": so.getBuyerMemo()));
        // 给到pacs 的卖家备注包含订单备注& 定制信息
        bz.setSellerMemo((so.getCustomizationMemo() == null? "": so.getCustomizationMemo()) + (so.getSellerMemo() == null? "": so.getSellerMemo()));
        bz.setIsNeededInvoice(integerToBoolean(so.getNeedInvoice()));        
        bz.setIsBillingManual(integerToBoolean(so.getIsBillingManual()));
        
        //发票类型 1:普通商业零售发票、2:增值税专用发票、3:电子发票
//        if (so.getInvoiceType() != null) {
//            bz.setInvoiceType(so.getInvoiceType().getValue());
//        }
//        if(so.getInvoiceKind()){
//        	bz.setInvoiceType(3);
//        }
        if(so.getInvoiceType()!=null && so.getInvoiceType().getValue()==1 && Boolean.FALSE.equals(so.getInvoiceKind())){
        	bz.setInvoiceType(1);
        }else if(so.getInvoiceType()!=null && so.getInvoiceType().getValue()==2){
        	bz.setInvoiceType(2);
        }else if(so.getInvoiceType()!=null && so.getInvoiceType().getValue()==1 && Boolean.TRUE.equals(so.getInvoiceKind())){
        	bz.setInvoiceType(3);
        }
        
        bz.setInvoiceTitle(so.getInvoiceTitle());
        bz.setInvoiceMemo(so.getInvoiceMemo());
        bz.setInvoiceContent(so.getInvoiceContent());
        
        bz.setVaTaxBankCard(so.getVaTaxBankCard());
        bz.setVaTaxBankName(so.getVaTaxBankName());
        bz.setVaTaxCompanyName(so.getVaTaxCompanyName());
        bz.setVaTaxRegisteredAddress(so.getVaTaxRegisterAddress());
        bz.setVaTaxTaxCode(so.getVaTaxCode());
        bz.setVaTaxTellphone(so.getVaTaxTelephone());
        bz.setTerminalSource(so.getTerminalSource());
        bz.setActivitySource(so.getActivitySource());
        bz.setSpecialType(Integer.toString(SoSpecialType.convertToPacsSoSpecialType(so.getSpecialType())));
        bz.setIsO2oOrder(SoSpecialType.isO2oOrder(so.getSpecialType()));
        bz.setMainBranchWhCode(so.getMainBranchWhCode());
        //非直连（到pac）、直连：bzWms、奇门 fanhtso
        //如果是ag拆单并且是已经取消的订单，发送到pac的时候直接创取消状态的单子 sunshanshan
        if (Constants.AGAUTO.equals(so.getSubOrderSource()) && SalesOrderStatus.CANCELED.getValue() == so.getOrderStatus().getValue()) {
        	bz.setIsDirectWmsOrder(Boolean.TRUE);
        	bz.setStatementType(2);//无效的订单
        	bz.setIsNeededInvoice(Boolean.FALSE);//不需要发票信息
        } else if(so.getIsDirectWmsOrder()== SysWmsStatus.UNWMS){
        	bz.setIsDirectWmsOrder(Boolean.FALSE);
        }else{
        	bz.setIsDirectWmsOrder(Boolean.TRUE);
        }
        Map<String, Object> map = new HashMap<String, Object>(3);
        map.put("vmiPromotionCode", so.getVmiPromotionCode());
        map.put("posSales", so.getPosSales());
        map.put("storeCode", so.getSoDeliveryInfo() == null ? "" : so.getSoDeliveryInfo().getStoreCode());
        map.put("cancelReason", so.getIsCycle().toString());
        bz.setExtProp1(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
        
        bz.setMainPaymentAcc(so.getPayAccount());
        
        bz.setExtVc1(so.getExtVc1());
        bz.setExtVc2(so.getExtVc2());
        bz.setExtVc3(so.getExtVc3());
        bz.setAlipayId(so.getAlipayId() == null ? "" : so.getAlipayId().toString());
        bz.setCreditCardFee(so.getCreditCardFee());
        
        bz.setOrderEnterType(resolveOrderEnterType(so));
        bz.setGoodsType(resolveGoodsType(so));
        
        //是否自动过仓 fanht 
        bz.setIsAutoCommitWh(so.getIsAutoWh());
        //平台付款时间 fanht
        if(so.getPlatformPaymentTime()!=null){
            bz.setPlatformPaymentTime(so.getPlatformPaymentTime());
        }else{
            bz.setPlatformPaymentTime(so.getPlatformCreateTime());
        }
        //仓库编码 fanht
        bz.setWhCode(so.getWarehouseCode());
        //是否开具发票明细 kuan.liu
        if(so.getIsBillingInvoiceDetail() != null){
        	bz.setIsBillingInvoiceDetail(so.getIsBillingInvoiceDetail() == 1 ? true:false);
        }
        bz.setExpensesTax(so.getOrderTaxFee());
        
        return bz;
    }
    
    // 1/null:接口抓单,2:拆单新订单,3:复制新订单
    public static Integer resolveOrderEnterType(SalesOrder so) {
        if (StringUtils.isNotBlank(so.getRaOmsOrderCode())) {
            return null;
        }
        
        if (StringUtils.isNotBlank(so.getCopySourceOmsOrderCode())) {
            return 3;
        }
        
        if (StringUtils.isNotBlank(so.getSplitSourceOmsOrderCode())) {
            return 2;
        }
        
        return 1;
    }
    
    // 1 实物商品订单,2:虚拟商品订单（无库存）,3:虚拟商品订单（有库存）
    public static SoGoodsType resolveGoodsType(SalesOrder so) {
        if (so.getSpecialType().equals(SoSpecialType.AUTO_DELIVERY_ORDER)) {
            return SoGoodsType.SO_GOODS_TYPE_XN;
        } else if (so.getSpecialType().equals(SoSpecialType.AUTO_DELIVERY_INV_ORDER)){
        	return SoGoodsType.SO_GOODS_TYPE_XN_INV;
        } else {
        	return SoGoodsType.SO_GOODS_TYPE_SJ;
        }
    }
    
    public static BzOrderLine toBzOrderLine(SalesOrderLine line) {
        BzOrderLine bz = new BzOrderLine();
        bz.setPlatformLineId(line.getPlatformOrderLineCode());
        bz.setSkuCode(line.getSkuCode());
        bz.setQty(line.getQuantity());
        bz.setListPrice(line.getListPrice());
        bz.setUnitPrice(line.getUnitPrice());
        bz.setPriceAfDiscount(line.getUnitPriceAfterDiscount());
        bz.setTotalBfDisc(line.getTotalAmount());
        bz.setTotalAfDisc(line.getTotalAmountAfterDiscount());
        bz.setProductDiscFee(line.getLineDiscount());
        bz.setDiscountFeeTotal(line.getTotalDiscount());
        bz.setAssignDiscFee(line.getTotalDiscount().subtract(line.getLineDiscount()));
        bz.setVirtualAmt(line.getVirtualAmount());
        bz.setInvoicePrice(line.getInvoiceUnitPrice());
        bz.setInvoiceTotal(line.getInvoiceTotalAmount());
        bz.setLineType(line.getOrderLineType().getValue());
        bz.setWarrantyMonths(line.getWarrantyMonths());
        bz.setBranchWhCode(line.getPlatformWhCode());
        bz.setPfSkuName(line.getPlatformSkuName());
        // activitySource[平台活动来源] fanht
        bz.setActivitySource(line.getActivitySource());
        
        Map<String, Object> map = new HashMap<String, Object>(3);
        map.put("vmiDiscountCode", line.getVmiDiscountCode());
        map.put("posSales", line.getPosSales());
        map.put("skuComboRemark", line.getSkuComboRemark());
        map.put("comboSkuCode", line.getComboSkuExtCodeOrgin());
        bz.setExtProp1(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
        
        bz.setOmsLineId(line.getId().toString());
        
        //天猫图片信息同步给pacs picPath
        bz.setPicPath(line.getPicPath());
        //订单行销售模式  yy
        bz.setSalesModel(line.getSalesModel());
        bz.setExpensesTax(line.getSubOrderTaxFee());
        return bz;
    }

    public static BzDeliveryInfo toBzDeliveryInfo(SalesOrder so, SoDeliveryInfo deli) {
        BzDeliveryInfo bz = new BzDeliveryInfo();
        bz.setCountry(deli.getCountry());
        bz.setCountryExt(deli.getCountryEn());

        bz.setProvince(deli.getProvince());
        bz.setProvinceExt(deli.getProvinceEn());

        bz.setCity(deli.getCity());
        bz.setCityExt(deli.getCityEn());

        bz.setDistrict(deli.getDistrict());
        bz.setDistrictExt(deli.getDistrictEn());

        bz.setAddress(deli.getAddress());
        bz.setAddressExt(deli.getAddressEn());

        bz.setZipCode(deli.getZipcode());
        bz.setZipCodeExt(deli.getZipcodeEn());

        bz.setReceiver(deli.getReceiver());
        bz.setReceiverExt(deli.getReceiverEn());

        bz.setReceiverPhone(deli.getReceiverPhone());
        bz.setReceiverMobile(deli.getReceiverMobile());
        //BooleanUtils.isTrue(so.getIsDirectWmsOrder())
        bz.setTransportatorCode(so.getIsDirectWmsOrder()==SysWmsStatus.BZWMS ? deli.getActualTransExpCode() : deli.getTransExpCode());
        bz.setTransTimeType(Integer.toString(deli.getTransTimeType().getValue()));

        bz.setRemark(deli.getRemark());
        bz.setRemarkExt(deli.getRemarkEn());

        bz.setTrackingNo(org.springframework.util.StringUtils.hasText(deli.getActualTransNumber()) ? deli.getActualTransNumber() : deli.getTransNumber());
        bz.setGoodsWeight(so.getGoodsWeight());

        //设置相关订单是送货上门，还是非送货上门
        bz.setSpecialServiceType((so.getDeliveryType() != null && SoDeliveryType.HOME_DELIVERY_SERVICE.getValue() == so.getDeliveryType().getValue()) ? 1 : 0);

        return bz;
    }
    
    public static BzPlatformPromotion toBzPlatformPromotion(PlatformPromotion prom) {
        BzPlatformPromotion bz = new BzPlatformPromotion();
        bz.setScopeType(prom.getScopeType());
        bz.setCode(prom.getPromotionId());
        bz.setDescription(prom.getDescription());
        bz.setDiscountFee(prom.getDiscountFee());
        bz.setCouponCode(prom.getPromotionName());
        
        return bz;
    }
    
    public static BzProductPackage toBzProductPackage(SalesOrderLinePackage pack) {
        BzProductPackage bz = new BzProductPackage();
        bz.setType(pack.getPackageType());
        bz.setRemark(pack.getRemark());
        return bz;
    }
    
    public static BzPaymentInfo toBzPaymentInfo(SalesOrderPaymentInfo pay, SalesOrder so) {
        BzPaymentInfo bz = new BzPaymentInfo();
        bz.setOmsOrderCode(so.getOmsOrderCode());
        bz.setPaymentType(Integer.toString(pay.getPaymentType().getValue()));
        bz.setPaymentBank(pay.getPaymentBank());
        bz.setPayTotal(pay.getPayAmount());
        bz.setPayNo(pay.getPayNo());
        bz.setPaymentTime(pay.getPaymentTime());
        bz.setIsPayCompleted(integerToBoolean(pay.getIsCompleted()));
        bz.setOmsPayNo(pay.getId().toString());
        bz.setRemark(pay.getRemark());
        
        bz.setPayAccount(so.getPayAccount());
        
        return bz;
    }

    public static BzOrderMemeber toBzOrderMemeber(OrderMember mem) {
    	if (mem == null) {
    		return null;
    	}
        BzOrderMemeber bz = new BzOrderMemeber();
        bz.setMemberId(mem.getPlatformMemberCode());
        bz.setEmail(mem.getEmail());
        bz.setRealName(mem.getRealName());
        bz.setMobile(mem.getMobile());
        bz.setPhone(mem.getPhone());
        bz.setCountry(mem.getCountry());
        bz.setProvince(mem.getProvince());
        bz.setCity(mem.getCity());
        bz.setDistrict(mem.getDistrict());
        bz.setAddress(mem.getAddress());

        return bz;
    }
    
    public static List<BzOrderServiceLine> toBzOrderServiceLine(SalesOrder so, List<SoServiceLine> soServiceLineList) {
    	if (soServiceLineList == null || soServiceLineList.isEmpty()) {
    		return null;
    	}
    	
    	List<BzOrderServiceLine> bzOrderServiceLines = new ArrayList<BzOrderServiceLine>();
    	for (SoServiceLine ssl : soServiceLineList) {
    		if (ssl.getServiceType() == null || SoServiceType.CUSTOMERS_FROM_CARRYING.equals(ssl.getServiceType())) {//如果是自提服务，则自提服务行不同步给pacs
    			continue;
    		}
    		BzOrderServiceLine bosl = new BzOrderServiceLine();
    		bosl.setPayment(ssl.getPayment());
    		bosl.setPlatformLineId(ssl.getPlatformLineId());
    		bosl.setQty(ssl.getQty());
    		bosl.setServiceId(ssl.getServiceId());
    		bosl.setTitle(ssl.getTitle());
    		bosl.setTmserSpuCode(ssl.getTmserSpuCode());
    		bosl.setTotalActual(ssl.getTotalActual());
    		bosl.setUnitPrice(ssl.getUnitPrice());
    		bosl.setSoServiceType(ssl.getServiceType().getValue());
    		bosl.setServiceTypeDesc(ssl.getServiceTypeDesc());
    		bosl.setServiceCompanyCode(ssl.getProviderCode());
			bosl.setServiceCompanyName(ssl.getProviderName());
    		bosl.setExtProp1(null);
    		bzOrderServiceLines.add(bosl);
    	}
    	
		return bzOrderServiceLines;
	}
    
    public static Boolean integerToBoolean(Integer i) {
        if (i == null || i.intValue() == 0) {
            return false;
        }
        
        return true;
    }    
}
