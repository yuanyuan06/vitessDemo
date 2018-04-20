package io.vitess.enums;

/**
 * 派件时间类型
 * @author 李光辉
 * @date 2014年7月4日 下午4:39:11
 * @since
 */
public enum TransTimeType {
    /** 1.普通 */
    NORMAL(1, "普通"), 
    /** 5.当日 */
    CURRENT_DAY(5, "当日"), 
    /** 6.次日 */
    NEXT_DAY(6, "次日");

    private int value;
    private String name;

    private TransTimeType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static TransTimeType valueOf(int value) {
        switch (value) {
            case 1:
                return NORMAL;
            case 5:
                return CURRENT_DAY;
            case 6:
                return NEXT_DAY;
            default:
                throw new IllegalArgumentException();
        }
    }
}
