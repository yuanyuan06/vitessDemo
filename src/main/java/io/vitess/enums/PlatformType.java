package io.vitess.enums;

/**
 * @author YSH4807
 * @date 2018/4/11 10:31
 */
public enum PlatformType {

    /**
     * 淘宝
     */
    TAOBAO_PLATFORM(1),
    /**
     * 淘宝分销
     */
    TB_FENXIAO(5),
    /**
     * 淘宝分销下的供销商订单
     */
    TB_DEALER(6),
    /**
     * 淘宝天猫O2O（这个类型作废）
     */
    TAOBAO_TM_O2O(8);

    private int value;

    private PlatformType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PlatformType valueOf(int value) {
        switch (value) {
            case 1:
                return TAOBAO_PLATFORM;
            case 5:
                return TB_FENXIAO;
            case 6:
                return TB_DEALER;
            case 8:
                return TAOBAO_TM_O2O;
            default:
                throw new IllegalArgumentException();
        }
    }
}
