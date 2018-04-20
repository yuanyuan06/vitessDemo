package io.vitess.enums;

/**
 * 短信数据类型
 * @author hailiang.jiang
 * @date 2015年9月25日 下午1:46:25
 */
public enum DataType {
	SO(SlipType.SALES_ORDER.getValue(), "销售订单"),
	RF(SlipType.REFUND_REQUEST.getValue(), "退款申请"),
	RA(SlipType.RETURN_REQUEST.getValue(), "退换货申请");
	
	private int value;
	private String name;
	
	private DataType(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public static DataType valueOf(int value){
		switch (value) {
			case 2:
				return SO;
			case 3:
				return RA;
			case 5:
				return RF;
			default:
				throw new IllegalArgumentException();
		}
	}

}
