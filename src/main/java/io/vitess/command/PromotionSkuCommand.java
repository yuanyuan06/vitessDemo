package io.vitess.command;

import java.io.Serializable;
import java.math.BigDecimal;

public class PromotionSkuCommand implements Serializable {
	private static final long serialVersionUID = 4697123645797316319L;
	
	/**
     * 数量
     */
    private Integer productQty;
    
    /**
     * 是否组合商品
     */
    private Boolean isComboProduct;
    
    /**
     * 商品行总金额
     */
    private BigDecimal lineTotal;
    
    /**
     * shopId
     */
    private Long shopId;
    
    /**
     * 全场切包含指定数量的标签商品 fanht
     */
    private Boolean isUsed = Boolean.FALSE;

	public Integer getProductQty() {
		return productQty;
	}

	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}

	public Boolean getIsComboProduct() {
		return isComboProduct;
	}

	public void setIsComboProduct(Boolean isComboProduct) {
		this.isComboProduct = isComboProduct;
	}

	public BigDecimal getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(BigDecimal lineTotal) {
		this.lineTotal = lineTotal;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }
    
}
