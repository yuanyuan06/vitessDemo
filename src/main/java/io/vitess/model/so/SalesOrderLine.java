package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.OrderLineType;
import io.vitess.model.base.Sku;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@TableName("t_td_sales_order_line")
public class SalesOrderLine extends SuperEntity{

	private static final long serialVersionUID = 8079539825879918172L;

	/** 拆单原始SalesOrderLine的ID号 **/
	@TableField("SPLIT_SOURCE_LINE_ID")
	private Long splitSourceLineId;

	/** 销售订单ID，关联SALES_ORDER_ID */
	@TableField("SALES_ORDER_ID")
	private SalesOrder salesOrder;

	/** 原外部平台订单行标识 */
	@TableField("PLATFORM_ORDER_LINE_CODE")
	private String platformOrderLineCode;

	/** 行类型，主卖品行;赠品行 */
	@TableField("ORDER_LINE_TYPE")
	private OrderLineType orderLineType;

	/** SKU */
	@TableField("SKU_ID")
	private Sku sku;

	/** EXTENTION_CODE（ERP的EXTENTION_CODE1） */
	@TableField("EXTENTION_CODE")
	private String extentionCode;

	/** SKU_CODE（ERP的JM_SKU_CODE） */
	@TableField("SKU_CODE")
	private String skuCode;

	/** SKU_NAME */
	@TableField("SKU_NAME")
	private String skuName;
	
	/** PLATFORM_SKU_NAME */
	@TableField("PLATFORM_SKU_NAME")
	private String platformSkuName;

	/** BAR_CODE */
	@TableField("BAR_CODE")
	private String barCode;

	/** 扩展属性 */
	@TableField("KEY_PROPERTIES")
	private String keyProperties;
	
	/** 是否组合商品     */
	@TableField("IS_COMBO_SKU")
    private Boolean isComboSku = Boolean.FALSE;
    
    /** 组合商品编码：若不是组合商品，该值为空， 否则值表示为extCode（该值为组合商品的ExtCode）  */
    @TableField("COMBO_SKU_EXT_CODE_ORGIN")
    private String comboSkuExtCodeOrgin;

    /**商品行第一次创建时的数量**/
    @TableField("INIT_QTY")
	private Integer initQty;
	
	/** 数量 */
	@TableField("QUANTITY")
	private Integer quantity;

	/** 吊牌价 */
	@TableField("LIST_PRICE")
	private BigDecimal listPrice;

	/** 销售单价(折前单价) */
	@TableField("UNIT_PRICE")
	private BigDecimal unitPrice;

	/** 销售金额总计=销售单价x数量 */
	@TableField("TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	/** 行折扣金额 */
	@TableField("LINE_DISCOUNT")
	private BigDecimal lineDiscount;

	/** 平摊后行折扣金额=行折扣金额+订单平摊到行的折扣金额 */
	@TableField("TOTAL_DISCOUNT")
	private BigDecimal totalDiscount;

	/** 折扣后金额总计=销售金额总计-平摊后行折扣金额 */
	@TableField("TOTAL_AMOUNT_AFTER_DISCOUNT")
	private BigDecimal totalAmountAfterDiscount;

	/** 折扣后单价=折扣后金额总计/数量，保留两位小数 */
	@TableField("UNIT_PRICE_AFTER_DISCOUNT")
	private BigDecimal unitPriceAfterDiscount;

	/** 虚拟货币支付金额(如积分抵扣),由整单平摊过来 */
	@TableField("VIRTUAL_AMOUNT")
	private BigDecimal virtualAmount;

	/** 开票金额总计=折扣后金额总计-虚拟货币支付金额 */
	@TableField("INVOICE_TOTAL_AMOUNT")
	private BigDecimal invoiceTotalAmount;

	/** 开票单价=开票金额总计/数量，保留两位小数 */
	@TableField("INVOICE_UNIT_PRICE")
	private BigDecimal invoiceUnitPrice;
	
	/** 总共使用积分 */
	@TableField("TOTAL_POINT_USED")
    private BigDecimal totalPointUsed;

	/** 保修时长(按月计) */
	@TableField("WARRANTY_MONTHS")
	private Integer warrantyMonths;

	/** 平台分仓编码 */
	@TableField("PLATFORM_WH_CODE")
	private String platformWhCode;

	/** 平台订单来源信息备注 */
	@TableField("SOURCE_REMARK")
	private String sourceRemark;

	/** 活动来源 */
	@TableField("ACTIVITY_SOURCE")
	private String activitySource;

	/** 赠品日志 */
	private List<SoPromotionApplyLog> proLog;

	/** VERSION */
	@TableField("VERSION")
	private Date version;
	
    /**
     * vmi促销fanht
     * 外部折扣编码
     */
    @TableField("VMI_DISCOUNT_CODE")
    private String vmiDiscountCode;
    
    /**
     * POS销售金额
     */
    @TableField("POS_SALES")
    private BigDecimal posSales;
    
    /**
     * 退换货对应原单line
     */
    @TableField("RA_SO_LINE_ID")
    private Long raSoLineId;
    
    /**
     * 商品组合搭配说明
     */
    @TableField("SKU_COMBO_REMARK")
    private String skuComboRemark;

    /** 创建类型：1.天猫抓取，2.客服导入 */
    @TableField("SOURCE_TYPE")
    private Integer sourceType;
    
    /**
     * 商家的预计发货时间
     */
    @TableField("EST_CON_TIME")
    private String estConTime;
    
    /**
     * 图片路径连接
     */
    @TableField("PIC_PATH")
    private String picPath;
    
    /**
     * 销售模式
     */
    @TableField("SALES_MODEL")
    private Integer salesModel;
    
    //天猫国际官网直供子订单关税税费
	@TableField("SUB_ORDER_TAX_FEE")
    private BigDecimal subOrderTaxFee;
    
    /**
     * 直连订单出库owner
     */
    @TableField("OUT_OWNER")
    private String outOwner;
    
    //店铺ID
	@TableField("SHOP_ID")
    private Long shopId;
    
    /**
     * 用于记录捆绑oid
     */
    @TableField("ASSEMBLY_RELA")
    private String assemblyRela;
    
    /* 平台total_fee:应付金额(商品价格*商品数量+手工调整金额 -子订单级订单优惠金额)*/
    @TableField("TOTAL_FEE")
    private BigDecimal totalFee;
    
    //------------- add by chenping 20170629 begin
    /**
     * 花呗分期期数
     */
    @TableField("FQG_NUM")
    private Long fqgNum;
    
    /**
     * 是否商家承担手续费
     * @return
     */
    @TableField("IS_FQG_S_FEE")
    private Boolean isFqgSFee;
  //------------- add by chenping 20170629 end
}
