package io.vitess.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.math.BigDecimal;
import java.util.Date;

@TableName("t_mq_so_line_log")
public class MqSoLineLog extends SuperEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3491854187095045360L;


    @TableField("VERSION")
    private Date version;

    /**
     * 条码(供应商到颜色尺码)
     */
    @Deprecated
    @TableField("BAR_CODE")
    private String barCode;

    @TableField("EXTENTION_CODE")
    private String extentionCode;

    /**
     * 数量
     */
    @TableField("QTY")
    private Integer qty;

    /**
     * 单价
     */
    @TableField("UNIT_PRICE")
    private BigDecimal unitPrice;

    /**
     * 市场价
     */
    @TableField("LIST_PRICE")
    private BigDecimal listPrice;

    /**
     * markdown价格
     */
    @TableField("MD_PRICE")
    private BigDecimal mdPrice;

    /**
     * 行总计 (扣减所有活动优惠且未扣减积分抵扣金额) sum(sl.totalActual)=so.totalActual totalActual + discountFee =
     * unitPrice × qty
     */
    @TableField("TOTAL_ACTUAL")
    private BigDecimal totalActual;

    /**
     * 行优惠总金额
     */
    @TableField("DISCOUNT_FEE")
    private BigDecimal discountFee;

    /**
     * 订单行使用外部积分支付金额
     */
    @TableField("OUTER_POINT_VALUE")
    private BigDecimal outerPointValue;

    /**
     * 订单行使用内部积分支付金额
     */
    @TableField("INNER_POINT_VALUE")
    private BigDecimal innerPointValue;

    /**
     * 积分外其它虚拟货币支付金额（如预付卡）
     */
    @TableField("OTHER_VC")
    private BigDecimal otherVc;

    /**
     * 平台订单行ID
     */
    @TableField("PLATFORM_LINE_ID")
    private String platformLineId;

    /**
     * 扩展字段1
     */
    @TableField("EXT_PROP1")
    private String extProp1;

    @TableField("SO_LOG_ID")
    private Long soLog;

    /**
     * 平台来源
     */
    @TableField("PLATFORM_SOURCE")
    private String platformSource;

    /**
     * 平台商品名称
     */
    @TableField("PLATFORM_SKU_NAME")
    private String platformSkuName;

    /**
     * 保修时长（月份）
     */
    @TableField("WARRANTY_MONTHS")
    private Long warrantyMonths;

    /**
     * 平台分仓编码
     */
    @TableField("PLATFORM_WH_CODE")
    private String platformWhCode;

    /**
     * 是否为赠品
     */
    @TableField("IS_PREZZIE")
    private Boolean isPrezzie;
    
    /** 商家的预计发货时间 */
    @TableField("EST_CON_TIME")
    private String estConTime;
    
    /**
     * 图片路径连接
     */
    @TableField("PIC_PATH")
    private String picPath;
    
    //天猫国际官网直供子订单关税税费
    @TableField("SUB_ORDER_TAX_FEE")
    private BigDecimal subOrderTaxFee = BigDecimal.ZERO;
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;
    
    /**
     * 用于记录捆绑oid
     */
    @TableField("ASSEMBLY_RELA")
    private String assemblyRela;

    /**
     * 包装信息
     */
    private MqSoPackingInfoLog mqSoPackingInfoLog;
    
    /* 平台part_mjz_discount:整单折扣分摊金额*/
    @TableField("PART_MJZ_DISCOUNT")
    private BigDecimal partMjzDiscount;
    
    /* 平台total_fee:应付金额(商品价格*商品数量+手工调整金额 -子订单级订单优惠金额)*/
    @TableField("TOTAL_FEE")
    private BigDecimal totalFee;
    /**
     * 全渠道门店编号（若派到的是门店，则此处为门店id；若派到的是电商仓，则此处为电商仓的名字）
     * @return
     */
    @TableField("TARGET_CODE")
    private String targetCode;
    /**
     * 派到的是门店(STORE)还是电商仓(WAREHOUSE)。
     * @return
     */
    @TableField("TARGET_TYPE")
    private String targetType;
    /**
     * 星盘派单号
     * @return 
     */
    @TableField("ALLOCATION_CODE")
    private String allocationCode;
    
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
