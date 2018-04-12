package io.vitess.enums;

/**
 * @author YSH4807
 * @date 2018/4/11 10:30
 */
public enum SoSpecialType {


    /**
     * 默认类型
     */
    DEFAULT(0, "默认类型"),
    /**
     * apple学生价订单
     */
    STUDENT_PRICE_ORDER(1, "apple学生价订单"),
    /**
     * 物流宝订单
     */
    WLB_ORDER(2, "物流宝订单"),
    O2O_ORDER_POST_SALES_SERVICE(3, "O2O有售后订单"),
    O2O_ORDER_NOT_POST_SALES_SERVICE(4, "O2O无售后订单"),
    ETICKET_ORDER(5,"电子券订单"),
    ETICKET_ACTUAL_ORDER(6,"电子券实物订单"),
    AUTO_DELIVERY_ORDER(7,"自动发货订单"),
    AUTO_DELIVERY_INV_ORDER(8,"自动发货有库存订单"),
    /**
     * 物流宝订单
     */
    WLB_COLLECT_ORDER(9, "菜鸟集货"),
    /**
     * 秒杀商品订单
     */
    APPOINTMENT_ORDER(10,"预约过仓订单");

    private int value;
    private String name;

    private SoSpecialType(int value, String name){
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue(){
        return value;
    }
    /**
     * 转化为PACS对应的类型
     * @methodName com.jumbo.model.sales.enums.SoSpecialType.convertToPacsSoSpecialType
     * @description
     * @author hailiang.jiang
     * @date 2015年3月5日 下午1:50:13
     * @version: v1.0.0
     */
    public static int convertToPacsSoSpecialType(SoSpecialType type) {
        if (O2O_ORDER_POST_SALES_SERVICE.getValue() == type.getValue() || O2O_ORDER_NOT_POST_SALES_SERVICE.getValue() == type.getValue()) {
            return DEFAULT.value;
        }else if(ETICKET_ORDER.equals(type)){
            return 6;
        }else if(ETICKET_ACTUAL_ORDER.equals(type)){
            return 7;
        }else if(AUTO_DELIVERY_ORDER.equals(type)){
            return 0;
        }else if(AUTO_DELIVERY_INV_ORDER.equals(type)){
            return 0;
        }else if(APPOINTMENT_ORDER.equals(type)){
            return 0;
        } else {
            return type.value;
        }
    }

    /**
     * 是否为O2O订单
     * @methodName com.jumbo.model.sales.enums.SoSpecialType.isO2oOrder
     * @description
     * @author hailiang.jiang
     * @date 2015年2月4日 下午4:09:37
     * @version: v1.0.0
     */
    public static boolean isO2oOrder(SoSpecialType speicalType) {
        if (speicalType == null) {
            return false;
        }
        return isO2oOrder(speicalType.getValue());
    }

    /**
     * 是否为O2O订单
     * @methodName com.jumbo.model.sales.enums.SoSpecialType.isO2oOrder
     * @description
     * @author hailiang.jiang
     * @date 2015年2月4日 下午4:06:02
     * @version: v1.0.0
     */
    public static boolean isO2oOrder(int value) {
        SoSpecialType type = valueOf(value);
        if (type != null && (type.getValue() == O2O_ORDER_POST_SALES_SERVICE.getValue() || type.getValue() == O2O_ORDER_NOT_POST_SALES_SERVICE.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * 是否为物流宝订单
     * @author hailiang.jiang
     * @date 2015年7月22日 下午5:34:26
     * @param value
     * @return
     */
    public static boolean isWlbOrder(int value) {
        SoSpecialType type = valueOf(value);
        return isO2oOrder(type);
    }

    /**
     * 是否为物流宝订单
     * @author hailiang.jiang
     * @date 2015年7月22日 下午5:34:26
     * @param value
     * @return
     */
    public static boolean isWlbOrder(SoSpecialType type) {
        if (type != null && type.getValue() == WLB_ORDER.getValue()) {
            return true;
        }
        return false;
    }


    /**
     * 是否为微软自动发货订单
     * @author bacui.lu
     * @date 2016年2月19日
     * @param so
     * @return
     */
    public static boolean isAutoDeliverySo(SoSpecialType speicalType) {
        if (speicalType == null) {
            return false;
        }
        if (speicalType.equals(SoSpecialType.AUTO_DELIVERY_INV_ORDER) || speicalType.equals(SoSpecialType.AUTO_DELIVERY_ORDER)) {
            return true;
        }
        return false;
    }


    public static SoSpecialType valueOf(int value){
        switch (value) {
            case 0:
                return DEFAULT;
            case 1:
                return STUDENT_PRICE_ORDER;
            case 2:
                return WLB_ORDER;
            case 3:
                return O2O_ORDER_POST_SALES_SERVICE;
            case 4:
                return O2O_ORDER_NOT_POST_SALES_SERVICE;
            case 5:
                return ETICKET_ORDER;
            case 6:
                return ETICKET_ACTUAL_ORDER;
            case 7:
                return AUTO_DELIVERY_ORDER;
            case 8:
                return AUTO_DELIVERY_INV_ORDER;
            case 9:
                return WLB_COLLECT_ORDER;
            case 10:
                return APPOINTMENT_ORDER;
            default:
                return null;
        }
    }
}
