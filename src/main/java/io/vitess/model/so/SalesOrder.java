package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import io.vitess.common.User;
import io.vitess.constants.Constants;
import io.vitess.constants.SysWmsStatus;
import io.vitess.enums.*;
import io.vitess.model.mq.CompanyShop;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@TableName("t_td_sales_order")
public class SalesOrder extends SuperEntity{

	private static final long serialVersionUID = -8346565385079726815L;

	/** 交易ID */
	private Trade trade;

	/** 原外部平台订单标识 */
	private String platformOrderCode;

	/** 原外部平台订单号'-'索引序号 */
	private String platformOrderCodeN;
	
	/** 原始的子订单号 */
	private String originalPocn;

	/** OMS订单标识,同platformOrderCodeN */
	private String omsOrderCode;
	
	/** 复制原订单编码 */
	private String copySourceOmsOrderCode;
	
	/** 拆分原订单编码 */
	private String splitSourceOmsOrderCode;
	
	   /** 退换货产生新单记录原单号 */
    private String raOmsOrderCode;
	
	/** 外部系统【pacs，wms】单号 */
	private String erpOrderCode;

	/** 订单状态 */
	private SalesOrderStatus orderStatus;
	
	/** 订单挂起原因    @see SalesOrderSuspendReasonType */
	private Integer suspendReasonType;

	/** 订单所属店铺 */
	private CompanyShop companyShop;

	/** 销售模式，半角逗号分隔:默认0 */
	private String salesModesStr = "0";

	/** 创建时间 */
	private Date createTime;

	/** 仓库编码 */
	private String warehouseCode;
	
	/** 同步至ERP时间 */
	private Date syncToErpTime;

	/** ERP反馈时间 */
	private Date erpResponseTime;

	/** 取消时间 */
	private Date cancelTime;

	/** 过仓时间 */
	private Date transToWarehouseTime;

	/** 出库时间 */
	private Date stockOutTime;

	/** 交易结束时间，确认、作废、退换货关闭 */
	private Date finishTime;

	/** 订单头折扣 */
	private BigDecimal headDiscount;

	/** 订单行折扣 */
	private BigDecimal linesDiscount;

	/** 订单整单折扣=订单头折扣+订单行折扣 */
	private BigDecimal discountTotal;

	/** 订单折前金额=sum（行商品折前价X数量） */
	private BigDecimal amountBeforeDiscount;

	/** 订单折后金额=订单折前金额-订单整单折扣 */
	private BigDecimal amountAfterDiscount;

	/** 支付产生的折扣：由于预付卡或其他支付方式带来的金额折扣 */
	private BigDecimal paymentDiscount;
	
	/** 订单上使用的总的外部积分点 */
    private BigDecimal totalOuterPoint;

    /** 订单上使用的总的内部积分点 */
    private BigDecimal totalInnerPoint;

	/** 使用虚拟点数 */
	private BigDecimal usePoint;

	/** 使用虚拟抵扣金额 */
	private BigDecimal virtualAmount;
	
	/** 送货上门费合计 **/
	private BigDecimal homeDeliveryFee = BigDecimal.ZERO;
	
	/** 上门安装费合计 **/
	private BigDecimal installFee = BigDecimal.ZERO;

	/** 主支付方式 */
	private PaymentType mainPaymentType;

	/** 运费 */
	private BigDecimal transFee;

	/** 卖家备注 */
	private String sellerMemo;

	/** 买家备注 */
	private String buyerMemo;
	
	/**
	 * 定制信息备注
	 */
	private String customizationMemo;
	
	/** 系统备注：可用来手动修改数据时，备份信息 **/
	private String sysMemo;
	
    /**
     * 发票形式：false 纸质发票；true 电子发票
     */
    private Boolean invoiceKind;

	/** 是否需要发票 */
	private Integer needInvoice;

	/** 是否需要拆票 */
	private Integer isBillingManual;

	/** 发票类型(1:普通商业零售发票、2:增值税专用发票) */
	private InvoiceType invoiceType;
	
	/**  发票备注 */
    private String invoiceMemo;

	/** 发票抬头 */
	private String invoiceTitle;
	
	/** 是否开具发票明细 */
	private Integer isBillingInvoiceDetail;

	/** 发票内容 */
	private String invoiceContent;

	/** 增票-公司名 */
	private String vaTaxCompanyName;

	/** 赠票-税号 */
	private String vaTaxCode;

	/** 赠票-公司注册地址 */
	private String vaTaxRegisterAddress;

	/** 赠票-公司电话 */
	private String vaTaxTelephone;

	/** 赠票-开户行 */
	private String vaTaxBankName;

	/** 赠票-开户行帐号 */
	private String vaTaxBankCard;
	
	/** 平台订单来源信息备注 */
	private String sourceRemark;

	/** 终端来源(PC、WAP) */
	private String terminalSource;

	/** 活动来源 */
	private String activitySource;
	
	/** 平台来源 */
    private String platformSource;
    
    /** 子订单来源：AUTO:自动拆单、MANUAL:手动拆单、SHIPPING: WMS拆Shipping */
    private String subOrderSource;
    
	/** 字段挪用做--订单取消原因，枚举详见t_sys_choose_option表的category_code为soCancelReason 
	 * 11 缺货取消
	 * 12 买家意愿
	 * 13 拆单
	 * 14 复制新单
	 * */
	private Integer isCycle = 0;
	
	/** 预计到货时间 */
    private Date estArrivalTime;

	/** 是否预售订单 */
    @Deprecated
	private Integer isPresale = Constants.NO;

	/** 平台业务类型 */
	private SalesOrderType orderType;
	
	/** 平台交易类型 */
	private String platformTradeType;
	
	/** 服务自提或者送货上门(0 默认；1：自提；2：送货上门) **/
	private SoDeliveryType deliveryType = SoDeliveryType.DEFAULT;
	
	/** 是否有安装服务 **/
	private Boolean isInstall = Boolean.FALSE;
	
	/** 安装公司编码 **/
	private String installCompanyCode;
	
	/** 安装公司名称 **/
	private String installCompanyName;
	
	/** 财务状态 */
    private SoFinanceStatus financeStatus;

    /** 已付款金额 */
    private BigDecimal financeTotalAmount;
    
    /** 外部平台创建时间 */
    private Date platformCreateTime;

    /**  外部平台付款时间 */
    private Date platformPaymentTime;
    
    /** 主支付帐号 */
    private String payAccount;
    
    /** 主支付流水号 */
    private String payNo;
    
    /** 是否需要包装 */
    private Integer neededPacking;
    
    /**
     * 服务项目费用
     * 如"延长保修1年","碰了摔了也管赔"等服务费，目前只有淘宝上存在服务类商品
     * 该金额信息需要以指定费用类目过至EBS,后续可能将订单中服务类商品信息单独了成其它数据表
     */
    private BigDecimal serviceItemFee = BigDecimal.ZERO;
    
    /**
     * 订单类型
     */
    private SoSpecialType specialType = SoSpecialType.DEFAULT;
    
    /** 仓储模式 1, "使用宝尊wms" ;  2, "不使用宝尊wms"*/
    private CompanyShopWhModel wfBranch;
    
    /** 创建人 */
    private User creator;
    
    /** 最后修改人 */
    private User lastModifyUser;
    
    /** 订单发货信息 */
    private SoDeliveryInfo soDeliveryInfo;

	/** VERSION */
	private Date version;
	
    /**
     * vmi促销fanht
     * 外部平台对接促销码
     */
    private String vmiPromotionCode;
    
    /**
     * 品牌pos销售金额
     */
    private BigDecimal posSales;
    
    private List<SalesOrderLine> salesOrderLineList;
    
    /** 支付明细 */
    private List<SalesOrderPaymentInfo> salesOrderPaymentInfoList;

    /** 创建类型：1.天猫抓取，2.客服导入 */
    private Integer sourceType;
    
    /**
     * 虚拟支付金额扩展字段1
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalOtherVc、totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 天猫订单该字段为：集分宝支付金额
     */
    private BigDecimal extVc1 = BigDecimal.ZERO;
    /**
     * 虚拟支付金额扩展字段2
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalOtherVc、totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 天猫订单该字段为：天猫点券支付金额
     */
    private BigDecimal extVc2 = BigDecimal.ZERO;
    /**
     * 虚拟支付金额扩展字段3
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalOtherVc、totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 
     */
    private BigDecimal extVc3 = BigDecimal.ZERO;
    
    /**
     * 过仓失败原因
     */
    private String toWhRemark;
    
    /**
     * 关怀等级
     */
    private Integer careGrade;
    
    /**
     * 关怀备注
     */
    private String careMomo;
    
    /**
     * 直连订单是否已获取owner
     */
    private Boolean isCloudStackSo = Boolean.FALSE;
    
    /**
     * 主分仓编码
     */
    private String mainBranchWhCode;
    
    /**
     * 商品重量
     */
    private BigDecimal goodsWeight;
    
    /** 是否自动过仓 **/
    private Boolean isAutoWh = Boolean.FALSE;
    
    /** 支付宝ID **/
    private Long alipayId;
    
    /** 使用信用卡支付金额数 **/
    private BigDecimal creditCardFee;

    /**
     * 是为直连WMS订单
     */
    private Integer isDirectWmsOrder = SysWmsStatus.UNWMS;
    /**
     * 拆单时间
     */
    private Date splitOrderTime;
    
    /**
	 * 是否指定发货仓库
	 */
	private Boolean isAssignWh = Boolean.FALSE;
	
    //天猫国际官网直供主订单关税税费
    private BigDecimal orderTaxFee;
    
	/**
	 * 是否捆绑销售订单 
	 */
	private Boolean isBundle = Boolean.FALSE;
	
    /**
     * 全渠道门店编号（若派到的是门店，则此处为门店id；若派到的是电商仓，则此处为电商仓的名字）
     * @return
     */
    private String targetCode;
    /**
     * 是否是全渠道订单
     */
    private Boolean isOmnichannelParam = Boolean.FALSE;
    
	/**
	 * 
	 * @Description:  订单路由状态（0未处理，5处理失败，10处理成功）
	 * @author zhiyong.shi
	 * 2017年3月27日
	 */
    private Integer orderRouteStatus = 0;
    /**
     * 星盘派单号
     * @return 
     */
    private String allocationCode;
    
    
    
    /**
     * 
     * @Description: 是否为订单路由订单
     * @author zhiyong.shi
     * 2017年3月28日
     */
    private Boolean isOrderRoute = Boolean.FALSE ;
    
    /**
     * 开票给企业或个人,1个人,2企业
     *  
     */
    private Integer businessType;
    
    
	public SalesOrder(){}
	
	
	/**
     * 企业税号/纳税识别码
     */
    private String buyerTaxNO;
    
    /**
     * 预售订单已付定金
     * @author xin.feng
     */
    private BigDecimal stepPaidFee;
    
    /**
     * 预包装订单送货地址是否有更改
     * @author xin.feng
     */
    private Boolean isAddrChanged = Boolean.FALSE;
    
    /**
     * 此订单发货将要使用的在IM系统对应的库位
     * 如预售订单该使用哪个库位发货
     *  @author xin.feng
     */
    private String whLocationCode;
    
    /**
     * @author xin.feng
     * 路由反馈的仓库是否支持预包装
     */
    private Boolean isSupportPrePackage;
    
    /**
     * @author xin.feng
     * 预售预包装订单支付尾款的时间
     */
    private Date stepPayTime;
    
    /**
     * 全渠道门店编号（若派到的是门店，则此处为门店id；若派到的是电商仓，则此处为电商仓的名字） add by chenping 20170814 
     * @return
     */
    private String originalTargetCode;

}
