package io.vitess.enums;

/**
 * 促销活动类型
 * 
 * @author zhouzheng.deng
 * 
 */
public enum PromotionType {
    /** 满赠 */
    FULL_GIFT(1),
    /** 阶梯满赠 */
    LADDER_FULL_GIFT(2),
    /** 买赠 */
    BUY_GIFT(3),
    /** 阶梯买赠 */
    LADDER_BUY_GIFT(4);

    private int value;

    private PromotionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PromotionType valueOf(int value) {
        switch (value) {
            case 1:
                return FULL_GIFT;
            case 2:
                return LADDER_FULL_GIFT;
            case 3:
                return BUY_GIFT;
            case 4:
                return LADDER_BUY_GIFT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
