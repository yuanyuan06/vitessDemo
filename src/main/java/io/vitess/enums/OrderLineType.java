package io.vitess.enums;

/**
 * 行类型
 * @author 李光辉
 * @date 2014年7月4日 下午4:46:23
 * @since
 */
public enum OrderLineType {
    /** 1,主卖品行 */
    MAIN(1), 
    /** 2,促销赠品行 */
    GIFT(2),
    /** 3,手动添加赠品行, 即额度申请赠品 */
    MANUAL_GIFT(3),
    /** 4,原订单赠品, 即tmall平台订单， */
    TMALL_PLATFORM_GIFT(4);
    
    private int value;

    private OrderLineType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    /**
     * 是否为非tmall平台的赠品
     */
    public static boolean isNotTmallPlatformGift(OrderLineType orderLineType) {
    	if (orderLineType == null) {
    		return false;
    	}
    	return isNotTmallPlatformGift(orderLineType.getValue());
    }
    
    /**
     * 是否为非tmall平台的赠品
     */
    public static boolean isNotTmallPlatformGift(int value) {
    	if (GIFT.getValue() == value || MANUAL_GIFT.getValue() == value) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * 是否为促销赠品行和原订单赠品
     * @author hailiang.jiang
     * @date 2015年10月16日 下午5:31:43
     * @param orderLineType
     * @return
     */
    public static boolean isPromotionGiftSku(OrderLineType orderLineType) {
    	if (orderLineType != null && (orderLineType.getValue() == GIFT.getValue() || orderLineType.getValue() == TMALL_PLATFORM_GIFT.getValue())) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * 是否为额度申请赠品
     * @author hailiang.jiang
     * @date 2015年10月16日 下午5:31:53
     * @param orderLineType
     * @return
     */
    public static boolean isLimitGiftSku(OrderLineType orderLineType) {
    	if (orderLineType != null && orderLineType.getValue() == MANUAL_GIFT.getValue()) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * 是否为原订单赠品
     * @author kuan.liu
     * @date 2016年01月04日 下午5:31:53
     * @param orderLineType
     * @return
     */
    public static boolean isTmallPlatformGiftSku(OrderLineType orderLineType) {
        if (orderLineType != null && orderLineType.getValue() == TMALL_PLATFORM_GIFT.getValue()) {
            return true;
        }
        return false;
    }
    
    /**
     * 是否为促销赠品
     * @author kuan.liu
     * @date 2016年01月06日 下午5:31:53
     * @param orderLineType
     * @return
     */
    public static boolean isPromotionGift(OrderLineType orderLineType) {
        if (orderLineType != null && orderLineType.getValue() == GIFT.getValue()) {
            return true;
        }
        return false;
    }
    
    /**
     * 是否为赠品
     */
    public static boolean isGift(int value) {
    	if (GIFT.getValue() == value || MANUAL_GIFT.getValue() == value || TMALL_PLATFORM_GIFT.getValue() == value) {
    		return true;
    	}
    	return false;
    }
    
    public static OrderLineType valueOf(int value) {
        switch (value) {
            case 1:
                return MAIN;
            case 2:
                return GIFT;
            case 3:
                return MANUAL_GIFT;
            case 4:
            	return TMALL_PLATFORM_GIFT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
