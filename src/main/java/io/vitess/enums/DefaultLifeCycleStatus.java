package io.vitess.enums;

/**
 * @author YSH4807
 * @date 2018/4/11 11:30
 */
public enum DefaultLifeCycleStatus {

    CANCELED(0), // 作废
    CREATED(1); // 有效


    private int value;

    private DefaultLifeCycleStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public static DefaultLifeCycleStatus valueOf(int value) {
        switch (value) {
            case 0:
                return CANCELED;
            case 1:
                return CREATED;
            default:
                throw new IllegalArgumentException();
        }
    }
}
