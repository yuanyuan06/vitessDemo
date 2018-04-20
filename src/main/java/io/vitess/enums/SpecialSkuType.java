package io.vitess.enums;

import java.util.HashMap;

/**
 * 特殊商品类型
 * 
 * @classname com.jumbo.model.baseinfo.enums.SpecialSkuType
 * @description TODO
 * @author hailiang.jiang
 * @date 2015年2月4日 上午10:55:30
 * @version: v1.0.0
 * @see
 */
public enum SpecialSkuType {
	O2O_POST_SALE_SERVICE(0, "O2O有服务商品");

	private static HashMap<Integer, SpecialSkuType> map;

	private int value;
	private String name;

	private SpecialSkuType(int value, String name) {
		this.value = value;
		this.name = name;

		initMap(value, this);
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	private static void initMap(int key, SpecialSkuType type) {
		if (map == null) {
			map = new HashMap<Integer, SpecialSkuType>(2);
		}
		map.put(key, type);
	}

	public static SpecialSkuType valueOf(int value) {
		SpecialSkuType type = map.get(value);
		if (type != null) {
			return type;
		}

		throw new IllegalArgumentException();
	}
	
	public static SpecialSkuType nullValueOf(int value) {
		SpecialSkuType type = map.get(value);
		if (type != null) {
			return type;
		}
		return null;
	}
	
}
