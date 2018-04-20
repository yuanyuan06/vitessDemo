package io.vitess.enums;

public enum BranchWarehouseWmsType {
	BZ_WMS_SYSTEM(1), //1：宝尊WMS系统
	O2O_SYSTEM(3),  //3：O2O系统
	MIX_WAREHOUSE(5); //5：混合仓
	
    private int value;

    private BranchWarehouseWmsType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BranchWarehouseWmsType valueOf(int value) {
        switch (value) {
            case 1:
                return BZ_WMS_SYSTEM;
            case 3:
                return O2O_SYSTEM;
            case 5:
            	return MIX_WAREHOUSE;
            default:
                throw new IllegalArgumentException();
        }
    }
}
