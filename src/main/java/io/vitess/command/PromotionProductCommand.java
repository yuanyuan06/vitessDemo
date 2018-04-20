package io.vitess.command;

import io.vitess.model.base.PromotionProduct;

public class PromotionProductCommand extends PromotionProduct {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4451282203825462990L;

    private String productName;
    private String skuID;
    private String productCategoryTagId;
    private String productCode;
    private long ruleIineId;
    private long productId;

  
    public String getProductCategoryTagId() {
        return productCategoryTagId;
    }


    public void setProductCategoryTagId(String productCategoryTagId) {
        this.productCategoryTagId = productCategoryTagId;
    }


    public String getProductCode() {
        return productCode;
    }


    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public String getSkuID() {
        return skuID;
    }

   
    public long getRuleIineId() {
        return ruleIineId;
    }

    public void setRuleIineId(long ruleIineId) {
        this.ruleIineId = ruleIineId;
    }

    public void setSkuID(String skuID) {
        this.skuID = skuID;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
