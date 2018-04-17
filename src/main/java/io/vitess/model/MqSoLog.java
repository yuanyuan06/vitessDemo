package io.vitess.model;

import io.vitess.enums.MqSoLogStatus;
import io.vitess.enums.PlatformType;
import io.vitess.enums.SoSpecialType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YSH4807
 * @date 2018/4/11 10:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MqSoLog implements Serializable {

    /**
     * PK
     */
    private Long id;

    private Date version;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 平台订单号
     */
    private String code;

    private Integer platformType;

    private String platformOrderStatus;

    /**
     * 处理状态
     */
    private Integer status;

    /**
     * 处理错误信息
     */
    private String errorMsg;

    /**
     * log创建时间（OMS接收时间）
     */
    private Date createTime;

    /**
     * 订单外部创建时间
     */
    private Date outerCreateTime;

    /**
     * 付款时间
     */
    private Date paymentTime;

    /**
     * 主支付帐号
     */
    private String payAccount;

    /**
     * 主支付流水号
     */
    private String payNo;

    /**
     * 是否需要发票
     */
    private Boolean isNeededInvoice;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 实际运费
     */
    private BigDecimal acutalTransFee;

    /**
     * 商品总金额
     */
    private BigDecimal totalActual;

    /**
     * 订单使用的外部积分点
     */
    private BigDecimal totalOuterPoint;

    /**
     * 订单使用的内部积分点
     */
    private BigDecimal totalInnerPoint;

    /**
     * 订单使用虚拟货币支付总金额
     */
    private BigDecimal totalVc;

    /**
     * 买家备注
     */
    private String remark;

    /**
     * 卖家备注
     */
    private String sellerMemo;

    /**
     * 定制信息
     */
    private String customizationMemo;

    /**
     * 是否需要包装
     */
    private Boolean isNeededPacking;

    /**
     * 配送备注 格式：{psDate:"",period:""} 从3.0.6版本开始 移至字段expProp1中
     */
    private String deliveryRemark;

    /**
     * isMemberOrder 从3.0.6版本开始 移至字段expProp1中
     */
    private Boolean isMemberOrder;

    /**
     * 数据来源
     */
    private String source;

    /**
     * taobao订单属性 是否保障速递 如果为true，则为保障速递订单，使用线下联系发货接口发货
     * 如果未false，则该订单非保障速递，根据卖家设置的订单流转规则可使用物流宝或者常规物流发货
     */
    private Boolean isLgtype;

    /**
     * 扩展字段1 son格式，若商城有某些特有的字段信息需要传送，以json格式通过该字段传送
     */
    private String extProp1;

    /**
     * 平台订单类型
     */
    private String platformOrderType;

    /**
     * 数据指向
     */
    private Integer direction;

    /**
     * 消息批次ID
     */
    private Long msgBatchId;

    /**
     * 分仓编码
     *
     * @return
     */
    private String storeCode;

    /**
     * 服务项目费用 如"延长保修1年","碰了摔了也管赔"等服务费，目前只有淘宝上存在服务类商品 该金额信息需要以指定费用类目过至EBS,后续可能将订单中服务类商品信息单独了成其它数据表
     */
    private BigDecimal serviceItemFee = BigDecimal.ZERO;

    /**
     * 相关编码1
     */
    private String slipCode1;
    /**
     * 相关编码2
     *
     */
    private String slipCode2;
    /**
     * 错误次数
     */
    private Integer errorCount = 0;

    /**
     * 商城接口5.0新增加字段 订单整单的折扣
     */
    private BigDecimal totalDiscount;

    /**
     * 商城接口5.0新增加字段 支付产生的折扣
     */
    private BigDecimal payDiscount;

    /**
     * 是否平台发货
     */
    private Boolean isPlatformDelivery;

    /**
     * COD收款金额
     */
    private BigDecimal codAmt;

    private Integer specialType = SoSpecialType.DEFAULT.getValue();

    /**
     * 虚拟支付金额扩展字段1
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 天猫订单该字段为：集分宝支付金额
     */
    private BigDecimal extVc1 = BigDecimal.ZERO;
    /**
     * 虚拟支付金额扩展字段2
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 天猫订单该字段为：天猫点券支付金额
     */
    private BigDecimal extVc2 = BigDecimal.ZERO;
    /**
     * 虚拟支付金额扩展字段3
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     *
     */
    private BigDecimal extVc3 = BigDecimal.ZERO;

    /**
     * 使用信用卡支付金额数
     */
    private String creditCardFee;
    /**
     * 支付宝ID
     */
    private Long alipayId;

    /** 未付款订单处理状态: 0.未处理；1.已处理 */
    private Integer noPayStatus = 0;

    //天猫国际官网直供主订单关税税费
    private BigDecimal orderTaxFee;

    /**
     * 是否捆绑订单
     */
    private Boolean isBundle = Boolean.FALSE;

    /**
     * 跨境订单
     */
    private Boolean crossBondedDeclare = Boolean.FALSE;

    /**
     * 全渠道 原始字符串
     * @return
     */
    private String  omnichannelParam;




}
