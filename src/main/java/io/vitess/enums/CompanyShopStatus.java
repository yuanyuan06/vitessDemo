package io.vitess.enums;

/**
 * @author YSH4807
 * @date 2018/4/11 11:25
 */
public enum CompanyShopStatus {

    DISABLE(0), // 不使用
    OPEN(1), // 开店
    CLOSED(10); // 关店

    private int value;

    private CompanyShopStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CompanyShopStatus valueOf(int value) {
        switch (value) {
            case 0:
                return DISABLE;
            case 1:
                return OPEN;
            case 10:
                return CLOSED;
            default:
                throw new IllegalArgumentException();
        }
    }
}
