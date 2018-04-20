package io.vitess.enums;

import io.vitess.constants.Constants;
import io.vitess.constants.InterfaceCodeConstants;


/**
 * 短信数据类型
 * @author hailiang.jiang
 * @date 2015年9月25日 下午1:46:25
 */
public enum SmsType {
	SPLIT_ORDER_AUTO(1011, "自动拆单"),
	SPLIT_ORDER_MANUAL(1012, "手工拆单"),
	SPLIT_ORDER_SHIPPING(1013, "根据WMS的作业单拆单");
	
	private int value;
	private String name;
	
	public static String getInterfaceCodeBySmsType(SmsType smsType) {
		if (SPLIT_ORDER_AUTO.equals(smsType) || SPLIT_ORDER_MANUAL.equals(smsType) || SPLIT_ORDER_SHIPPING.equals(smsType)) {
			return InterfaceCodeConstants.SMS2_2;
		}
		throw new IllegalArgumentException("找不到指定的短信类型[SmsType]");
	}
	
	public static SmsType getSpiltOrderSmsDataType(String code) {
		if (Constants.AUTO.equals(code)) {
			return SPLIT_ORDER_AUTO;
		}
		if (Constants.MANUAL.equals(code)) {
			return SPLIT_ORDER_MANUAL;
		}
		if (Constants.SHIPPING.equals(code)) {
			return SPLIT_ORDER_SHIPPING;
		}
		throw new IllegalArgumentException("找不到指定的拆单类型[" + code + "]");
	}
	
	private SmsType(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public static SmsType valueOf(int value) {
		switch (value) {
			case 1011:
				return SPLIT_ORDER_AUTO;
			case 1012:
				return SPLIT_ORDER_MANUAL;
			case 1013:
				return SPLIT_ORDER_SHIPPING;
			default:
				throw new IllegalArgumentException();
		}
	}

}
