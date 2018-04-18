package io.vitess.enums;

/**
 * 平台业务类型
 * @author 李光辉
 * @date 2014年7月11日 下午2:52:28
 * @since
 */
public enum SalesOrderType {
    /** 淘宝商城订单 */
    PLATFORM_ONLINE_TB(50, "TB", "淘宝商城订单"),
    /** 淘宝商城手工订单 */
    PLATFORM_OFFLINE_TB(51, "TB", "淘宝商城手工订单"),
    /** 淘宝分销订单 */
    PLATFORM_ONLINE_TB_DISTRIBUTION(52, "TB", "淘宝分销订单"),
    /** 淘宝分销手工订单 */
    PLATFORM_OFFLINE_TB_DISTRIBUTION(53, "TB", "淘宝分销手工订单"),
    /** 淘宝经销订单 */
    PLATFORM_ONLINE_TB_DEALER(54, "TB", "淘宝经销订单"),
    /** 淘宝经销手工订单 */
    PLATFORM_OFFLINE_TB_DEALER(55, "TB", "淘宝经销手工订单"),
    /** 淘宝供货中心订单 */
    PLATFORM_ONLINE_TB_SUPPLY_CENTER(225, "TB", "淘宝供货中心订单"),
    /** 淘宝供货中心手工订单 */
    PLATFORM_OFFLINE_TB_SUPPLY_CENTER(226, "TB", "淘宝供货中心手工订单");
    
    private int value;
    
    private String platformPrefix;
    
    private String name;

    private SalesOrderType(int value, String platformPrefix, String name) {
        this.value = value;
        this.platformPrefix = platformPrefix;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getPlatformPrefix() {
        return platformPrefix;
    }

    public String getName() {
        return name;
    }

    public static SalesOrderType valueOf(int value) {
        switch (value) {
            case 50:
                return PLATFORM_ONLINE_TB;
            case 51:
                return PLATFORM_OFFLINE_TB;
            case 52:
                return PLATFORM_ONLINE_TB_DISTRIBUTION;
            case 53:
                return PLATFORM_OFFLINE_TB_DISTRIBUTION;
            case 54:
                return PLATFORM_ONLINE_TB_DEALER;
            case 55:
                return PLATFORM_OFFLINE_TB_DEALER;
            case 225:
                return PLATFORM_ONLINE_TB_SUPPLY_CENTER;
            case 226:
                return PLATFORM_OFFLINE_TB_SUPPLY_CENTER;
            default:
                throw new IllegalArgumentException();
        }
    }
}
