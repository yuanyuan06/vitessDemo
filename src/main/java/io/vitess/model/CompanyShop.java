package io.vitess.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.common.SuperEntity;
import io.vitess.constants.SysWmsStatus;
import io.vitess.enums.CompanyShopManagerMode;
import io.vitess.enums.SkuSplitType;
import io.vitess.model.base.DefaultTransTemplete;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 店铺信息
 * 
 * @author fanht
 * 
 */
@TableName("t_ma_tb_shop_info")
public class CompanyShop extends SuperEntity {

	private static final long serialVersionUID = -3096402703545912438L;

	/**
	 * PK
	 */
	@TableField("ID")
	private Long id;

	/**
	 * 店铺code
	 */
	@TableField("INNER_SHOP_CODE")
	private String innerShopCode;

	/**
	 * 淘宝ID
	 */
	@TableField("SHOP_ID")
	private String shopId;

	/**
	 * 是否已绑定
	 */
	@TableField("IS_BOUND")
	private Boolean isBound = true;

	/**
	 * 淘宝店铺名称
	 */
	@TableField("SHOP_NAME")
	private String shopName;

	/**
	 * 创建时间
	 */
	@TableField("CREATE_TIME")
	private Date createTime = new Date();

	/**
	 * version
	 */
	@Version
	private int version;

	/**
	 * 用户名
	 */
	@TableField("MEMBER_NAME")
	private String memberName;
	/**
	 * 电话
	 */
	@TableField("TELEPHONE")
	private String telephone;

	/**
	 * 地址
	 */
	@TableField("ADDRESS")
	private String address;

	/**
	 * 安装公司名称
	 */
	@TableField("INSTALL_COMPANY_NAME")
	private String InstallCompanyName;

	/**
	 * 商品销售模式
	 */
	@Deprecated
	@TableField("SALES_MODES")
	private String salesModesStr = "0";

	/**
	 * 商品销售模式 0: 付款经销 1: 代销 2: 结算经销 ##3: 混合模式(结算经销+代销) 4: 混合模式(付款经销+结算经销)
	 */
	@TableField("SALES_MODE")
	private Integer salesMode;

	/**
	 * 生命周期 0：不使用 1：开店 10：关店
	 */
//	private CompanyShopStatus lifeCycleStatus;
	@TableField("LIFE_CYCLE_STATUS")
	private Integer lifeCycleStatus;

	/** 所属公司CODE */
	@TableField("COMPANY")
	private String company;

	/** 所属公司名称 */
	@TableField("COMPANY_NAME")
	private String companyName;

	/**
	 * 店铺仓储模式
	 */
//	private CompanyShopWhModel whModel;
	@TableField("WH_MODEL")
	private Integer whModel;

	/**
	 * 所属行业
	 */
	@TableField("INDUSTRY")
	private String industry;

	/**
	 * 发票是否需要备注明细(税控发票用,标识发票备注栏中是否需要记录商品明细)
	 */
	@TableField("IS_BILLING_INVOICE_DETAIL")
	private Boolean isBillingInvoiceDetail;

	/** 固定经营项目名称(税控发票用) */
	@TableField("FIXED_OPERATING_ITEM")
	private String fixedOperatingItem;

	/** 自动拆票类型：1.按单张发票最大开票金额及备注长度拆票；2.不支持拆票 */
	@TableField("AUTO_SPLIT_TYPE")
	private Integer autoSplitType;

	/** 开票金额计算规则：1.只体现商品信息(扣除积分和折扣);2.发票明细包含两部分：商品明细、积分和拆扣明细(不支持系统自动拆票、手工拆票) */
	@TableField("INVOICE_AMT_RULE")
	private Integer invoiceAmtRule;

	/** 单张发票最大开票金额(如果不配置,则默认是 10000) */
	@TableField("MAX_INVOICE_AMT")
	private BigDecimal maxInvoiceAmt;

	/**
	 * 发票是否由宝尊开
	 */
	@TableField("IS_BAOZUN_BILLING_INVOICE")
	private Boolean isBaozunBillingInvoice;

	/**
	 * 是否默认开票 即该店铺所有订单进系统时默认将订单上is_needed_invoice设置为true
	 */
	@TableField("IS_DEFAULT_INVOICE")
	private Boolean isDefaultInvoice = Boolean.FALSE;

	/**
	 * 发票形式：false 纸质发票；true 电子发票
	 */
	@TableField("INVOICE_KIND")
	private Boolean invoiceKind = Boolean.FALSE;

	/**
	 * 是否自动过单
	 */
	@TableField("IS_AUTO_COMMIT")
	private Boolean isAutoCommit;

	/**
	 * 是否自动过仓到仓库（O2O订单）
	 */
	@TableField("IS_AUTO_COMMIT_TO_WH_FOR_O2O")
	private Boolean isAutoCommitToWhForO2o;

	/**
	 * 平台店铺编码
	 */
	@TableField("PLATFORM_SHOP_CODE")
	private String platformShopCode;

	/**
	 * 平台app KEY
	 */
	@TableField("APP_KEY")
	private String appKey;

	/**
	 * 平台 app Secret
	 */
	@TableField("APP_SECRET")
	private String appSecret;

	/**
	 * 平台 app session key
	 */
	@TableField("IS_MQ_SO")
	private String sessionKey;

	/**
	 * 卖家收货地址编号
	 */
	@TableField("SELLER_ADDRESS_ID")
	private Long sellerAddressId;

	/**
	 * 是否自动拆单 最早设计的拆单算法：按经营模式拆单（已作废）
	 */
	@Deprecated
	@TableField("IS_AUTO_SPLIT_ORDER")
	private Integer isAutoSplitOrder = 0;

	/**
	 * 是否应用含特殊商品挂起
	 */
	@TableField("IS_SUSPEND_FOR_SPECIAL_SKU")
	private Boolean isSuspendForSpecialSku = Boolean.FALSE;

	/**
	 * 是否自动完成Ra
	 */
	@TableField("IS_AUTO_FINISH_RA")
	private Boolean isAutoFinishRa = Boolean.TRUE;

	/**
	 * 订单默认支付方式:6[支付宝]
	 */
	@TableField("PAYMENT_TYPE")
	private Integer defPaymentType;

	/**
	 * 是否必须选择物流服务商
	 */
	@TableField("IS_REQUIRED_TRANS")
	private Boolean isRequiredTrans = Boolean.FALSE;

	/**
	 * 是否使用OMS物流模板
	 */
	@TableField("IS_USE_DEFTRANS")
	private Boolean isUseDefTrans = Boolean.FALSE;

	/**
	 * OMS物流模板
	 */
	private DefaultTransTemplete deftransTemp;

	/**
	 * 是否是O2O店铺（默认false）
	 */
	@TableField("IS_O2O_SHOP")
	private Boolean isO2OShop = Boolean.FALSE;

	/**
	 * 是否应用VMI促销 默认不应用,目前LEVIS和Denizen店铺存在vmi促销
	 */
	@TableField("IS_APPLY_VMI_PROMOTION")
	private Boolean isApplyVmiPromotion = Boolean.FALSE;

	/**
	 * 自动过仓时是否忽略备注
	 */
	@TableField("IS_IGNORE_MEMO")
	private Boolean isIgnoreMemo;

	/**
	 * 是否依次过仓 这个逻辑已经不需要了（直连wms）
	 */
	@Deprecated
	@TableField("IS_COMMIT_SUCCESSIVELY")
	private Boolean isCommitSuccessively;

	/**
	 * 是否校验英文地址
	 */
	@TableField("NEED_VERIFY_EN_ADDRESS")
	private Boolean needVerifyEnAddress = Boolean.FALSE;

	/**
	 * 是否自动创建退款
	 */
	@TableField("IS_AUTO_CREATE_RF")
	private Boolean isAutoCreateRf = Boolean.TRUE;

	/**
	 * 店铺切换开关：通过hub抓单的开关
	 */
	@TableField("IS_SYNC_TO_OMS")
	private Boolean isSyncToOms = Boolean.FALSE;

	/**
	 * 是否自动抓单：直接从淘宝抓单的开关
	 */
	@TableField("IS_AUTO_ORDER")
	private Boolean isAutoOrder = Boolean.FALSE;

	/**
	 * 是否抓取在售列表
	 */
	@TableField("IS_ON_SALES_SKU")
	private Boolean isOnSalesSku = Boolean.FALSE;

	/**
	 * 店铺的创单方式 1：独立线程；0：公共线程；
	 */
	@TableField("IS_MQ_SO")
	private Boolean isMqSo = Boolean.FALSE;

	/** 拆单方式，默认为：不拆分 **/
	@TableField("SKU_SPLIT_TYPE")
	private Integer skuSplitType = SkuSplitType.NO_SPLIT.getValue();
//	private SkuSplitType skuSplitType = SkuSplitType.NO_SPLIT;

	/**
	 * 是否开通云栈服务
	 */
	@Deprecated
	@TableField("IS_OPEN_CLOUD_STACK")
	private Boolean isOpenCloudStack = Boolean.FALSE;

	/**
	 * 默认发票抬头类型
	 */
	@TableField("DEF_INVOICE_TITLE_TYPE")
	private Integer defInvoiceTitleType;
//	private DefInvoiceTitleType defInvoiceTitleType;

	/**
	 * 是否解析订单服务信息
	 */
	@TableField("IS_PARSE_ORDER_SERVICE_INFO")
	private Boolean isParseOrderServiceInfo = Boolean.FALSE;

	/**
	 * 是否使用淘宝退款指令
	 */
	@TableField("IS_REFUND_DIRECT")
	private Boolean isRefundDirect = Boolean.FALSE;

	/**
	 * 店铺销售区域
	 */
	@TableField("BUSINESS_REGION_TYPE")
	private String businessRegionType;

	/**
	 * 是否使用依次过仓 这个逻辑已经不需要了（直连wms）
	 */
	@Deprecated
	@TableField("IS_OPEN_SEQ_TO_WH")
	private Boolean isOpenSeqToWh = Boolean.FALSE;

	/**
	 * 是否创建 电子凭证订单
	 */
	@TableField("IS_CREATE_ETICKET")
	private Boolean isCreateEticket = Boolean.FALSE;

	/**
	 * 是否开启数据分析
	 */
	@TableField("IS_OPEN_DATA_ANALYSIS")
	private Boolean isOpenDataAnalysis = Boolean.FALSE;

	/**
	 * 是否为单独的任务推数据到edw
	 */
	@TableField("IS_SPECIFIC_SHOP_TO_EDW")
	private Boolean isSpecificShopToEdw = Boolean.FALSE;

	/**
	 * 是否抓起未付款订单
	 */
	@TableField("IS_PARSE_NO_PAY_ORDER")
	private Boolean isParseNoPayOrder = Boolean.FALSE;

	/**
	 * 是否开启直连WMS
	 */
	@TableField("IS_OPEN_DIRECT_WMS")
	private Integer isOpenDirectWms = SysWmsStatus.UNWMS;
	/**
	 * 店铺code(toms/bzWms交互使用)
	 */
	@TableField("INTERFACE_SHOP_CODE")
	private String interfaceShopCode;
	/**
	 * 是否允许分仓发货
	 */
	@TableField("IS_ALLOW_DS")
	private Boolean isAllowDS = Boolean.FALSE;
	/**
	 * 是否开启拆单发送短信功能
	 */
	@TableField("IS_OPEN_SPLIT_ORDER_SEND_SMS")
	private Boolean isOpenSplitOrderSendSms = Boolean.FALSE;

	/**
	 * 是否是只做数据分析的店铺
	 */
	@TableField("IS_DA_SHOP")
	private Boolean isDaShop = Boolean.FALSE;

	/**
	 * 全量库存同步开关fanht
	 */
	@TableField("IS_OPEN_SYNC_INV")
	private Boolean isOpenSyncInv = Boolean.FALSE;

	/**
	 * 增量库存同步开关fanht
	 */
	@TableField("IS_SYNC_INVENTORY")
	private Boolean isSyncInventory = Boolean.FALSE;

	/**
	 * 增量是否创建自动发货订单lbc
	 */
	@TableField("IS_CREATE_AUTO_DELIVERY")
	private Boolean isCreateAutoDelivery = Boolean.FALSE;

	/**
	 * 仓储客户代码
	 */
	@TableField("WH_CUSTOMER_CODE")
	private String whCustomerCode;

	/**
	 * 是否在订单行上打标销售模式
	 */
	@TableField("IS_APPLY_SALES_MODE")
	private Boolean isApplySalesMode = Boolean.FALSE;

	/**
	 * 店铺平台类型
	 */
	@TableField("PLATFORM_TYPE")
	private String platformType;

	/**
	 * 平台发货 0, 非平台 1, 菜鸟发货 2, 全店物流宝 3, cj 4, cj集货(cj需求短期解决方案)
	 */
	@TableField("IS_CAI_NIAO")
	private Integer isCaiNiao = 0;

	/**
	 * 店铺是否开启库存共享
	 */
	@TableField("IS_SHARE_INVENTORY")
	private Boolean isShareInventory = Boolean.FALSE;

	/**
	 * toms端是否关闭库存共享（关闭意味着不在从pac获取出库outOwner）
	 */
	@TableField("IS_OWN_WMS")
	private Boolean isOwnWms = Boolean.FALSE;

	/**
	 * 货主编码 店铺code(toms/其他wms交互使用)
	 */
	@TableField("OWNER_CODE")
	private String ownerCode;

	/**
	 * 管理模式
	 */
	@TableField("MANAGER_MODE")
	private Integer managerMode = CompanyShopManagerMode.NORMAL.getValue();
//	private CompanyShopManagerMode managerMode = CompanyShopManagerMode.NORMAL;

	/**
	 * 组织
	 */
	@TableField("OU_ID")
	private OperationUnit ou;

	/**
	 * 店铺关联仓库
	 */
	private List<ShopWh> whList;

	/**
	 * 省
	 * 
	 */
	@TableField("PROVINCE")
	private String province;

	/**
	 * 市
	 * 
	 */
	@TableField("CITY")
	private String city;

	/**
	 * 区
	 */
	@TableField("DISTRICT")
	private String district;

	/**
	 * 是否支持一键拆单
	 */
	@TableField("IS_SURPORT_ONEKEY_SPLIT")
	private Boolean isSurportOneKeySplit = Boolean.FALSE;;

	/**
	 * 一键拆单行数上限值（默认为3）
	 */
	@TableField("ONEKEY_SPLIT_UPPERBOUND")
	private Integer oneKeySplitUpperBound = 2;

	/**
	 * 订单取消时是否要求填写取消原因
	 */
	@TableField("IS_NEED_REASON")
	private Boolean isNeedReason = Boolean.FALSE;

	/**
	 * 平台库存为零时，增量库存是否同步
	 */
	@TableField("IS_MQ_INV")
	private Boolean isMqInv = Boolean.TRUE;

	/**
	 * 
	 * @Description: 是否开启订单路由
	 * @author zhiyong.shi 2017年3月2日
	 */
	@TableField("IS_ORDER_ROUTE")
	private Boolean isOrderRoute = false;
	/**
	 * 新增发送订单路由的owner
	 */
	@TableField("CODE")
	private String code;
	/**
	 * 是否开启应用新的退款审核规则
	 */
	@TableField("IS_APPLY_AUDIT_RULE")
	private Boolean isApplyAuditRule = false;
	/**
	 * 行业名称
	 */
	@TableField("INDUSTRY_NAME")
	private String industryName;

	/**
	 * 海外订单是否挂起
	 */
	@TableField("IS_HANG_BY_ABROAD_ORDER")
	private Boolean isHangByAbroadOrder = true;

	/**
	 * 
	 * @Description: 依次过仓
	 * @author zhiyong.shi 2017年6月8日
	 */
	@TableField("IS_ONE_BY_ONE_TO_WH")
	private Boolean isOneByOneToWh = false;
	/**
	 * 是否开启配送方式拆单
	 */
	@TableField("IS_SHIPPING_METHODS")
	private Boolean isShippingMethods = false;

	/**
	 * 开启ag退款
	 */
	@TableField("OPEN_AG_REFUND")
	private Boolean openAgRefund;

	/**
	 * 
	 * @Description:LF仓对接有区域要求，定义和LF对接的物流商开关
	 * @author zhiyong.shi 2017年6月29日
	 */
	@TableField("IS_TRANS")
	private Boolean isTrans = Boolean.FALSE;

	/**
	 * 是否开启预售
	 * 
	 * @author xin.feng
	 */
	@TableField("IS_PRESALE")
	private Boolean isPresale = Boolean.FALSE;

	/**
	 * 是否开启预售预过仓包装
	 * 
	 * @author xin.feng
	 */
	@TableField("IS_PREPACKAGE")
	private Boolean isPrepackage = Boolean.FALSE;
	/**
	 * 
	 * @Description: 斯凯奇定制，却非要做成通用的，退货运单号是否必填开关。。。
	 * @author zhiyong.shi 2017年8月1日
	 */
	@TableField("IS_TRACKING_NO")
	private Boolean isTrackingNo = Boolean.FALSE;

	/**
	 * 是否支持换转退
	 * 
	 * @author hui.li
	 */
	@TableField("IS_TRADE_TO_RETURN")
	private Boolean isTradeToReturn = Boolean.FALSE;

	/**
	 * 是否开启退换货并行
	 * 
	 * @author xiao.wang
	 */
	@TableField("IS_PARALLEL")
	private Boolean isParallel = Boolean.FALSE;

    /**
     * 一个平台对接码根据逻辑库位同步多份库存到平台
     * 业务场景：Nike预售库存同步
     * @author xin.feng
     */
    @TableField("IS_MULTI_SKU")
    private Boolean isMultiSku = Boolean.FALSE;

}