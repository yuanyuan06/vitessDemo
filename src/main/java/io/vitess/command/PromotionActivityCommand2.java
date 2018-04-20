package io.vitess.command;

import io.vitess.model.base.PromotionActivity;
import io.vitess.model.base.PromotionGift;
import io.vitess.model.base.PromotionProduct;
import io.vitess.model.base.PromotionRule;

import java.util.List;
import java.util.Map;

public class PromotionActivityCommand2 extends PromotionActivity {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2380266612522329047L;

    /**
     * 促销活动规则
     */
    private List<PromotionRule> rulesList;

    /**
     * 促销活动主卖品
     */
    private Map<Long, List<PromotionProduct>> productMap;

    /**
     * 促销活动赠品
     */
    private Map<Long, List<PromotionGift>> giftMap;

    /**
     * shopId
     * 
     * @return
     */
    private Long shopId;

    /**
     * typeInt
     */
    private Integer typeInt;

    /**
     * statusInt
     */
    private Integer statusInt;
    
    /**
     * productScopeInt
     */
    private Integer productScopeInt;
    
    /**
     * isExceludeComboSkuInt
     */
    private Integer isExceludeComboSkuInt;
    
    /**
     * applyModelInt
     */
    private Integer applyModelInt;
    
    /**
     * promotionProductTypeInt
     */
    private Integer promotionProductTypeInt;

    public List<PromotionRule> getRulesList() {
        return rulesList;
    }

    public void setRulesList(List<PromotionRule> rulesList) {
        this.rulesList = rulesList;
    }

    public Map<Long, List<PromotionProduct>> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Long, List<PromotionProduct>> productMap) {
        this.productMap = productMap;
    }

    public Map<Long, List<PromotionGift>> getGiftMap() {
        return giftMap;
    }

    public void setGiftMap(Map<Long, List<PromotionGift>> giftMap) {
        this.giftMap = giftMap;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getTypeInt() {
        return typeInt;
    }

    public void setTypeInt(Integer typeInt) {
        this.typeInt = typeInt;
    }

    public Integer getStatusInt() {
        return statusInt;
    }

    public void setStatusInt(Integer statusInt) {
        this.statusInt = statusInt;
    }

	public Integer getProductScopeInt() {
		return productScopeInt;
	}

	public void setProductScopeInt(Integer productScopeInt) {
		this.productScopeInt = productScopeInt;
	}

	public Integer getIsExceludeComboSkuInt() {
		return isExceludeComboSkuInt;
	}

	public void setIsExceludeComboSkuInt(Integer isExceludeComboSkuInt) {
		this.isExceludeComboSkuInt = isExceludeComboSkuInt;
	}

	public Integer getApplyModelInt() {
		return applyModelInt;
	}

	public void setApplyModelInt(Integer applyModelInt) {
		this.applyModelInt = applyModelInt;
	}

	public Integer getPromotionProductTypeInt() {
		return promotionProductTypeInt;
	}

	public void setPromotionProductTypeInt(Integer promotionProductTypeInt) {
		this.promotionProductTypeInt = promotionProductTypeInt;
	}

}
