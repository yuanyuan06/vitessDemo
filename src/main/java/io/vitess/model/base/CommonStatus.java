package io.vitess.model.base;

public enum CommonStatus {

    STATUS_ENABLE(1), // 有效
    STATUS_DISABLE(0); // 作废

    private int value;

    private CommonStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CommonStatus valueOf(int value) {
        switch (value) {
            case 0:
                return STATUS_DISABLE;
            case 1:
                return STATUS_ENABLE;
            default:
                throw new IllegalArgumentException();
        }
    }
}
