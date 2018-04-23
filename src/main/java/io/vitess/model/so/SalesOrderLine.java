package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import io.vitess.enums.OrderLineType;
import io.vitess.model.base.Sku;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@TableName("t_td_sales_order_line")
public class SalesOrderLine extends SuperEntity{

	private static final long serialVersionUID = 8079539825879918172L;

	/** 拆单原始SalesOrderLine的ID号 **/
	private Long splitSourceLineId;

	/** 销售订单ID，关联SALES_ORDER_ID */
	private SalesOrder salesOrder;

	/** 原外部平台订单行标识 */
	private String platformOrderLineCode;

	/** 行类型，主卖品行;赠品行 */
	private OrderLineType orderLineType;

	/** SKU */
	private Sku sku;

	/** EXTENTION_CODE（ERP的EXTENTION_CODE1） */
	private String extentionCode;

	/** SKU_CODE（ERP的JM_SKU_CODE） */
	private String skuCode;

	/** SKU_NAME */
	private String skuName;
	
	/** PLATFORM_SKU_NAME */
	private String platformSkuName;

	/** BAR_CODE */
	private String barCode;

	/** 扩展属性 */
	private String keyProperties;
	
	/** 是否组合商品     */
    private Boolean isComboSku = Boolean.FALSE;
    
    /** 组合商品编码：若不是组合商品，该值为空， 否则值表示为extCode（该值为组合商品的ExtCode）  */
    private String comboSkuExtCodeOrgin;

    /**商品行第一次创建时的数量**/
	private Integer initQty;
	
	/** 数量 */
	private Integer quantity;

	/** 吊牌价 */
	private BigDecimal listPrice;

	/** 销售单价(折前单价) */
	private BigDecimal unitPrice;

	/** 销售金额总计=销售单价x数量 */
	private BigDecimal totalAmount;

	/** 行折扣金额 */
	private BigDecimal lineDiscount;

	/** 平摊后行折扣金额=行折扣金额+订单平摊到行的折扣金额 */
	private BigDecimal totalDiscount;

	/** 折扣后金额总计=销售金额总计-平摊后行折扣金额 */
	private BigDecimal totalAmountAfterDiscount;

	/** 折扣后单价=折扣后金额总计/数量，保留两位小数 */
	private BigDecimal unitPriceAfterDiscount;

	/** 虚拟货币支付金额(如积分抵扣),由整单平摊过来 */
	private BigDecimal virtualAmount;

	/** 开票金额总计=折扣后金额总计-虚拟货币支付金额 */
	private BigDecimal invoiceTotalAmount;

	/** 开票单价=开票金额总计/数量，保留两位小数 */
	private BigDecimal invoiceUnitPrice;
	
	/** 总共使用积分 */
    private BigDecimal totalPointUsed;

	/** 保修时长(按月计) */
	private Integer warrantyMonths;

	/** 平台分仓编码 */
	private String platformWhCode;

	/** 平台订单来源信息备注 */
	private String sourceRemark;

	/** 活动来源 */
	private String activitySource;

	/** 赠品日志 */
	private List<SoPromotionApplyLog> proLog;

	/** VERSION */
	private Date version;
	
    /**
     * vmi促销fanht
     * 外部折扣编码
     */
    private String vmiDiscountCode;
    
    /**
     * POS销售金额
     */
    private BigDecimal posSales;
    
    /**
     * 退换货对应原单line
     */
    private Long raSoLineId;
    
    /**
     * 商品组合搭配说明
     */
    private String skuComboRemark;

    /** 创建类型：1.天猫抓取，2.客服导入 */
    private Integer sourceType;
    
    /**
     * 商家的预计发货时间
     */
    private String estConTime;
    
    /**
     * 图片路径连接
     */
    private String picPath;
    
    /**
     * 销售模式
     */
    private Integer salesModel;
    
    //天猫国际官网直供子订单关税税费
    private BigDecimal subOrderTaxFee;
    
    /**
     * 直连订单出库owner
     */
    private String outOwner;
    
    //店铺ID
    private Long shopId;
    
    /**
     * 用于记录捆绑oid
     */
    private String assemblyRela;
    
    /* 平台total_fee:应付金额(商品价格*商品数量+手工调整金额 -子订单级订单优惠金额)*/
    private BigDecimal totalFee;
    
    //------------- add by chenping 20170629 begin
    /**
     * 花呗分期期数
     */
    private Long fqgNum;
    
    /**
     * 是否商家承担手续费
     * @return
     */
    private Boolean isFqgSFee;
  //------------- add by chenping 20170629 end
}
