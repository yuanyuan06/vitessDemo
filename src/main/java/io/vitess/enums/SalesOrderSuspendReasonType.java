package io.vitess.enums;


/**
 * 订单挂起的的原因
 * 
 * @author hailiang.jiang
 * @date 2014年7月31日
 * @description PendingOrderType
 */
public enum SalesOrderSuspendReasonType {
    PROVINCE_CITY_DISTINECT_IS_EMPTY(1, "省市区为空"), 
    HK_TW_MC_ORDER(2, "港澳台订单"), 
    PRE_SALES_ORDER(3, "预售订单"), 
    PURCHASE_CYCLE_ORDER(4, "周期购订单"), 
    MEMO_SO(5, "自动过仓至ERP且有备注且不忽略备注"), 
    COPY_SO(6, "复制产生的新单"), 
    SPLIT_SO(7, "拆单产生的新单"), 
    NO_PAY(8, "等待付款"), 
    O2O_MIX_WAREHOUSE(9, "O2O订单属于混合仓"), 
    EN_PROVINCE_CITY_DISTINECT_IS_EMPTY(10, "地址需要翻译"), 
    CARE_ORDER(11, "特殊关怀订单"), 
    CLOUD_STACK_ORDER(12, "云栈订单"),
    SO_CONTAIN_SPECIAL_SKU(13, "含特殊商品挂起"), 
    O2O_POST_SALES_SERVICE(14,"O2O有服务订单挂起"),
    ETICKET_ACTUAL_ORDER(15,"电子券实物订单"),
    SALES_MODE(16,"销售模式"),
    HANG_BY_ABROAD_ORDER(17,"非大陆地区订单自动挂起")
    ;

    private int value;

    private String name;

    private SalesOrderSuspendReasonType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static SalesOrderSuspendReasonType valueOf(int value) {
        switch (value) {
            case 1:
                return PROVINCE_CITY_DISTINECT_IS_EMPTY;
            case 2:
                return HK_TW_MC_ORDER;
            case 3:
                return PRE_SALES_ORDER;
            case 4:
                return PURCHASE_CYCLE_ORDER;
            case 5:
                return MEMO_SO;
            case 6:
                return COPY_SO;
            case 7:
                return SPLIT_SO;
            case 8:
                return NO_PAY;
            case 9:
            	return O2O_MIX_WAREHOUSE;
            case 10:
                return EN_PROVINCE_CITY_DISTINECT_IS_EMPTY;
            case 11:
                return CARE_ORDER;
            case 12:
                return CLOUD_STACK_ORDER;
            case 13:
            	return SO_CONTAIN_SPECIAL_SKU;
            case 14:
            	return O2O_POST_SALES_SERVICE;
            case 15:
            	return ETICKET_ACTUAL_ORDER;
            case 17:
            	return HANG_BY_ABROAD_ORDER;
            default:
                throw new IllegalArgumentException();
        }
    }

}
