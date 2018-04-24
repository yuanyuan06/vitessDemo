package io.vitess.enums;

public enum SkuSpecialType {

	DEFAULT(0), // 默认类型
    STUDENT_PRICE_SKU(1); // 学生价商品

    private int value;

    SkuSpecialType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SkuSpecialType valueOf(int value) {
        switch (value) {
            case 0:
                return DEFAULT;
            case 1:
                return STUDENT_PRICE_SKU;
            default:
                return null;
        }
    }
    
}
