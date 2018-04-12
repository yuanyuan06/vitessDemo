package io.vitess.enums;

import java.util.HashMap;

/**
 * @author YSH4807
 * @date 2018/4/11 11:25
 */
public enum DefInvoiceTitleType {

    PERSONAL(1, "个人"),
    RECEIVER(2, "收货人姓名");

    private static HashMap<Integer, DefInvoiceTitleType> map;

    private int value;

    private String name;

    private DefInvoiceTitleType(int value, String name) {
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

    private static void initMap(int key, DefInvoiceTitleType type) {
        if (map == null) {
            map = new HashMap<Integer, DefInvoiceTitleType>();
        }
        map.put(key, type);
    }

    public static DefInvoiceTitleType valueOf(int value) {
        DefInvoiceTitleType type = map.get(value);
        if (type != null) {
            return type;
        }
        throw new IllegalArgumentException();
    }

    public static DefInvoiceTitleType nullValueOf(int value) {
        DefInvoiceTitleType type = map.get(value);
        if (type != null) {
            return type;
        }
        return PERSONAL;
    }
}
