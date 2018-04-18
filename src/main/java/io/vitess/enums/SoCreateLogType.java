package io.vitess.enums;

/**
 * 订单导入创建操作日志类型
 * 
 * @author wudan
 * 
 */
public enum SoCreateLogType {

    TB(1), // 淘宝订单
    OFFLINE(2),// 淘宝订单
    PERIOD(3), // 周期购
    GIFT_REISSUE(4);//赠品补发单


    private int value;

    private SoCreateLogType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SoCreateLogType valueOf(int value) {
        switch (value) {
            case (1):
                return TB;
            case (2):
                return OFFLINE;
            case (3):
                return PERIOD;
            case (4):
                return GIFT_REISSUE;
            default:
                throw new IllegalArgumentException();
        }
    }
}
