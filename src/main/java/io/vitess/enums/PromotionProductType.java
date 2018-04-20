package io.vitess.enums;

/**
 * 主卖品类型
 * 
 * @author yunlong.fan
 * 
 */
public enum PromotionProductType {

    APPLY_PRODUCT(1), // 按商品指定
    APPLY_CATEGORY(2); // 按标签指定

    private int value;

    private PromotionProductType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PromotionProductType valueOf(int value) {
        switch (value) {
            case 1:
                return APPLY_PRODUCT;
            case 2:
                return APPLY_CATEGORY;
            default:
                throw new IllegalArgumentException();
        }
    }
}
