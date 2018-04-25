package io.vitess.service.common;

import io.vitess.command.*;
import io.vitess.common.DateUtil;
import io.vitess.constants.Constants;
import io.vitess.dao.base.CompanyShopDao;
import io.vitess.dao.base.ProductDao;
import io.vitess.dao.base.PromotionActivityDao;
import io.vitess.dao.base.SkuDao;
import io.vitess.dao.so.VmiTimedPromotionDao;
import io.vitess.enums.OrderLineType;
import io.vitess.enums.PromotionType;
import io.vitess.model.base.Product;
import io.vitess.model.base.PromotionActivity;
import io.vitess.model.base.Sku;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.so.SalesOrder;
import io.vitess.model.so.SalesOrderLine;
import io.vitess.model.so.SoPromotionApplyLog;
import io.vitess.model.so.VmiTimedPromotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("promotionManager")
public class PromotionManagerImpl implements PromotionManager{
    private static final long serialVersionUID = 8502543169225968148L;
    
    protected static final Logger log = LoggerFactory.getLogger(PromotionManagerImpl.class);
    
    @Autowired
    private PromotionActivityDao promotionActivityDao;
    @Autowired
    private PromotionCashManager promotionCashManager;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private VmiTimedPromotionDao vmiTimedPromotionDao;
    @Autowired
    private CompanyShopDao companyShopDao;
    /**
     * 赠品是否到sku标示 0 是不到 1是到
     */
    private static final String IS_SKU_GIFT_TYPE = "1";
    private static final String NO_SKU_GIFT_TYPE = "0";
    /**
     * 促销应用
     * @param soc
     * @return
     */
    @Override
    public SalesOrderCommand applyPromotionAndAddGiftLine(SalesOrderCommand soc) {
        log.info("促销调用Start");
        CompanyShop byId = companyShopDao.findById(soc.getCompanyShop());
        Long shopId = byId.getId();
        SalesOrder newSo = soc;
        Collection<SalesOrderLineCommand> slList = soc.getSoLineCommandList();
        // 促销应用
        Collection<SalesOrderLineCommand> slComList = promotionActiveUsed(shopId, newSo, slList);
        log.info("赠品行："+slComList.size());
        // 赠品行添加
        if (slComList!=null&&slComList.size()>0) {
            for (SalesOrderLineCommand slCommand : slComList) {
                // 赠品添加日志
                List<SoPromotionApplyLog> logList = new ArrayList<SoPromotionApplyLog>();
                if (null != slCommand.getIsGift() && slCommand.getIsGift() == true) {
                    Map<Long, String> proActiveMap = slCommand.getProActiveMap();
                    if (null != proActiveMap) {
                        Set<Long> key = proActiveMap.keySet();
                        for (Iterator<Long> it = key.iterator(); it.hasNext();) {
                            Long proActiveId = (Long) it.next();
                            String proInfoStr = proActiveMap.get(proActiveId);
                            //优化fanht
                            PromotionActivity proActie = promotionActivityDao.findPromotionByIdShopId(proActiveId, shopId);
                            SoPromotionApplyLog proLog = new SoPromotionApplyLog();
                            proLog.setCreateTime(DateUtil.now());
                            if (null != proActie) {
                                proLog.setDescription(proActie.getDescription());
                                proLog.setPromotionCode(proActie.getCode());
                            }
                            if (proInfoStr.contains(Constants.SEMICOLON)) {
                                String[] args = proInfoStr.split(Constants.SEMICOLON);
                                proLog.setGiftQty(Integer.parseInt(args[0]));
                                proLog.setType(PromotionType.valueOf(Integer.parseInt(args[1])));
                            }
                            proLog.setGiftType(slCommand.getGiftType());
                            proLog.setPromotionGiftId(slCommand.getGiftId());
                            //proLog.setSoLine(slCommand);
                            proLog.setShopId(shopId);//店铺ID
                            
                            logList.add(proLog);
                            
                        }
                    }
                }
                //追加赠品日志
                slCommand.setProLog(logList);
                //赠品加到so中
                soc.getSoLineCommandList().add(slCommand);
                log.info("促销增加的商品："+slCommand.getSkuCode()+":"+slCommand.getSkuName());
            }
        }
        log.info("促销调用End");
        return soc;
    }

    /**
     * 计算赠品行、促销应用
     * @param shopId
     * @param newSo
     * @param slList
     * @return
     */
    private Collection<SalesOrderLineCommand> promotionActiveUsed(Long shopId, SalesOrder newSo, Collection<SalesOrderLineCommand> slList) {
        Collection<SalesOrderLineCommand> slComList = new ArrayList<SalesOrderLineCommand>();
        // 缓存中读取促销
        Map<Long, List<PromotionActivityCommand2>> promotionMap = PromotionCashManagerImpl.getPromotionMap();
        if (promotionMap!=null) {
            log.info("获取促销缓存："+promotionMap.size());
            // 根据店铺ID获取促销应用
            List<PromotionActivityCommand2> proList = promotionMap.get(shopId);
            if (proList!=null && proList.size() > 0) {
                log.info("获取店铺"+shopId+"的促销缓存："+proList.size());
                Map<Long, PromotionGiftCommand> giftMap = new HashMap<Long, PromotionGiftCommand>();
                // 初始化skuInfoMap 信息 --订单商品信息 <商品ID，商品信息组合>
                // PromotionSkuCommand信息组成（数量；是否组合商品；金额;是否标签；）
                Map<Long, PromotionSkuCommand> skuInfoNotComboMap = new HashMap<Long, PromotionSkuCommand>();
                Map<Long, PromotionSkuCommand> skuInfoComboMap = new HashMap<Long, PromotionSkuCommand>();
                //分析销售明细行
                for (SalesOrderLineCommand sl : slList) {
                    PromotionSkuCommand skuCom = new PromotionSkuCommand();
                    skuCom.setProductQty(sl.getQuantity());
                    skuCom.setIsComboProduct(sl.getIsComboSku());
                    //折扣后行金额总计 
                    skuCom.setLineTotal(sl.getTotalAmountAfterDiscount());
                    skuCom.setShopId(shopId);
                    Sku sku = skuDao.findById(sl.getSku());
                    if (sku !=null && sku.getProduct() != null) {
                        Product byId = productDao.findById(sku.getProduct());
                        Long productId = byId.getId();
                        if (sl.getIsComboSku()) {
                            PromotionSkuCommand skuCom2 = skuInfoComboMap.get(productId);
                            // 同款合并
                            if (null != skuCom2) {
                                skuCom.setProductQty(skuCom.getProductQty() + skuCom2.getProductQty());
                                skuCom.setLineTotal(skuCom.getLineTotal().add(skuCom2.getLineTotal()));
                            }
                            skuInfoComboMap.put(productId, skuCom);
                        } else {
                            PromotionSkuCommand skuCom3 = skuInfoNotComboMap.get(productId);
                            // 同款合并
                            if (null != skuCom3) {
                                skuCom.setProductQty(skuCom.getProductQty() + skuCom3.getProductQty());
                                skuCom.setLineTotal(skuCom.getLineTotal().add(skuCom3.getLineTotal()));
                            }
                            skuInfoNotComboMap.put(productId, skuCom);
                        }
                    }
                }
                //促销时间判断
                for (PromotionActivityCommand2 proData : proList) {
                    //促销时间判断(付款完成时间判断)
                    if (newSo.getPlatformPaymentTime()!=null) {
                        Date paymentCopTime = newSo.getPlatformPaymentTime();
                        // Date paymentCopTime = newSo.getCreateTime();
                        Date activeTimeBegin = proData.getStartTime();
                        Date activeTimeEnd = proData.getEndTime();
                        // 满足促销应用时间
                        if (DateUtil.DateCompare(paymentCopTime, activeTimeBegin, activeTimeEnd)) {
                            log.info("促销时间满足，促销活动号："+proData.getCode());
                            // 应用所有可使用规则、订单折后金额
                            giftMap = promotionCashManager.applyPromotion(proData, newSo.getAmountAfterDiscount(), skuInfoComboMap, skuInfoNotComboMap, giftMap);
                        }
                    }
                }
                // 赠品添加
                if (giftMap!=null) {
                    Set<Long> key = giftMap.keySet();
                    for (Iterator<Long> it = key.iterator(); it.hasNext();) {
                    	//商品ID
                        Long giftProductId = (Long) it.next();
                        PromotionGiftCommand proGiftCommand = giftMap.get(giftProductId);
                        Integer giftQyt = proGiftCommand.getGiftQty();
                        Long productGiftId = proGiftCommand.getId();
                        Map<Long, String> proActiveMap = proGiftCommand.getProActiveMap();

                        SalesOrderLineCommand soline = new SalesOrderLineCommand();
                        //根据赠品表中的商品ID查出商品市场价
                        Product product = null;
                        Sku sku = null;
                        if(proGiftCommand.getBindId().equals(NO_SKU_GIFT_TYPE)){
                        	//如果是赠品是商品的时候map的key就是商品Id 
                        	 product =productDao.findById(giftProductId);
                             //根据赠品表商品ID随机取一条SKU信息放入订单明细
	                         sku = skuDao.findByProductId(giftProductId);
	                         soline.setGiftType(NO_SKU_GIFT_TYPE);
                        }else{
                        	//根据sku具体ID 去sku表查询sku明细数据 如果赠品是sku的时候 需要从map中获取商品ID 加入商品价格
                        	 product = productDao.findById(proGiftCommand.getProductID());
	                         sku = skuDao.findById(giftProductId);
	                         soline.setGiftType(IS_SKU_GIFT_TYPE);
                        }
                        if (null != sku) {
                            soline.setSkuName(sku.getName());
                            soline.setBarCode(sku.getBarCode());
                            soline.setKeyProperties(sku.getKeyProperties());
                            soline.setSkuCode(sku.getCode());
                            soline.setExtentionCode(sku.getExtensionCode1());
                            soline.setSku(sku.getId());
                        }
                        soline.setQuantity(giftQyt);
                        if(null !=product){
                        	soline.setListPrice(product.getListPrice());
                        }
                        soline.setIsGift(Boolean.TRUE);
                        soline.setOrderLineType(OrderLineType.GIFT.getValue());
                        soline.setPlatformOrderLineCode("促销赠品_" + slComList.size());
                        soline.setUnitPrice(BigDecimal.ZERO);
                        soline.setTotalAmount(BigDecimal.ZERO);
                        soline.setLineDiscount(BigDecimal.ZERO);
                        soline.setTotalDiscount(BigDecimal.ZERO);
                        soline.setTotalAmountAfterDiscount(BigDecimal.ZERO);
                        soline.setUnitPriceAfterDiscount(BigDecimal.ZERO);
                        soline.setVirtualAmount(BigDecimal.ZERO);
                        soline.setInvoiceTotalAmount(BigDecimal.ZERO);
                        soline.setInvoiceUnitPrice(BigDecimal.ZERO);
                        soline.setTotalPointUsed(BigDecimal.ZERO);
                        soline.setSalesOrder(newSo.getId());
                        // 赠品日志内容添加
                        soline.setGiftQty(giftQyt);
                        soline.setGiftId(productGiftId);
                        soline.setIsGift(true);
                        soline.setPromotionType(proGiftCommand.getType());
                        soline.setProActiveMap(proActiveMap);
                        soline.setShopId(shopId);
                        slComList.add(soline);
                    }
                }
            }
        }
        return slComList;
    }
    
    /**
     * VMI促销应用
     * @param soc
     * @return
     */
    @Override
    public SalesOrderCommand applyVmiPromotion(SalesOrderCommand soc) {
        CompanyShop shop = companyShopDao.findById(soc.getCompanyShop());
        Collection<SalesOrderLineCommand> slList = soc.getSoLineCommandList();
        //折前价
        BigDecimal lineSumTotal = soc.getAmountBeforeDiscount();

        Map<Long, Collection<VmiTimedPromotion>> timedProMap01 = null;
        Map<String, VmiTimedPromotion> timedProMap02 = null;
        Map<Long, VmiTimedPromotion> timedProMap03 = null;
        // if (shop.getId().longValue() == 601||shop.getId().longValue()==1782)
        // {
        // 找VMI限时促销码（01商品折扣）
        List<VmiTimedPromotion> timedProsList01 = vmiTimedPromotionDao.findVmiPromotionList(5, "01", shop.getId());
        timedProMap01 = new HashMap<Long, Collection<VmiTimedPromotion>>();
        if (timedProsList01 != null && timedProsList01.size() > 0) {
            for (VmiTimedPromotion timedPro : timedProsList01) {
                Collection<VmiTimedPromotion> timedPros = timedProMap01.get(timedPro.getProduct().getId());
                if (timedPros == null) {
                    timedPros = new ArrayList<VmiTimedPromotion>();
                }
                timedPros.add(timedPro);
                timedProMap01.put(timedPro.getProduct().getId(), timedPros);
            }
        }
        // 找VMI限时促销码（02满额减）
        List<VmiTimedPromotion> timedProsList02 = vmiTimedPromotionDao.findVmiPromotionList(5, "02", shop.getId());
        timedProMap02 = new HashMap<String, VmiTimedPromotion>();
        if (timedProsList02 != null && timedProsList02.size() > 0) {
            for (VmiTimedPromotion timedPro : timedProsList02) {
                String proAmount = timedPro.getProAmount().setScale(0).toString();
                String proAmount2 = proAmount;
                if (timedPro.getProAmount2() != null) proAmount2 = timedPro.getProAmount2().setScale(0).toString();
                String key = proAmount + "/" + proAmount2;
                timedProMap02.put(key, timedPro);
            }
        }
        // 找VMI限时促销码（04单品买赠）
        List<VmiTimedPromotion> timedProsList03 = vmiTimedPromotionDao.findVmiPromotionList(5, "04", shop.getId());
        timedProMap03 = new HashMap<Long, VmiTimedPromotion>();
        if (timedProsList03 != null && timedProsList03.size() > 0) {
            for (VmiTimedPromotion timedPro : timedProsList03) {
                timedProMap03.put(timedPro.getMasterProduct().getId(), timedPro);
            }
        }

        List<SalesOrderLineCommand> vmiProSoLines = applyVmiPromotion(soc, slList, shop.getId(), timedProMap01, timedProMap02, timedProMap03, lineSumTotal);
        soc.getSoLineCommandList().addAll(vmiProSoLines);
        //计算销售订单 POS 销售额
        return calLevisPosAmount(soc);
    }
    
    /**
     * 计算销售订单 POS 销售额
     */
    private SalesOrderCommand calLevisPosAmount(SalesOrderCommand so) {
        for (SalesOrderLineCommand soLine : so.getSoLineCommandList()) {
            //折后行总价
            BigDecimal linePosSales = soLine.getTotalAmountAfterDiscount().multiply(new BigDecimal(0.995)).setScale(2, BigDecimal.ROUND_HALF_UP);
            soLine.setPosSales(linePosSales);
        }
        //折后总价
        so.setPosSales(so.getAmountAfterDiscount().multiply(new BigDecimal(0.995)).setScale(2, BigDecimal.ROUND_HALF_UP));
        return so;
    }
        
    private List<SalesOrderLineCommand> applyVmiPromotion(SalesOrderCommand so, Collection<SalesOrderLineCommand> soLines, Long shopId, Map<Long, Collection<VmiTimedPromotion>> timedProMap01, Map<String, VmiTimedPromotion> timedProMap02,
                                                          Map<Long, VmiTimedPromotion> timedProMap03, BigDecimal lineSumTotal) {
        List<SalesOrderLineCommand> vmiPromotionSoLines = new ArrayList<SalesOrderLineCommand>();
        Date createdTime = so.getPlatformCreateTime();
        Date payTime = so.getPlatformPaymentTime() == null ? so.getPlatformCreateTime() : so.getPlatformPaymentTime();
        BigDecimal totalActual = so.getAmountAfterDiscount();// 订单实际金额
        // BigDecimal totalBfDiscount = so.getTotalBfDiscount(); // 订单折前金额
        BigDecimal totalBfDiscount = lineSumTotal; //订单折前金额
        // 应用01类型促销
        if (timedProMap01.size() > 0) {
            // log.error("---start vmi price pro1---" + so.getCode() + "---" +
            // timedProMap01.size());
            for (SalesOrderLine line : soLines) {
                // Long skuId = line.getProduct().getId();
                Long skuId = line.getSku();
                Sku sku = skuDao.findById(skuId);
                Product proId = productDao.findById(sku.getProduct());
                Collection<VmiTimedPromotion> timedPros = timedProMap01.get(proId.getId());
                if (timedPros != null) {
                    for (VmiTimedPromotion timedPro : timedPros) {
                        // log.error("---start vmi price pro2---" + so.getCode()
                        // + "---" + timedPros.size());
                        if (timedPro != null) {
                            // log.error("---start vmi price pro3---" +
                            // timedPro.getCode());
                            if (createdTime.compareTo(timedPro.getStartTime()) >= 0 && createdTime.compareTo(timedPro.getEndTime()) < 0) {
                                line.setVmiDiscountCode(timedPro.getCode());
                                // log.error("---apply vmi price pro3---" +
                                // so.getCode() + "---" +
                                // line.getVmiDiscountCode());
                                continue;
                            }
                        }
                    }
                }
            }
        }
        // 应用04类型促销（单品买赠）
        // 赠品信息
        Map<Long, Integer> giftMaps = new HashMap<Long, Integer>();
        if (timedProMap03.size() > 0) {
            for (SalesOrderLine line : soLines) {
                // Long skuId = line.getProduct().getId();

                Long skuId = line.getSku();
                Sku sku = skuDao.findById(skuId);
                Product proId = productDao.findById(sku.getProduct());
                VmiTimedPromotion pro = timedProMap03.get(proId.getId());
                //04类型回退fanht
                //if (pro != null && pro.getGiftQuota() >= line.getQuantity() && (payTime.compareTo(pro.getStartTime()) >= 0 && payTime.compareTo(pro.getEndTime()) < 0)) {
                if (pro != null && pro.getGiftQuota() > 0 && (payTime.compareTo(pro.getStartTime()) >= 0 && payTime.compareTo(pro.getEndTime()) < 0)) {
                    Long giftSkuId = pro.getProduct().getId();
                    // int giftQty = line.getRequestedQty().intValue();
                    int giftQty = line.getQuantity();
                    giftQty = giftQty + (giftMaps.get(giftSkuId) == null ? 0 : giftMaps.get(giftSkuId));
                    giftMaps.put(giftSkuId, giftQty);
                }
            }
        }
        if (giftMaps.size() > 0) {
            log.info("---add gift---");
            for (Long productId : giftMaps.keySet()) {
                Integer qty = giftMaps.get(productId);
                SalesOrderLineCommand li = new SalesOrderLineCommand();
                Sku sku = skuDao.findByProductId(productId);
                Product product = productDao.findById(sku.getProduct());

                li.setIsGift(Boolean.TRUE);
                li.setOrderLineType(OrderLineType.GIFT.getValue());
                li.setPlatformOrderLineCode("VMI促销赠品");
                li.setKeyProperties(sku.getKeyProperties());
                li.setBarCode(sku.getBarCode());
                li.setExtentionCode(sku.getExtensionCode1());
                li.setSkuCode(sku.getCode());
                li.setSku(sku.getId());
                li.setQuantity(qty);
                li.setSkuName(product.getName());
                li.setVmiDiscountCode(product.getSupplierSkuCode());
                li.setShopId(shopId);

                li.setUnitPrice(BigDecimal.ZERO);
                li.setTotalAmount(BigDecimal.ZERO);
                li.setLineDiscount(BigDecimal.ZERO);
                li.setTotalDiscount(BigDecimal.ZERO);
                li.setTotalAmountAfterDiscount(BigDecimal.ZERO);
                li.setUnitPriceAfterDiscount(BigDecimal.ZERO);
                li.setVirtualAmount(BigDecimal.ZERO);
                li.setInvoiceTotalAmount(BigDecimal.ZERO);
                li.setInvoiceUnitPrice(BigDecimal.ZERO);
                li.setListPrice(BigDecimal.ZERO);
                li.setTotalPointUsed(BigDecimal.ZERO);
                
                vmiPromotionSoLines.add(li);

                // 扣减赠品配额
                for (Long mtProductId : timedProMap03.keySet()) {
                    VmiTimedPromotion pro = timedProMap03.get(mtProductId);
                    if (productId.longValue() == pro.getProduct().getId().longValue()) {
                        pro.setGiftQuota(pro.getGiftQuota() - qty);
                        vmiTimedPromotionDao.insert(pro);
                    }
                }
            }
        }

        // 应用02类型促销
        BigDecimal proDiscount = totalBfDiscount.subtract(totalActual);
        if (proDiscount.compareTo(new BigDecimal(0)) > 0) {
            if (timedProMap02.size() > 0) {
                for (String key : timedProMap02.keySet()) {
                    String proAmount1 = key.split("/")[0];
                    String proAmount2 = key.split("/")[1];
                    if (proDiscount.compareTo(new BigDecimal(proAmount1)) >= 0 && proDiscount.compareTo(new BigDecimal(proAmount2)) <= 0) {
                        VmiTimedPromotion timedPro = timedProMap02.get(key);
                        if (payTime.compareTo(timedPro.getStartTime()) >= 0 && payTime.compareTo(timedPro.getEndTime()) <= 0) {
                            so.setVmiPromotionCode(timedPro.getCode());
                            break;
                        }
                    }
                }

            }
            // 如果金额有差异但是无促销码，则设置默认促销码
            if (shopId.longValue() != 1782 && so.getVmiPromotionCode() == null) {
                so.setVmiPromotionCode("PPA5416");
            }
        }

        // 应用03类型促销：优化可以支持多线程算法fanht
        List<VmiTimedPromotion> vmiTimedPromotion3List = vmiTimedPromotionDao.findVmiPromotionList2(5, "03", shopId, payTime, totalActual);
        VmiTimedPromotion timedPro = null;
        if (vmiTimedPromotion3List != null && vmiTimedPromotion3List.size() > 0) {
            timedPro = vmiTimedPromotion3List.iterator().next();
        }
        
        //抢占库存算法fanht
        Integer update = 0;
        if (timedPro != null) {
        	update = vmiTimedPromotionDao.updateGiftQuota(timedPro.getId(),  shopId);
        }
        
        if (timedPro != null && update > 0) {
            log.info("---apply vmi gift---" + so.getOmsOrderCode() + "---so total---" + totalActual);
            SalesOrderLineCommand li = new SalesOrderLineCommand();
            Product product = timedPro.getProduct();
            Sku sku = skuDao.findByProductId(product.getId());

            li.setIsGift(Boolean.TRUE);
            li.setOrderLineType(OrderLineType.GIFT.getValue());
            li.setPlatformOrderLineCode("VMI促销赠品");
            li.setKeyProperties(sku.getKeyProperties());
            li.setBarCode(sku.getBarCode());
            li.setExtentionCode(sku.getExtensionCode1());
            li.setSkuCode(sku.getCode());
            li.setSku(sku.getId());
            li.setQuantity(1);
            li.setSkuName(product.getName());
            li.setVmiDiscountCode(timedPro.getCode());
            li.setShopId(shopId);

            li.setUnitPrice(BigDecimal.ZERO);
            li.setTotalAmount(BigDecimal.ZERO);
            li.setLineDiscount(BigDecimal.ZERO);
            li.setTotalDiscount(BigDecimal.ZERO);
            li.setTotalAmountAfterDiscount(BigDecimal.ZERO);
            li.setUnitPriceAfterDiscount(BigDecimal.ZERO);
            li.setVirtualAmount(BigDecimal.ZERO);
            li.setInvoiceTotalAmount(BigDecimal.ZERO);
            li.setInvoiceUnitPrice(BigDecimal.ZERO);
            li.setListPrice(BigDecimal.ZERO);
            li.setTotalPointUsed(BigDecimal.ZERO);

            vmiPromotionSoLines.add(li);
            
//            timedPro.setGiftQuota(timedPro.getGiftQuota() - 1);
//            vmiTimedPromotionDao.save(timedPro);
            
            log.info("---end vmi gift---" + so.getOmsOrderCode() + "---so total---" + totalActual);
        }
        return vmiPromotionSoLines;
    }
}
