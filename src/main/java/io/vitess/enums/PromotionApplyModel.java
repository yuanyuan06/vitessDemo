package io.vitess.enums;

/**
 * 活动规则应用方式
 * 
 * @author yunlong.fan
 * 
 */
public enum PromotionApplyModel {

    APPLY_ALL(1), // 依次应用
    APPLY_ONECE(2); // 赠品不足则中止

    private int value;

    private PromotionApplyModel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PromotionApplyModel valueOf(int value) {
        switch (value) {
            case 1:
                return APPLY_ALL;
            case 2:
                return APPLY_ONECE;
            default:
                throw new IllegalArgumentException();
        }
    }
}
