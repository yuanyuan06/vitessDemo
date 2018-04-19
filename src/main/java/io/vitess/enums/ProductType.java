package io.vitess.enums;

public enum ProductType {
    MATERIAL_OBJECT(0), 	// 实物
    VIRTUAL_NO_GOODS(1), 	// 软件-无库存
    VIRTUAL_GOODS(2);		// 软件-有库存

    private int value;

    private ProductType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ProductType valueOf(int value) {
        switch (value) {
            case 0:
                return MATERIAL_OBJECT;
            case 1:
                return VIRTUAL_NO_GOODS;
            case 2:
            	return VIRTUAL_GOODS;
            default:
                throw new IllegalArgumentException();
        }
    }
}
