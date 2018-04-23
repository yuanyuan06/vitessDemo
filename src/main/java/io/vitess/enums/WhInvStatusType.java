package io.vitess.enums;


public enum WhInvStatusType {

	 // 销售相关库存状态
	WH_INVSTATUS_TYPE_SALES(1),
    // 非销售流程库存状态
	WH_INVSTATUS_TYPE_UN_SALES(2);

    private int value;

    private WhInvStatusType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WhInvStatusType valueOf(int value) {
        switch (value) {
            case 1:
                return WH_INVSTATUS_TYPE_SALES;
            case 2:
                return WH_INVSTATUS_TYPE_UN_SALES;
            default:
                throw new IllegalArgumentException();
        }
    }
}
