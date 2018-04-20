package io.vitess.enums;

/**
 * 销售模式
 * 
 * @author 李光辉
 * @date 2014年7月4日 下午4:02:01
 * @since
 */
public enum SalesMode {
	// 付款经销
	PAYMENT(0, "付款经销", "0"),
	// 代销sale by proxy
	CONSIGNMENT(1, "代销", "1"),
	// 结算经销
	SETTLEMENT(2, "结算经销", "2"),
	// 结算经销+代销
	SETTLEMENT_OR_CONSIGNMENT(3, "结算经销+代销", "1,2");

	public static final String CHOOSEOPTIONNAME = "salesMode";

	private int value;

	private String name;
	
	private String salesModesStr;

	private SalesMode(int value, String name, String salesModesStr){
		this.value = value;
		this.name = name;
		this.salesModesStr = salesModesStr;
	}

	public int getValue(){
		return value;
	}

	public String getName(){
		return name;
	}

	public String getSalesModesStr() {
        return salesModesStr;
    }

    public static SalesMode valueOf(int value){
		switch (value) {
		case 0:
			return PAYMENT;
		case 1:
			return CONSIGNMENT;
		case 2:
			return SETTLEMENT;
		case 3:
			return SETTLEMENT_OR_CONSIGNMENT;
		default:
			throw new IllegalArgumentException();
		}
	}

}
