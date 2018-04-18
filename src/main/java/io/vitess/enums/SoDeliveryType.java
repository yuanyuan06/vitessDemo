package io.vitess.enums;

public enum SoDeliveryType {
	DEFAULT(0, "默认类型"), 
	CUSTOMERS_FROM_CARRYING(1, "自提"),
	HOME_DELIVERY_SERVICE(2, "送货上门");

	private int value;
	private String name;

	private SoDeliveryType(int value, String name){
		this.value = value;
		this.name = name;
	}

	public String getName() {
        return name;
    }

    public int getValue(){
		return value;
	}
    
    
	public static SoDeliveryType valueOf(int value){
		switch (value) {
		case 0:
			return DEFAULT;
		case 1:
			return CUSTOMERS_FROM_CARRYING;
		case 2:
			return HOME_DELIVERY_SERVICE;
		default:
			return null;
		}
	}
	
}
