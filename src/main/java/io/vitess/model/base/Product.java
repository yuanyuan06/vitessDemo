package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import io.vitess.enums.ProductType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * 
 * @author fanht
 * 
 */
@Data
//@TableName("t_ma_sku")
public class Product extends SuperEntity{
	private static final long serialVersionUID = 5469350702688787492L;

	public static final Integer SKU_STATUS_ONSALES = 5;

	public static final Integer SKU_STATUS_OFFSALES = 1;

	public static final Integer SKU_STATUS_DISABLED = 0;

	public static final Integer SKU_STOCK_STATUS_ENOUGH = 5; // 现货

	public static final Integer SKU_STOCK_STATUS_OUT_OF_STOCK = 0; // 缺货

	public static final Integer SKU_STOCK_STATUS_RESERVE = 1; // 预订

	public static final Integer PRODUCT_WARRANTY_CARD_TYPE_NOT_NEEDED = new Integer(1);// 1:无需保修卡

	public static final Integer PRODUCT_WARRANTY_CARD_TYPE_NEEDED = new Integer(2);// 2:需要保修卡

	public static final Integer PRODUCT_WARRANTY_CARD_TYPE_NOT_FORCED = new Integer(3);// 3:不强制校验保修卡

	private String code;

	private String name;

	private String supplierSkuCode;

	private ProductType type;

	private String salesModesStr;

	private Integer salesMode;

	private String manufacture;

	private BigDecimal grossWeight;

	private BigDecimal netWeight;

	private BigDecimal length;

	private BigDecimal width;

	private BigDecimal height;

	private Boolean isSnSku;

	private String enName;

	private int version;

	private Date createTime = new Date();

	private Date lastModifyTime = new Date();

	private String brandCategory1;

	private Boolean isAvailable = Boolean.TRUE;

	private Boolean isGift = Boolean.FALSE;

	private Boolean isRailway;

	private String prodCategory;

	private Integer warrantyCardType = PRODUCT_WARRANTY_CARD_TYPE_NOT_NEEDED;

	private String brandCode;

	private String brandName;

	private String supplierCode;

	private String supplierName;

	private BigDecimal listPrice;

	private String businessRegionType;

	private String whCustomerCode;

	private Integer brandSpecialType;

	private Integer spType;
	
	public static Integer SP_TYPE_NORMAL = 0;
	
	public static Integer SP_TYPE_SVC_CARD = 1;
	
	public static Integer SP_TYPE_ETICKET_ACTUAL = 2;
	
	public static Integer SP_TYPE_SVC_MERGE = 3; //N合一
	
}
