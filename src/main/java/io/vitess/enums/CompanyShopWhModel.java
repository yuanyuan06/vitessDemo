package io.vitess.enums;

/**
 * @author YSH4807
 * @date 2018/4/11 11:26
 */
public enum CompanyShopWhModel{
    USE_BAOZUN_WMS(1, "使用宝尊wms"), // 使用宝尊wms
    NOT_USE_BAOZUN_WMS(2, "不使用宝尊wms"); // 不使用宝尊wms

    private int value;
    private String name;

    private CompanyShopWhModel(int value, String name){
        this.value = value;
        this.name = name;
    }

    public int getValue(){
        return value;
    }

    public String getName() {
        return name;
    }

    public static CompanyShopWhModel valueOf(int value){
        switch (value) {
            case 1:
                return USE_BAOZUN_WMS;
            case 2:
                return NOT_USE_BAOZUN_WMS;
            default:
                throw new IllegalArgumentException();
        }
    }
}