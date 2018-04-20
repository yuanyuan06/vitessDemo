package io.vitess.command;

import io.vitess.model.base.PromotionRule;

public class PromotionRuleCommand extends PromotionRule {

    /**
	 * 
	 */
    private static final long serialVersionUID = -8981467346813967074L;
   
    private String addORupdateFlag;
    private Long skuId; // 商品的id
    private Integer productScopeUpdate;

    public String getAddORupdateFlag() {
        return addORupdateFlag;
    }

    public void setAddORupdateFlag(String addORupdateFlag) {
        this.addORupdateFlag = addORupdateFlag;
    }
  
    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getProductScopeUpdate() {
        return productScopeUpdate;
    }

    public void setProductScopeUpdate(Integer productScopeUpdate) {
        this.productScopeUpdate = productScopeUpdate;
    }


}
