package io.vitess.command;

import io.vitess.common.PropertyUtil;
import io.vitess.enums.PromotionType;
import io.vitess.model.base.PromotionGift;
import io.vitess.model.so.PlatformPromotion;
import io.vitess.model.so.SalesOrderLine;
import io.vitess.model.so.SalesOrderLinePackage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SalesOrderLineCommand extends SalesOrderLine {
    private static final long serialVersionUID = 5937414134954061692L;

    /**
     * sku 平台对接编码
     */
    private String skuExtCode1;
    
    /**
     * 商品外部编码
     */
    private String extensionCode1;
    
    /**
     * 编码，唯一标示Sku的编码 JM_SKU_CODE
     */
    private String jmSkuCode;
    
    /**
     * 订单序列（导入功能使用）
     */
    private String orderSeq;

    private String soCode;

    private String owner;

    private Long invSkuId;

    private String pCode;
    /**
     * 标题
     */
    private String titleStr;
    
    /**
     * 判断是到sku 还是到gift
     */
    private String giftType;
    
    /**
     * JM商品编码
     */
    private String productCode;
    
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品属性
     */
    private String goodsProperties;

    private String supplierSkuCode;

    private String keyProperties2;

    private Date createTime;

    private Long usedQty;
    
    private Long requestedQty;

    private Long currentTaxQty;

    private Long skuId;
    
    private String packageInfo;
    
    /**
     * 促销描述
     */
    private String proDescription;
    
    /**
     * 赠品数量
     */
    private Integer giftQty;
    
    /**
     * 促销赠品
     */
    private PromotionGift promotionGift;
    
    /**
     * 活动编码
     */
    private String promotionCode;
    
    /**
     * 是否赠品
     */
    private Boolean isGift = Boolean.FALSE;
    
    /**
     * 赠品类型
     */
    private PromotionType promotionType;
    
    /**
     * Map<Long, String> Long对应促销活动id, String 赠送数量，活动类型
     */
    private Map<Long, String> proActiveMap;
    
    private Long solineId;
    
    private String orderCode; // 订单编号
    private String upc; // SKU唯一编码
    /**
     * 订单行包装信息
     */
    private List<SalesOrderLinePackage> platformSLPackingInfos;
    /**
     * 是否需要礼品盒
     */
    private Boolean isNeededPacking;
    
    private String refSoCode;
    
    /**
     * 订单平台金额促销活动信息
     */
    private List<PlatformPromotion> soPlatformPromotionList;
    
    private Long soId;
    
    /** 仓库编码 */
    private String warehouseCode;
    
    /** 是否有售后服务的商品 **/
    private Boolean isPostSalesServiceSku = Boolean.FALSE;
    
    /** 销售出库时，出库SN **/
    private List<SoLineSnCommand> soLineSnCommandList;
    
    /** 行类型，主卖品行;赠品行 */
    private Integer orderLineTypeInt;
    
    /** 品牌对接码 */
    private String extensionCode2;
	/**
	 * 赠品Id可能是sku 也可能是 product 根据type 区分
	 */
    private Long GiftId;
    
    /**
     * 全渠道门店编号（若派到的是门店，则此处为门店id；若派到的是电商仓，则此处为电商仓的名字）
     * @return
     */
    private String targetCode;
    
    /**
     * 派到的是门店(STORE)还是电商仓(WAREHOUSE)。
     * @return
     */
    private String targetType;
    /**
     * 星盘派单号
     * @return 
     */
    private String allocationCode;
    
    /**
     * 销售模式
     */
    private String salesMode;

    private Integer allowedQuantity;
    
    

	public String getSalesMode() {
		return salesMode;
	}

	public void setSalesMode(String salesMode) {
		this.salesMode = salesMode;
	}

	public String getAllocationCode() {
		return allocationCode;
	}

	public void setAllocationCode(String allocationCode) {
		this.allocationCode = allocationCode;
	}

	public Long getGiftId() {
		return GiftId;
	}

	public void setGiftId(Long giftId) {
		GiftId = giftId;
	}

	public String getGiftType() {
		return giftType;
	}

	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}

	public List<SoLineSnCommand> getSoLineSnCommandList() {
        return soLineSnCommandList;
    }

    public void setSoLineSnCommandList(List<SoLineSnCommand> soLineSnCommandList) {
        this.soLineSnCommandList = soLineSnCommandList;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }
    
    public Long getInvSkuId() {
        return invSkuId;
    }

    public void setInvSkuId(Long invSkuId) {
        this.invSkuId = invSkuId;
    }

    public String getSoCode() {
        return soCode;
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getSupplierSkuCode() {
        return supplierSkuCode;
    }

    public void setSupplierSkuCode(String supplierSkuCode) {
        this.supplierSkuCode = supplierSkuCode;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getGoodsProperties() {
        return goodsProperties;
    }

    public void setGoodsProperties(String goodsProperties) {
        this.goodsProperties = goodsProperties;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKeyProperties2() {
        return keyProperties2;
    }

    public void setKeyProperties2(String keyProperties2) {
        this.keyProperties2 = keyProperties2;
    }

	public SalesOrderLine generateSoLine(){
		SalesOrderLine soLine = new SalesOrderLine();
		try {
			PropertyUtil.copyProperties(this, soLine);
		} catch (Exception e) {
			throw new RuntimeException("copy property from socommand to so error.");
		}
		return soLine;
	}

    public static SalesOrderLine generateSoLine2(SalesOrderLine destSoData, SalesOrderLine newSoData) {
        try {
            PropertyUtil
                    .copyProperties(destSoData, newSoData, new PropListCopyable("version", "skuName", "skuFob", "keyProp", "jmCode", "requestedQty", "confirmedQty", "sentQty", "receivedQty", "jmSkuCode", "vmiDiscountCode", "posSales", "discountFee",
                            "mdPrice", "couponCode", "skuComboRemark", "totalPointUsed", "listPrice", "platformUnitPrice", "platformTotalActual", "lineTotal", "lineUnitPrice", "finalTotalActual", "finalUnitPrice", "promotionCode1", "promotionCode2",
                            "promotionCode3", "promotionCode4", "promotionCode5", "promotionDiscount1", "promotionDiscount2", "promotionDiscount3", "promotionDiscount4", "promotionDiscount5", "soPromotions", "platformLineId", "product", "oldSoLine",
                            "so", "sku", "isComboSku","extensionCode"));
        } catch (Exception e) {
            throw new RuntimeException("copy property from soline to so error.");
        }
        return newSoData;
    }
    
    public static SalesOrderLine generateSoLine3(SalesOrderLine destSoData, SalesOrderLineCommand newSoData) {
        try {
            PropertyUtil
                    .copyProperties(destSoData, newSoData, new PropListCopyable("version", "skuName", "skuFob", "keyProp", "jmCode", "requestedQty", "confirmedQty", "sentQty", "receivedQty", "jmSkuCode", "vmiDiscountCode", "posSales", "discountFee",
                            "mdPrice", "couponCode", "skuComboRemark", "totalPointUsed", "listPrice", "platformUnitPrice", "platformTotalActual", "lineTotal", "lineUnitPrice", "finalTotalActual", "finalUnitPrice", "promotionCode1", "promotionCode2",
                            "promotionCode3", "promotionCode4", "promotionCode5", "promotionDiscount1", "promotionDiscount2", "promotionDiscount3", "promotionDiscount4", "promotionDiscount5", "soPromotions", "platformLineId", "product", "oldSoLine",
                            "so", "sku", "isComboSku"));
        } catch (Exception e) {
            throw new RuntimeException("copy property from soline to so error.");
        }
        return newSoData;
    }
    
    public SalesOrderLine generateSoLine4(SalesOrderLineCommand soLineCom) {
    	SalesOrderLine soLine = new SalesOrderLine();
        try {
        	PropertyUtil
            .copyProperties(this, soLine, new PropListCopyable("version", "skuName", "skuFob", "keyProp", "jmCode", "requestedQty", "confirmedQty", "sentQty", "receivedQty", "jmSkuCode", "vmiDiscountCode", "posSales", "discountFee",
                    "mdPrice", "couponCode", "skuComboRemark", "totalPointUsed", "listPrice", "platformUnitPrice", "platformTotalActual", "lineTotal", "lineUnitPrice", "finalTotalActual", "finalUnitPrice", "promotionCode1", "promotionCode2",
                    "promotionCode3", "promotionCode4", "promotionCode5", "promotionDiscount1", "promotionDiscount2", "promotionDiscount3", "promotionDiscount4", "promotionDiscount5", "soPromotions", "platformLineId", "product", "oldSoLine",
                    "so", "sku", "isComboSku"));
        } catch (Exception e) {
            throw new RuntimeException("copy property from socommand to so error.");
        }
        return soLine;
    }

    public Long getUsedQty() {
        return usedQty;
    }

    public void setUsedQty(Long usedQty) {
        this.usedQty = usedQty;
    }

    public Long getCurrentTaxQty() {
        return currentTaxQty;
    }

    public void setCurrentTaxQty(Long currentTaxQty) {
        this.currentTaxQty = currentTaxQty;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo;
    }

	public String getProDescription() {
		return proDescription;
	}

	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}

	public Integer getGiftQty() {
		return giftQty;
	}

	public void setGiftQty(Integer giftQty) {
		this.giftQty = giftQty;
	}

	public PromotionGift getPromotionGift() {
		return promotionGift;
	}

	public void setPromotionGift(PromotionGift promotionGift) {
		this.promotionGift = promotionGift;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public Boolean getIsGift() {
		return isGift;
	}

	public void setIsGift(Boolean isGift) {
		this.isGift = isGift;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	public Map<Long, String> getProActiveMap() {
		return proActiveMap;
	}

	public void setProActiveMap(Map<Long, String> proActiveMap) {
		this.proActiveMap = proActiveMap;
	}

	public Long getSolineId() {
		return solineId;
	}

	public void setSolineId(Long solineId) {
		this.solineId = solineId;
	}

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public List<SalesOrderLinePackage> getSalesOrderLinePackage() {
        return platformSLPackingInfos;
    }

    public void setSalesOrderLinePackage(List<SalesOrderLinePackage> platformSLPackingInfos) {
        this.platformSLPackingInfos = platformSLPackingInfos;
    }

    public Boolean getIsNeededPacking() {
        return isNeededPacking;
    }

    public void setIsNeededPacking(Boolean isNeededPacking) {
        this.isNeededPacking = isNeededPacking;
    }

    public String getRefSoCode() {
        return refSoCode;
    }

    public void setRefSoCode(String refSoCode) {
        this.refSoCode = refSoCode;
    }

    public List<PlatformPromotion> getPlatformPromotionList() {
        return soPlatformPromotionList;
    }

    public void setPlatformPromotionList(List<PlatformPromotion> soPlatformPromotionList) {
        this.soPlatformPromotionList = soPlatformPromotionList;
    }

	public String getSkuExtCode1() {
		return skuExtCode1;
	}

	public void setSkuExtCode1(String skuExtCode1) {
		this.skuExtCode1 = skuExtCode1;
	}

	public String getExtensionCode1() {
		return extensionCode1;
	}

	public void setExtensionCode1(String extensionCode1) {
		this.extensionCode1 = extensionCode1;
	}

	public Boolean getIsPostSalesServiceSku() {
		return isPostSalesServiceSku;
	}

	public void setIsPostSalesServiceSku(Boolean isPostSalesServiceSku) {
		this.isPostSalesServiceSku = isPostSalesServiceSku;
	}

	public String getJmSkuCode() {
		return jmSkuCode;
	}

	public void setJmSkuCode(String jmSkuCode) {
		this.jmSkuCode = jmSkuCode;
	}

	public Long getRequestedQty() {
		return requestedQty;
	}

	public void setRequestedQty(Long requestedQty) {
		this.requestedQty = requestedQty;
	}

    public Integer getOrderLineTypeInt() {
        return orderLineTypeInt;
    }

    public void setOrderLineTypeInt(Integer orderLineTypeInt) {
        this.orderLineTypeInt = orderLineTypeInt;
    }

	public String getExtensionCode2() {
		return extensionCode2;
	}

	public void setExtensionCode2(String extensionCode2) {
		this.extensionCode2 = extensionCode2;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

    public Integer getAllowedQuantity() {
        return allowedQuantity;
    }

    public void setAllowedQuantity(Integer allowedQuantity) {
        this.allowedQuantity = allowedQuantity;
    }
}
