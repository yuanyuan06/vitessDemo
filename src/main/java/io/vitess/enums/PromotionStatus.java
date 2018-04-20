package io.vitess.enums;


/**
 * 促销活动状态
 * 
 * @author zhouzheng.deng
 * 
 */
public enum PromotionStatus {
    STATUS_DISABLE(0), // 停用
    STATUS_ENABLE(1), // 启用
    STATUS_IMPROVING(2), // 待完善
    STATUS_INVALID(3); // 作废

    private int value;

    private PromotionStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PromotionStatus valueOf(int value) {
        switch (value) {
            case 0:
                return STATUS_DISABLE;
            case 1:
                return STATUS_ENABLE;
            case 2:
                return STATUS_IMPROVING;
            case 3:
                return STATUS_INVALID;
            default:
                throw new IllegalArgumentException();
        }
    }
}
