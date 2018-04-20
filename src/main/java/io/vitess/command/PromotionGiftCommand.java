package io.vitess.command;

import io.vitess.enums.PromotionType;
import io.vitess.model.base.PromotionGift;

import java.util.Map;


public class PromotionGiftCommand extends PromotionGift {

   
    private static final long serialVersionUID = -8896248496410974085L;

    private String giftName;
    private String skuID;
    private long productID;
    private long giftId;
    private long ruleIineId;
    private String giftCode;
    
    

	public String getGiftCode() {
        return giftCode;
    }

    public void setGiftCode(String giftCode) {
        this.giftCode = giftCode;
    }

    private PromotionType type;
    
    /**
     * Map<Long, String> Long对应促销活动id, String 赠送数量，活动类型
     */
    private Map<Long, String> proActiveMap;
    
    public long getProductID() {
        return productID;
    }

    public long getRuleIineId() {
        return ruleIineId;
    }

    public void setRuleIineId(long ruleIineId) {
        this.ruleIineId = ruleIineId;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getSkuID() {
        return skuID;
    }

    public void setSkuID(String skuID) {
        this.skuID = skuID;
    }

    public long getGiftId() {
        return giftId;
    }

    public void setGiftId(long giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

	public PromotionType getType() {
		return type;
	}

	public void setType(PromotionType type) {
		this.type = type;
	}

	public Map<Long, String> getProActiveMap() {
		return proActiveMap;
	}

	public void setProActiveMap(Map<Long, String> proActiveMap) {
		this.proActiveMap = proActiveMap;
	}
}
