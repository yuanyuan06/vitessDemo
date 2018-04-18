package io.vitess.model.mq;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.common.SuperEntity;
import io.vitess.enums.SoSpecialType;

import java.math.BigDecimal;
import java.util.Date;

@TableName("t_mq_so_log")
public class MqSoLog extends SuperEntity {
    private static final long serialVersionUID = 6640950308239023560L;

    @Version
    private Date version;

    /**
     * 店铺ID
     */
    @TableField("SHOP_ID")
    private Long shopId;

    /**
     * 平台订单号
     */
    @TableField("CODE")
    private String code;

    @TableField("PLATFORM_TYPE")
    private Integer platformType;
//    private PlatformType platformType;


    @TableField("PLATFORM_ORDER_STATUS")
    private String platformOrderStatus;

    /**
     * 处理状态
     */
    @TableField("STATUS")
    private Integer status;
//    private MqSoLogStatus status;

    /**
     * 处理错误信息
     */
    @TableField("ERROR_MSG")
    private String errorMsg;

    /**
     * log创建时间（OMS接收时间）
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 订单外部创建时间
     */
    @TableField("OUTER_CREATE_TIME")
    private Date outerCreateTime;

    /**
     * 付款时间
     */
    @TableField("PAYMENT_TYPE")
    private Date paymentTime;

    /**
     * 付款方式
     */
    @Deprecated
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    /**
     * 主支付帐号
     */
    @TableField("ALIPAY_ACCOUNT")
    private String payAccount;

    /**
     * 主支付流水号
     */
    @TableField("ALIPAY_NO")
    private String payNo;

    /**
     * 是否需要发票
     */
    @TableField("IS_NEEDED_INVOICE")
    private Boolean isNeededInvoice;

    /**
     * 发票抬头
     */
    @TableField("INVOICE_TITLE")
    private String invoiceTitle;

    /**
     * 发票内容
     */
    @TableField("INVOICE_CONTENT")
    private String invoiceContent;

    /**
     * 物流供应商编码
     */
    @Deprecated
    @TableField("LP_CODE")
    private String lpCode;

    /**
     * 实际运费
     */
    @TableField("ACTUAL_TRANS_FEE")
    private BigDecimal acutalTransFee;

    /**
     * 商品总金额
     */
    @TableField("TOTAL_ACTUAL")
    private BigDecimal totalActual;

    /**
     * 订单使用总积分
     */
    @Deprecated
    @TableField("TOTAL_POINT_USED")
    private BigDecimal totalPointUsed;


    /**
     * 订单使用的外部积分点
     */
    @TableField("TOTAL_OUTER_POINT")
    private BigDecimal totalOuterPoint;


    /**
     * 订单使用的内部积分点
     */
    @TableField("TOTAL_INNER_POINT")
    private BigDecimal totalInnerPoint;


    /**
     * 订单使用虚拟货币支付总金额
     */
    @TableField("TOTAL_VC")
    private BigDecimal totalVc;

    /**
     * 买家备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 卖家备注
     */
    @TableField("SELLER_MEMO")
    private String sellerMemo;
    
    /**
     * 定制信息
     */
    @TableField("CUSTOMIZATION_MEMO")
    private String customizationMemo;

    /**
     * 会员帐号
     */
    @Deprecated
    @TableField
    private String account;

    /**
     * 会员邮箱
     */
    @Deprecated
    @TableField("MEMBER_EMAIL")
    private String memberEmail;

    /**
     * 优惠券代码 从3.0.6版本开始 移至平台会员信息中
     */
    @Deprecated
    @TableField("COUPON_CODE")
    private String couponCode;

    /**
     * 优惠券类型 1:现金券 2:折扣券 从3.0.6版本开始 移至平台会员信息中
     */
    @Deprecated
    @TableField("COUPON_TYPE")
    private String couponType;

    /**
     * 优惠券抵扣金额 从3.0.6版本开始 移至平台会员信息中
     */
    @Deprecated
    @TableField("COUPON_DISCOUNT_FEE")
    private BigDecimal couponDiscountFee;


    /**
     * 是否需要包装
     */
    @TableField("IS_NEEDED_PACKING")
    private Boolean isNeededPacking;

    /**
     * 配送备注 格式：{psDate:"",period:""} 从3.0.6版本开始 移至字段expProp1中
     */
    @TableField("DELIVERY_REMARK")
    private String deliveryRemark;

    /**
     * isMemberOrder 从3.0.6版本开始 移至字段expProp1中
     */
    @TableField("IS_MEMBER_ORDER")
    private Boolean isMemberOrder;

    /**
     * Esprit官方商城使用,从3.0.6版本开始 移至字段expProp1中
     */
    @Deprecated
    @TableField("GC_AMT")
    private BigDecimal gcAmt;

    /**
     * Esprit官方商城使用,从3.0.6版本开始 移至字段expProp1中
     */
    @Deprecated
    @TableField("SGC_AMT")
    private BigDecimal sgcAmt;

    /**
     * 积分抵扣金额 esprit报表需要，商城端将该金额作为了促销已从订单总金额中扣除 4.0版本后积分不属于促销而是一种支付方式
     */
    @Deprecated
    @TableField("BPC_AMT")
    private BigDecimal bpcAmt;

    /**
     * 会员卡号 esprit报表需要，4.0版本后移至平台会员信息vipCode字段上
     */
    @Deprecated
    @TableField("CARD_NO")
    private String cardNo;

    /**
     * 数据来源
     */
    @TableField("SOURCE")
    private String source;

    /**
     * taobao订单属性 是否保障速递 如果为true，则为保障速递订单，使用线下联系发货接口发货
     * 如果未false，则该订单非保障速递，根据卖家设置的订单流转规则可使用物流宝或者常规物流发货
     */
    @TableField("IS_LG_TYPE")
    private Boolean isLgtype;

    /**
     * 扩展字段1 son格式，若商城有某些特有的字段信息需要传送，以json格式通过该字段传送
     */
    @TableField("EXT_PROP1")
    private String extProp1;

    /**
     * 平台订单类型
     */
    @TableField("PLATFORM_ORDER_TYPE")
    private String platformOrderType;
    
    /**
     * 数据指向
     */
    @TableField("DIRECTION")
    private Integer direction;

    /**
     * 消息批次ID
     */
    @TableField("MSG_BATCH_ID")
    private Long msgBatchId;

    /**
     * 分仓编码
     * 
     * @return
     */
    @TableField("STORE_CODE")
    private String storeCode;

    /**
     * taobao订单属性 , 宝贝标题
     */
    @Deprecated
    @TableField("TITLE_REMARK")
    private String titleRemark;

    /**
     * 服务项目费用 如"延长保修1年","碰了摔了也管赔"等服务费，目前只有淘宝上存在服务类商品 该金额信息需要以指定费用类目过至EBS,后续可能将订单中服务类商品信息单独了成其它数据表
     */
    @TableField("SERVICE_ITEM_FEE")
    private BigDecimal serviceItemFee = BigDecimal.ZERO;

    /**
     * Nike Global 物流类型
     */
    @Deprecated
    @TableField("NIKE_SERVICE_LEVEL_CODE_TYPE")
    private Integer nikeServiceLevelCodeType;

    /**
     * 相关编码1
     */
    @TableField("SLIP_CODE1")
    private String slipCode1;
    /**
     * 相关编码2
     * 
     */
    @TableField("SLIP_CODE2")
    private String slipCode2;
    /**
     * 错误次数
     */
    @TableField("ERROR_COUNT")
    private Integer errorCount = 0;

    /**
     * 商城接口5.0新增加字段 订单整单的折扣
     */
    @TableField("TOTAL_DISCOUNT")
    private BigDecimal totalDiscount;

    /**
     * 商城接口5.0新增加字段 支付产生的折扣
     */
    @TableField("PAY_DISCOUNT")
    private BigDecimal payDiscount;
    
    /**
     * 是否平台发货
     */
    @TableField("IS_PLATFORM_DELIVERY")
    private Boolean isPlatformDelivery;
    
    /**
     * COD收款金额
     */
    @TableField("COD_AMT")
    private BigDecimal codAmt;

    @TableField("SPECIAL_TYPE")
//    private SoSpecialType specialType = SoSpecialType.DEFAULT;
    private Integer specialType = SoSpecialType.DEFAULT.getValue();

    /**
     * 虚拟支付金额扩展字段1
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 天猫订单该字段为：集分宝支付金额
     */
    @TableField("EXT_VC1")
    private BigDecimal extVc1 = BigDecimal.ZERO;
    /**
     * 虚拟支付金额扩展字段2
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 天猫订单该字段为：天猫点券支付金额
     */
    @TableField("EXT_VC2")
    private BigDecimal extVc2 = BigDecimal.ZERO;
    /**
     * 虚拟支付金额扩展字段3
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 
     */
    @TableField("EXT_VC3")
    private BigDecimal extVc3 = BigDecimal.ZERO;
    
    /**
     * 使用信用卡支付金额数
     */
    @TableField("CREDIT_CARD_FEE")
    private String creditCardFee;
    /**
     * 支付宝ID
     */
    @TableField("ALIPAY_ID")
    private Long alipayId;
    
    /** 未付款订单处理状态: 0.未处理；1.已处理 */
    @TableField("no_pay_status")
    private Integer noPayStatus = 0;
    
    //天猫国际官网直供主订单关税税费
    @TableField("ORDER_TAX_FEE")
    private BigDecimal orderTaxFee;
	
	 /**
     * 是否捆绑订单
     */
	 @TableField("IS_BUNDLE")
    private Boolean isBundle = Boolean.FALSE;
    
    /**
     * 跨境订单
     */
    @TableField("CROSS_BONDED_DECLARE")
    private Boolean crossBondedDeclare = Boolean.FALSE;

    /**
     * 全渠道 原始字符串
     *
     * @return
     */
    @TableField("OMNICHANNEL_PARAM")
    private String omnichannelParam;
    
    /**
     * 发票类型，1电子或者0纸质
     */
    @TableField("INVOICE_KIND")
    private Boolean invoiceKind;
    
    /**
     * 企业税号/纳税识别码
     */
    @TableField("BUYER_TAX_NO")
    private String buyerTaxNO;
    
    /**
     * 分阶段付款的订单状态（例如万人团订单等），目前有三返回状态FRONT_NOPAID_FINAL_NOPAID(定金未付尾款未付)，FRONT_PAID_FINAL_NOPAID(定金已付尾款未付)，FRONT_PAID_FINAL_PAID(定金和尾款都付)
     */
    @TableField("STEP_TRADE_STATUS")
    private String stepTradeStatus;
    
    /**
     * 分阶段付款的已付金额（万人团订单已付金额）
     */
    @TableField("STEP_PAID_FEE")
    private BigDecimal stepPaidFee;

}