package io.vitess.enums;


public enum CompanyShopManagerMode {
    /**
     * 自营
     */
    NORMAL(1), // 自营
    THREEPL_ASYNCHRO(2), // 3pl 非实时
    THREEPL_SYNCHRO(3), // 3pl 实时
    VMI(4);// 虚拟仓库

    private int value;

    private CompanyShopManagerMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CompanyShopManagerMode valueOf(int value) {
        switch (value) {
            case 1:
                return NORMAL;
            case 2:
                return THREEPL_ASYNCHRO;
            case 3:
                return THREEPL_SYNCHRO;
            case 4:
                return VMI;
            default:
                throw new IllegalArgumentException();
        }
    }
}
