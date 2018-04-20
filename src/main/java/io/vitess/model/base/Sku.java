package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.SkuSpecialType;
import lombok.Data;

/**
 * SKU
 * 
 * @author fanht
 * 
 */
@Data
@TableName("t_ma_inv_sku")
public class Sku extends SuperEntity {
    private static final long serialVersionUID = 2960655395582098457L;

    @TableField("CODE")
    private String code;

    @TableField("SUPPLIER_CODE")
    private String supplierCode;

    @TableField("BAR_CODE")
    private String barCode;

    @TableField("KEY_PROPERTIES")
    private String keyProperties;

    @TableField("NAME")
    private String name;

    @TableField("EN_NAME")
    private String enName;

    @TableField("IDX_EXT_CODE")
    private String extensionCode1;

    @TableField("EXT_CODE2")
    private String extensionCode2;

    @TableField("EXT_CODE3")
    private String extensionCode3;

    @TableField("VERSION")
    private int version;

    @TableField("PRODUCT_ID")
    private Product product;

    @TableField("COLOR")
    private String color;

    @TableField("COLOR_NAME")
    private String colorName;

    @TableField("SKU_SIZE")
    private String skuSize;

    @TableField("SPECIAL_TYPE")
    private SkuSpecialType specialType = SkuSpecialType.DEFAULT;

    @TableField("ITEM_ID")
    private String itemId;

    @TableField("SHIPPING_METHODS")
    private String shippingMethods;


}
