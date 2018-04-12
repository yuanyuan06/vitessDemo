package io.vitess.enums;

import java.util.HashMap;

/**
 * @author YSH4807
 * @date 2018/4/11 11:24
 */
public enum SkuSplitType {

    SPLIT_BY_SKU_DEFAULT_WH(1, "按店铺SKU默认仓拆分订单"),
    NO_SPLIT(2, "不拆分"),
    /** 当匹配到多个仓库时，整单按店铺默认仓库 **/
    NO_SPLIT_BY_SKU_DEFAULT_WH(3, "按SKU指定仓库"),
    SPLIT_BY_SKU_NUM(4, "按商品数量拆"),
    O2O_SALES_SERVICE_TYPE(5, "按O2O订单服务类型拆"),//有服务+无服务+赠品 ==> [有服务+赠品][无服务] 这个已作废fanht
    DESIGNATED_WH(6, "分仓"),	// 根据指定仓库发货,分仓哈根达斯
    DESIGNATED_WH_BY_SKU(7, "指定sku分仓"),	// nike
    SPLIT_BY_STORE(8,"按门店分仓拆单");
    private static HashMap<Integer, SkuSplitType> map;

    private int value;

    private String name;

    private SkuSplitType(int value, String name) {
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

    private static void initMap(int key, SkuSplitType type) {
        if (map == null) {
            map = new HashMap<Integer, SkuSplitType>(2);
        }
        map.put(key, type);
    }

    public static SkuSplitType valueOf(int value) {
        SkuSplitType type = map.get(value);
        if (type != null) {
            return type;
        }

        throw new IllegalArgumentException();
    }

    public static SkuSplitType nullValueOf(int value) {
        SkuSplitType type = map.get(value);
        if (type != null) {
            return type;
        }
        return NO_SPLIT;
    }
}
