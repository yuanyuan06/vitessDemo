package io.vitess.enums;

/**
 * 促销活动主卖品范围
 * 
 * @author zhouzheng.deng
 * 
 */
public enum ProductScope {

    PRODUCT_SCOPE_FULL(1), // 全场
    PRODUCT_SCOPE_INCLUDE(5), // 仅限指定标签商品
    PRODUCT_SCOPE_EXCLUDE(10), // 全场且排除指定标签商品
    PRODUCT_SCOPE_INCLUDE_LABEL_AND_QTY(15); //全场且包含指定标签且指定数量

    private int value;

    private ProductScope(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ProductScope valueOf(int value) {
        switch (value) {
            case 1:
                return PRODUCT_SCOPE_FULL;
            case 5:
                return PRODUCT_SCOPE_INCLUDE;
            case 10:
                return PRODUCT_SCOPE_EXCLUDE;
            case 15:
                return PRODUCT_SCOPE_INCLUDE_LABEL_AND_QTY;
            default:
                throw new IllegalArgumentException();
        }
    }
}
