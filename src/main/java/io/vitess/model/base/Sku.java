package io.vitess.model.base;

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
//@TableName("t_ma_inv_sku")
public class Sku extends SuperEntity {
    private static final long serialVersionUID = 2960655395582098457L;

    private String code;

    private String supplierCode;

    private String barCode;

    private String keyProperties;

    private String name;

    private String enName;

    private String extensionCode1;

    private String extensionCode2;

    private String extensionCode3;

    private int version;

    private Long product;

    private String color;

    private String colorName;

    private String skuSize;

//    private SkuSpecialType specialType = SkuSpecialType.DEFAULT;
    private Integer specialType = SkuSpecialType.DEFAULT.getValue();

    private String itemId;

    private String shippingMethods;


}
