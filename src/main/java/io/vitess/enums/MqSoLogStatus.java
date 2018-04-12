package io.vitess.enums;

/**
 * @author YSH4807
 * @date 2018/4/11 10:31
 */
public enum MqSoLogStatus {

    NO_PAY_STATUS_WAITING(0, "未付款等待处理"), //未付款等待处理
    MQ_SO_STATUS_WAITING(2, "等待转换"), // 等待转换
    MQ_SO_STATUS_ERROR(5, "转换失败"), // 转换失败
    MQ_SO_STATUS_SUCCESS(10, "转换成功"); // 转换成功

    private int value;

    private String name;

    private MqSoLogStatus(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static MqSoLogStatus valueOf(int value) {
        switch (value) {
            case 0:
                return NO_PAY_STATUS_WAITING;
            case 2:
                return MQ_SO_STATUS_WAITING;
            case 5:
                return MQ_SO_STATUS_ERROR;
            case 10:
                return MQ_SO_STATUS_SUCCESS;
            default:
                throw new IllegalArgumentException();
        }
    }
}
