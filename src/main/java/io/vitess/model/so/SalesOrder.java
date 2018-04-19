package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
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
@TableName("t_td_sales_order")
public class SalesOrder extends SuperEntity{

	private static final long serialVersionUID = -8346565385079726815L;

	/** 交易ID */
	@TableField("TRADE_ID")
	private Trade trade;

	/** 原外部平台订单标识 */
	@TableField("PLATFORM_ORDER_CODE")
	private String platformOrderCode;

	/** 原外部平台订单号'-'索引序号 */
	@TableField("PLATFORM_ORDER_CODE_N")
	private String platformOrderCodeN;
	
	/** 原始的子订单号 */
	@TableField("ORIGINAL_POCN")
	private String originalPocn;

	/** OMS订单标识,同platformOrderCodeN */
	@TableField("OMS_ORDER_CODE")
	private String omsOrderCode;
	
	/** 复制原订单编码 */
	@TableField("COPY_SOURCE_OMS_ORDER_CODE")
	private String copySourceOmsOrderCode;
	
	/** 拆分原订单编码 */
	@TableField("SPLIT_SOURCE_OMS_ORDER_CODE")
	private String splitSourceOmsOrderCode;
	
	   /** 退换货产生新单记录原单号 */
   	@TableField("RA_OMS_ORDER_CODE")
    private String raOmsOrderCode;
	
	/** 外部系统【pacs，wms】单号 */
	@TableField("ERP_ORDER_CODE")
	private String erpOrderCode;

	/** 订单状态 */
	@TableField("ORDER_STATUS")
	private SalesOrderStatus orderStatus;
	
	/** 订单挂起原因    @see SalesOrderSuspendReasonType */
	@TableField("SUSPEND_REASON_TYPE")
	private Integer suspendReasonType;

	/** 订单所属店铺 */
	@TableField("ERP_SHOP_CODE")
	private CompanyShop companyShop;

	/** 销售模式，半角逗号分隔:默认0 */
	@TableField("SALES_MODES_STR")
	private String salesModesStr = "0";

	/** 创建时间 */
	@TableField("CREATE_TIME")
	private Date createTime;

	/** 仓库编码 */
	@TableField("WAREHOUSE_CODE")
	private String warehouseCode;
	
	/** 同步至ERP时间 */
	@TableField("SYNC_TO_ERP_TIME")
	private Date syncToErpTime;

	/** ERP反馈时间 */
	@TableField("ERP_RESPONSE_TIME")
	private Date erpResponseTime;

	/** 取消时间 */
	@TableField("CANCEL_TIME")
	private Date cancelTime;

	/** 过仓时间 */
	@TableField("TRANS_TO_WAREHOUSE_TIME")
	private Date transToWarehouseTime;

	/** 出库时间 */
	@TableField("STOCK_OUT_TIME")
	private Date stockOutTime;

	/** 交易结束时间，确认、作废、退换货关闭 */
	@TableField("FINISH_TIME")
	private Date finishTime;

	/** 订单头折扣 */
	@TableField("HEAD_DISCOUNT")
	private BigDecimal headDiscount;

	/** 订单行折扣 */
	@TableField("LINES_DISCOUNT")
	private BigDecimal linesDiscount;

	/** 订单整单折扣=订单头折扣+订单行折扣 */
	@TableField("DISCOUNT_TOTAL")
	private BigDecimal discountTotal;

	/** 订单折前金额=sum（行商品折前价X数量） */
	@TableField("AMOUNT_BEFORE_DISCOUNT")
	private BigDecimal amountBeforeDiscount;

	/** 订单折后金额=订单折前金额-订单整单折扣 */
	@TableField("AMOUNT_AFTER_DISCOUNT")
	private BigDecimal amountAfterDiscount;

	/** 支付产生的折扣：由于预付卡或其他支付方式带来的金额折扣 */
	@TableField("PAYMENT_DISCOUNT")
	private BigDecimal paymentDiscount;
	
	/** 订单上使用的总的外部积分点 */
	@TableField("TOTAL_OUTER_POINT")
    private BigDecimal totalOuterPoint;

    /** 订单上使用的总的内部积分点 */
    @TableField("TOTAL_INNER_POINT")
    private BigDecimal totalInnerPoint;

	/** 使用虚拟点数 */
	@TableField("USE_POINT")
	private BigDecimal usePoint;

	/** 使用虚拟抵扣金额 */
	@TableField("VIRTUAL_AMOUNT")
	private BigDecimal virtualAmount;
	
	/** 送货上门费合计 **/
	@TableField("HOME_DELIVERY_FEE")
	private BigDecimal homeDeliveryFee = BigDecimal.ZERO;
	
	/** 上门安装费合计 **/
	@TableField("INSTALL_FEE")
	private BigDecimal installFee = BigDecimal.ZERO;

	/** 主支付方式 */
	@TableField("MAIN_PAYMENT_TYPE")
	private PaymentType mainPaymentType;

	/** 运费 */
	@TableField("TRANS_FEE")
	private BigDecimal transFee;

	/** 卖家备注 */
	@TableField("SELLER_MEMO")
	private String sellerMemo;

	/** 买家备注 */
	@TableField("BUYER_MEMO")
	private String buyerMemo;
	
	/**
	 * 定制信息备注
	 */
	@TableField("CUSTOMIZATION_MEMO")
	private String customizationMemo;
	
	/** 系统备注：可用来手动修改数据时，备份信息 **/
	@TableField("SYS_MEMO")
	private String sysMemo;
	
    /**
     * 发票形式：false 纸质发票；true 电子发票
     */
    @TableField("INVOICE_KIND")
    private Boolean invoiceKind;

	/** 是否需要发票 */
	@TableField("NEED_INVOICE")
	private Integer needInvoice;

	/** 是否需要拆票 */
	@TableField("IS_BILLING_MANUAL")
	private Integer isBillingManual;

	/** 发票类型(1:普通商业零售发票、2:增值税专用发票) */
	@TableField("INVOICE_TYPE")
	private InvoiceType invoiceType;
	
	/**  发票备注 */
	@TableField("INVOICE_MEMO")
    private String invoiceMemo;

	/** 发票抬头 */
	@TableField("INVOICE_TITLE")
	private String invoiceTitle;
	
	/** 是否开具发票明细 */
	@TableField("IS_BILLING_INVOICE_DETAIL")
	private Integer isBillingInvoiceDetail;

	/** 发票内容 */
	@TableField("INVOICE_CONTENT")
	private String invoiceContent;

	/** 增票-公司名 */
	@TableField("VA_TAX_COMPANY_NAME")
	private String vaTaxCompanyName;

	/** 赠票-税号 */
	@TableField("VA_TAX_CODE")
	private String vaTaxCode;

	/** 赠票-公司注册地址 */
	@TableField("VA_TAX_REGISTER_ADDRESS")
	private String vaTaxRegisterAddress;

	/** 赠票-公司电话 */
	@TableField("VA_TAX_TELEPHONE")
	private String vaTaxTelephone;

	/** 赠票-开户行 */
	@TableField("VA_TAX_BANK_NAME")
	private String vaTaxBankName;

	/** 赠票-开户行帐号 */
	@TableField("VA_TAX_BANK_CARD")
	private String vaTaxBankCard;
	
	/** 平台订单来源信息备注 */
	@TableField("SOURCE_REMARK")
	private String sourceRemark;

	/** 终端来源(PC、WAP) */
	@TableField("TERMINAL_SOURCE")
	private String terminalSource;

	/** 活动来源 */
	@TableField("ACTIVITY_SOURCE")
	private String activitySource;
	
	/** 平台来源 */
	@TableField("PLATFORM_SOURCE")
    private String platformSource;
    
    /** 子订单来源：AUTO:自动拆单、MANUAL:手动拆单、SHIPPING: WMS拆Shipping */
    @TableField("SUB_ORDER_SOURCE")
    private String subOrderSource;
    
	/** 字段挪用做--订单取消原因，枚举详见t_sys_choose_option表的category_code为soCancelReason 
	 * 11 缺货取消
	 * 12 买家意愿
	 * 13 拆单
	 * 14 复制新单
	 * */
	@TableField("IS_CYCLE")
	private Integer isCycle = 0;
	
	/** 预计到货时间 */
	@TableField("EST_ARRIVAL_TIME")
    private Date estArrivalTime;

	/** 是否预售订单 */
    @Deprecated
	@TableField("IS_PRESALE")
	private Integer isPresale = Constants.NO;

	/** 平台业务类型 */
	@TableField("ORDER_TYPE")
	private SalesOrderType orderType;
	
	/** 平台交易类型 */
	@TableField("PLATFORM_TRADE_TYPE")
	private String platformTradeType;
	
	/** 服务自提或者送货上门(0 默认；1：自提；2：送货上门) **/
	@TableField("DELIVERY_TYPE")
	private SoDeliveryType deliveryType = SoDeliveryType.DEFAULT;
	
	/** 是否有安装服务 **/
	@TableField("IS_INSTALL")
	private Boolean isInstall = Boolean.FALSE;
	
	/** 安装公司编码 **/
	@TableField("INSTALL_COMPANY_CODE")
	private String installCompanyCode;
	
	/** 安装公司名称 **/
	@TableField("INSTALL_COMPANY_NAME")
	private String installCompanyName;
	
	/** 财务状态 */
	@TableField("FINANCE_STATUS")
    private SoFinanceStatus financeStatus;

    /** 已付款金额 */
    @TableField("FINANCE_TOTAL_AMOUNT")
    private BigDecimal financeTotalAmount;
    
    /** 外部平台创建时间 */
    @TableField("TB_CREATE_TIME")
    private Date platformCreateTime;

    /**  外部平台付款时间 */
    @TableField("TB_PAYMENT_TIME")
    private Date platformPaymentTime;
    
    /** 主支付帐号 */
    @TableField("ALIPAY_ACCOUNT")
    private String payAccount;
    
    /** 主支付流水号 */
    @TableField("ALIPAY_NO")
    private String payNo;
    
    /** 是否需要包装 */
    @TableField("NEED_PACKING")
    private Integer neededPacking;
    
    /**
     * 服务项目费用
     * 如"延长保修1年","碰了摔了也管赔"等服务费，目前只有淘宝上存在服务类商品
     * 该金额信息需要以指定费用类目过至EBS,后续可能将订单中服务类商品信息单独了成其它数据表
     */
    @TableField("SERVICE_ITEM_FEE")
    private BigDecimal serviceItemFee = BigDecimal.ZERO;
    
    /**
     * 订单类型
     */
    @TableField("SPECIAL_TYPE")
    private SoSpecialType specialType = SoSpecialType.DEFAULT;
    
    /** 仓储模式 1, "使用宝尊wms" ;  2, "不使用宝尊wms"*/
    @TableField("WF_BRANCH")
    private CompanyShopWhModel wfBranch;
    
    /** 创建人 */
    @TableField("CREATE_AU_USER_ID")
    private User creator;
    
    /** 最后修改人 */
    @TableField("LAST_MODIFY_AU_USER_ID")
    private User lastModifyUser;
    
    /** 订单发货信息 */
    private SoDeliveryInfo soDeliveryInfo;

	/** VERSION */
	@Version
	private Date version;
	
    /**
     * vmi促销fanht
     * 外部平台对接促销码
     */
    @TableField("VMI_PROMOTION_CODE")
    private String vmiPromotionCode;
    
    /**
     * 品牌pos销售金额
     */
    @TableField("POS_SALES")
    private BigDecimal posSales;
    
    private List<SalesOrderLine> salesOrderLineList;
    
    /** 支付明细 */
    private List<SalesOrderPaymentInfo> salesOrderPaymentInfoList;

    /** 创建类型：1.天猫抓取，2.客服导入 */
    @TableField("SOURCE_TYPE")
    private Integer sourceType;
    
    /**
     * 虚拟支付金额扩展字段1
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalOtherVc、totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 天猫订单该字段为：集分宝支付金额
     */
    @TableField("EXT_VC1")
    private BigDecimal extVc1 = BigDecimal.ZERO;
    /**
     * 虚拟支付金额扩展字段2
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalOtherVc、totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 天猫订单该字段为：天猫点券支付金额
     */
    @TableField("EXT_VC2")
    private BigDecimal extVc2 = BigDecimal.ZERO;
    /**
     * 虚拟支付金额扩展字段3
     * 目前只是从平台接入记录在订单头上为报表提供相关信息，未应用到商品行上且未参与totalOtherVc、totalVc的计算，待后续从运营及财务层面确认退货及货款相关处理规则后再应用到这些层面
     * 
     */
    @TableField("EXT_VC3")
    private BigDecimal extVc3 = BigDecimal.ZERO;
    
    /**
     * 过仓失败原因
     */
    @TableField("TO_WH_REMARK")
    private String toWhRemark;
    
    /**
     * 关怀等级
     */
    @TableField("CARE_GRADE")
    private Integer careGrade;
    
    /**
     * 关怀备注
     */
    @TableField("CARE_MOMO")
    private String careMomo;
    
    /**
     * 直连订单是否已获取owner
     */
    @TableField("IS_CLOUD_STACK_SO")
    private Boolean isCloudStackSo = Boolean.FALSE;
    
    /**
     * 主分仓编码
     */
    @TableField("MAIN_BRANCH_WH_CODE")
    private String mainBranchWhCode;
    
    /**
     * 商品重量
     */
    @TableField("GOODS_WEIGHT")
    private BigDecimal goodsWeight;
    
    /** 是否自动过仓 **/
    @TableField("IS_AUTO_WH")
    private Boolean isAutoWh = Boolean.FALSE;
    
    /** 支付宝ID **/
    @TableField("ALIPAY_ID")
    private Long alipayId;
    
    /** 使用信用卡支付金额数 **/
    @TableField("CREDIT_CARD_FEE")
    private BigDecimal creditCardFee;

    /**
     * 是为直连WMS订单
     */
    @TableField("IS_DIRECT_WMS_ORDER")
    private Integer isDirectWmsOrder = SysWmsStatus.UNWMS;
    /**
     * 拆单时间
     */
    @TableField("SPLIT_ORDER_TIME")
    private Date splitOrderTime;
    
    /**
	 * 是否指定发货仓库
	 */
    @TableField("IS_ASSIGN_WH")
	private Boolean isAssignWh = Boolean.FALSE;
	
    //天猫国际官网直供主订单关税税费
	@TableField("ORDER_TAX_FEE")
    private BigDecimal orderTaxFee;
    
	/**
	 * 是否捆绑销售订单 
	 */
	@TableField("IS_BUNDLE")
	private Boolean isBundle = Boolean.FALSE;
	
    /**
     * 全渠道门店编号（若派到的是门店，则此处为门店id；若派到的是电商仓，则此处为电商仓的名字）
     * @return
     */
    @TableField("TARGET_CODE")
    private String targetCode;
    /**
     * 是否是全渠道订单
     */
    @TableField("IS_OMNICHANNEL_PARAM")
    private Boolean isOmnichannelParam = Boolean.FALSE;
    
	/**
	 * 
	 * @Description:  订单路由状态（0未处理，5处理失败，10处理成功）
	 * @author zhiyong.shi
	 * 2017年3月27日
	 */
	@TableField("ORDER_ROUTE_STATUS")
    private Integer orderRouteStatus = 0;
    /**
     * 星盘派单号
     * @return 
     */
    @TableField("ALLOCATION_CODE")
    private String allocationCode;
    
    
    
    /**
     * 
     * @Description: 是否为订单路由订单
     * @author zhiyong.shi
     * 2017年3月28日
     */
    @TableField("IS_ORDER_ROUTE")
    private Boolean isOrderRoute = Boolean.FALSE ;
    
    /**
     * 开票给企业或个人,1个人,2企业
     *  
     */
    @TableField("BUSINESS_TYPE")
    private Integer businessType;
    
    
	public SalesOrder(){}
	
	
	/**
     * 企业税号/纳税识别码
     */
	@TableField("BUYER_TAX_NO")
    private String buyerTaxNO;
    
    /**
     * 预售订单已付定金
     * @author xin.feng
     */
    @TableField("STEP_PAID_FEE")
    private BigDecimal stepPaidFee;
    
    /**
     * 预包装订单送货地址是否有更改
     * @author xin.feng
     */
    @TableField("IS_ADDR_CHANGED")
    private Boolean isAddrChanged = Boolean.FALSE;
    
    /**
     * 此订单发货将要使用的在IM系统对应的库位
     * 如预售订单该使用哪个库位发货
     *  @author xin.feng
     */
    @TableField("WH_LOCATION_CODE")
    private String whLocationCode;
    
    /**
     * @author xin.feng
     * 路由反馈的仓库是否支持预包装
     */
    @TableField("IS_SUPPORT_PRE_PACKAGE")
    private Boolean isSupportPrePackage;
    
    /**
     * @author xin.feng
     * 预售预包装订单支付尾款的时间
     */
    @TableField("STEP_PAY_TIME")
    private Date stepPayTime;
    
    /**
     * 全渠道门店编号（若派到的是门店，则此处为门店id；若派到的是电商仓，则此处为电商仓的名字） add by chenping 20170814 
     * @return
     */
    @TableField("ORIGINAL_TARGET_CODE")
    private String originalTargetCode;

}
