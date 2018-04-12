package io.vitess.enums;

/**
 * @author YSH4807
 * @date 2018/4/11 11:08
 */
public enum MqSoPackingInfoLevel {

    BY_ORDER(1),// 整单级别
    BY_PRODUCT(2);// 商品行级别

    private int value;

    private MqSoPackingInfoLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MqSoPackingInfoLevel valueOf(int value) {
        switch (value) {
            case 1:
                return BY_ORDER;
            case 2:
                return BY_PRODUCT;
            default:
                throw new IllegalArgumentException();
        }
    }
}