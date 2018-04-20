package io.vitess.enums;

public enum BranchWarehouseStatus {
    // 启用
    ENABLE(1),
    //禁用
    DISENABLE(0);

    private int value;

    private BranchWarehouseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BranchWarehouseStatus valueOf(int value) {
        switch (value) {
            case 1:
                return ENABLE;
            case 0:
                return DISENABLE;
            default:
                throw new IllegalArgumentException();
        }
    }
}
