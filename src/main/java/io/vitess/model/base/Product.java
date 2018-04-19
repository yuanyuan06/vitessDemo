package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.ProductType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * 
 * @author fanht
 * 
 */
@TableName("t_ma_sku")
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

	@TableField("CODE")
	private String code;

	@TableField("NAME")
	private String name;

	@TableField("SUPPLIER_SKU_CODE")
	private String supplierSkuCode;

	@TableField("loxia.dao.support.GenericEnumUserType")
	private ProductType type;

	@TableField("SALES_MODES")
	private String salesModesStr;

	@TableField("SALES_MODE")
	private Integer salesMode;

	@TableField("MANUFACTURE")
	private String manufacture;

	@TableField("GROSS_WEIGHT")
	private BigDecimal grossWeight;

	@TableField("NET_WEIGHT")
	private BigDecimal netWeight;

	@TableField("LENGTH")
	private BigDecimal length;

	@TableField("WIDTH")
	private BigDecimal width;

	@TableField("HEIGHT")
	private BigDecimal height;

	@TableField("IS_SN_SKU")
	private Boolean isSnSku;

	@TableField("EN_NAME")
	private String enName;

	@TableField("VERSION")
	private int version;

	@TableField("CREATE_TIME")
	private Date createTime = new Date();

	@TableField("LAST_MODIFY_TIME")
	private Date lastModifyTime = new Date();

	@TableField("BRAND_CATEGORY1")
	private String brandCategory1;

	@TableField("IS_AVAILABLE")
	private Boolean isAvailable = Boolean.TRUE;

	@TableField("IS_GIFT")
	private Boolean isGift = Boolean.FALSE;

	@TableField("IS_RAILWAY")
	private Boolean isRailway;

	@TableField("PROD_CATEGORY")
	private String prodCategory;

	@TableField("WARRANTY_CARD_TYPE")
	private Integer warrantyCardType = PRODUCT_WARRANTY_CARD_TYPE_NOT_NEEDED;

	@TableField("BRAND_CODE")
	private String brandCode;

	@TableField("BRAND_NAME")
	private String brandName;

	@TableField("SUPPLIER_CODE")
	private String supplierCode;

	@TableField("SUPPLIER_NAME")
	private String supplierName;

	@TableField("LIST_PRICE")
	private BigDecimal listPrice;

	@TableField("BUSINESS_REGION_TYPE")
	private String businessRegionType;

	@TableField("WH_CUSTOMER_CODE")
	private String whCustomerCode;

	@TableField("BRAND_SPECIAL_TYPE")
	private Integer brandSpecialType;

	@TableField("SP_TYPE")
	private Integer spType;
	
	public static Integer SP_TYPE_NORMAL = 0;
	
	public static Integer SP_TYPE_SVC_CARD = 1;
	
	public static Integer SP_TYPE_ETICKET_ACTUAL = 2;
	
	public static Integer SP_TYPE_SVC_MERGE = 3; //N合一
	
}
