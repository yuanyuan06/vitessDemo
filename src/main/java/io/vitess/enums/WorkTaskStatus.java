package io.vitess.enums;

public enum WorkTaskStatus {
    RUNNING(1), BLOCKED(2), SUSPEND(3), END(10);

    private int value;

    private WorkTaskStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WorkTaskStatus valueOf(int value) {
        switch (value) {
            case 1:
                return RUNNING;
            case 2:
                return BLOCKED;
            case 3:
                return SUSPEND;
            case 10:
                return END;
            default:
                throw new IllegalArgumentException();
        }
    }
}
