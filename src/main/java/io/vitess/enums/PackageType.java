package io.vitess.enums;

/**
 * @author YSH4807
 * @date 2018/4/11 11:08
 */
public enum PackageType {

    /** 10.赠送礼品卡 */
    GIFT_CARD(10, "赠送礼品卡"),
    /** 20.Coach保修卡 */
    COACH_GUARANTEE_CARD(20, "Coach保修卡"),
    /** 30.商品特殊包装 */
    SPECIAL_PACKAGE(30, "商品特殊包装"),
    /** 50.商品特殊印制 */
    SPECIAL_PRINTED(50, "商品特殊印制"),
    /** 60.哈根达斯兑换地特殊处理类型 */
    HAAGENDAZS_EXCHANGE(60, "哈根达斯兑换地特殊处理类型"),
    /** 70.CK礼盒 */
    CK_GIFT_BOX(70, "CK礼盒");

    private int value;
    private String name;

    private PackageType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static PackageType valueOf(int value) {
        switch (value) {
            case 10:
                return GIFT_CARD;
            case 20:
                return COACH_GUARANTEE_CARD;
            case 30:
                return SPECIAL_PACKAGE;
            case 50:
                return SPECIAL_PRINTED;
            case 60:
                return HAAGENDAZS_EXCHANGE;
            case 70:
                return CK_GIFT_BOX;
            default:
                throw new IllegalArgumentException();
        }
    }
}
