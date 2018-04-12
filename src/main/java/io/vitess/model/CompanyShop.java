package io.vitess.model;

import io.vitess.constants.SysWmsStatus;
import io.vitess.enums.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author YSH4807
 * @date 2018/4/11 11:14
 */
@Data
public class CompanyShop implements Serializable {

    /**
     * PK
     */
    private Long id;

    /**
     * 店铺code
     */
    private String innerShopCode;

    /**
     * 淘宝ID
     */
    private String shopId;

    /**
     * 是否已绑定
     */
    private Boolean isBound = true;

    /**
     * 淘宝店铺名称
     */
    private String shopName;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * version
     */
    private int version;

    /**
     * 用户名
     */
    private String memberName;
    /**
     * 电话
     */
    private String telephone;

    /**
     * 地址
     */
    private String address;

    /**
     * 安装公司名称
     */
    private String InstallCompanyName;

    /**
     * 商品销售模式
     *0: 付款经销
     *1: 代销
     *2: 结算经销
     *##3: 混合模式(结算经销+代销)
     *4: 混合模式(付款经销+结算经销)
     */
    private Integer salesMode;

    /**
     * 生命周期
     * 0：不使用
     * 1：开店
     * 10：关店
     */
    private CompanyShopStatus lifeCycleStatus;

    /** 所属公司CODE */
    private String company;

    /** 所属公司名称  */
    private String companyName;

    /**
     * 店铺仓储模式
     */
    private CompanyShopWhModel whModel;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 发票是否需要备注明细(税控发票用,标识发票备注栏中是否需要记录商品明细)
     */
    private Boolean isBillingInvoiceDetail;

    /** 固定经营项目名称(税控发票用) */
    private String fixedOperatingItem;

    /** 自动拆票类型：1.按单张发票最大开票金额及备注长度拆票；2.不支持拆票  */
    private Integer autoSplitType;

    /** 开票金额计算规则：1.只体现商品信息(扣除积分和折扣);2.发票明细包含两部分：商品明细、积分和拆扣明细(不支持系统自动拆票、手工拆票) */
    private Integer invoiceAmtRule;

    /** 单张发票最大开票金额(如果不配置,则默认是 10000) */
    private BigDecimal maxInvoiceAmt;

    /**
     * 发票是否由宝尊开
     */
    private Boolean isBaozunBillingInvoice;

    /**
     * 是否默认开票 即该店铺所有订单进系统时默认将订单上is_needed_invoice设置为true
     */
    private Boolean isDefaultInvoice = Boolean.FALSE;

    /**
     * 发票形式：false 纸质发票；true 电子发票
     */
    private Boolean invoiceKind = Boolean.FALSE;

    /**
     * 是否自动过单
     */
    private Boolean isAutoCommit;

    /**
     * 是否自动过仓到仓库（O2O订单）
     */
    private Boolean isAutoCommitToWhForO2o;

    /**
     * 平台店铺编码
     */
    private String platformShopCode;

    /**
     * 平台app KEY
     */
    private String appKey;

    /**
     * 平台 app Secret
     */
    private String appSecret;

    /**
     * 平台 app session key
     */
    private String sessionKey;

    /**
     * 卖家收货地址编号
     */
    private Long sellerAddressId;

    /**
     * 是否应用含特殊商品挂起
     */
    private Boolean isSuspendForSpecialSku = Boolean.FALSE;

    /**
     * 是否自动完成Ra
     */
    private Boolean isAutoFinishRa = Boolean.TRUE;

    /**
     * 订单默认支付方式:6[支付宝]
     */
    private Integer defPaymentType;

    /**
     * 是否必须选择物流服务商
     */
    private Boolean isRequiredTrans = Boolean.FALSE;

    /**
     * 是否使用OMS物流模板
     */
    private Boolean isUseDefTrans = Boolean.FALSE;

    /**
     * OMS物流模板
     */
    private DefaultTransTemplete deftransTemp;

    /**
     * 是否是O2O店铺（默认false）
     */
    private Boolean isO2OShop = Boolean.FALSE;

    /**
     * 是否应用VMI促销 默认不应用,目前LEVIS和Denizen店铺存在vmi促销
     */
    private Boolean isApplyVmiPromotion = Boolean.FALSE;

    /**
     * 自动过仓时是否忽略备注
     */
    private Boolean isIgnoreMemo;

    /**
     * 是否校验英文地址
     */
    private Boolean needVerifyEnAddress = Boolean.FALSE;

    /**
     * 是否自动创建退款
     */
    private Boolean isAutoCreateRf = Boolean.TRUE;

    /**
     * 店铺切换开关：通过hub抓单的开关
     */
    private Boolean isSyncToOms = Boolean.FALSE;

    /**
     * 是否自动抓单：直接从淘宝抓单的开关
     */
    private Boolean isAutoOrder = Boolean.FALSE;

    /**
     * 是否抓取在售列表
     */
    private Boolean isOnSalesSku = Boolean.FALSE;

    /**
     * 店铺的创单方式 1：独立线程；0：公共线程；
     */
    private Boolean isMqSo = Boolean.FALSE;

    /** 拆单方式，默认为：不拆分 **/
    private SkuSplitType skuSplitType = SkuSplitType.NO_SPLIT;


    /**
     * 默认发票抬头类型
     */
    private DefInvoiceTitleType defInvoiceTitleType;

    /**
     * 是否解析订单服务信息
     */
    private Boolean isParseOrderServiceInfo = Boolean.FALSE;

    /**
     *是否使用淘宝退款指令
     */
    private Boolean isRefundDirect  = Boolean.FALSE;

    /**
     * 店铺销售区域
     */
    private String businessRegionType;

    /**
     * 是否创建 电子凭证订单
     */
    private Boolean isCreateEticket  = Boolean.FALSE;

    /**
     * 是否开启数据分析
     */
    private Boolean isOpenDataAnalysis = Boolean.FALSE;

    /**
     * 是否为单独的任务推数据到edw
     */
    private Boolean isSpecificShopToEdw = Boolean.FALSE;

    /**
     * 是否抓起未付款订单
     */
    private Boolean isParseNoPayOrder = Boolean.FALSE;

    /**
     * 是否开启直连WMS
     */
    private Integer isOpenDirectWms = SysWmsStatus.UNWMS;
    /**
     * 店铺code(toms/bzWms交互使用)
     */
    private String interfaceShopCode;
    /**
     * 是否允许分仓发货
     */
    private Boolean isAllowDS = Boolean.FALSE;
    /**
     * 是否开启拆单发送短信功能
     */
    private Boolean isOpenSplitOrderSendSms = Boolean.FALSE;

    /**
     * 是否是只做数据分析的店铺
     */
    private Boolean isDaShop = Boolean.FALSE;

    /**
     * 全量库存同步开关fanht
     */
    private Boolean isOpenSyncInv = Boolean.FALSE;

    /**
     * 增量库存同步开关fanht
     */
    private Boolean isSyncInventory = Boolean.FALSE;

    /**
     * 增量是否创建自动发货订单lbc
     */
    private Boolean isCreateAutoDelivery = Boolean.FALSE;

    /**
     * 仓储客户代码
     */
    private String whCustomerCode;

    /**
     * 是否在订单行上打标销售模式
     */
    private Boolean isApplySalesMode = Boolean.FALSE;

    /**
     * 店铺平台类型
     */
    private String platformType;

    /**
     * 平台发货
     * 0, 非平台
     * 1, 菜鸟发货
     * 2, 全店物流宝
     * 3, cj
     * 4, cj集货(cj需求短期解决方案)
     */
    private Integer isCaiNiao = 0;

    /**
     * 店铺是否开启库存共享
     */
    private Boolean isShareInventory = Boolean.FALSE;

    /**
     * toms端是否关闭库存共享（关闭意味着不在从pac获取出库outOwner）
     */
    private Boolean isOwnWms = Boolean.FALSE;

    /**
     * 货主编码
     * 店铺code(toms/其他wms交互使用)
     */
    private String ownerCode;

    /**
     * 管理模式
     */
    private CompanyShopManagerMode managerMode = CompanyShopManagerMode.NORMAL;

    /**
     * 组织
     */
    private OperationUnit ou;

    /**
     * 店铺关联仓库
     */
    private List<ShopWh> whList;


    /**
     * 省
     *
     */
    private String province;

    /**
     * 市
     *
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 是否支持一键拆单
     */
    private Boolean isSurportOneKeySplit = Boolean.FALSE;;

    /**
     * 一键拆单行数上限值（默认为3）
     */
    private Integer oneKeySplitUpperBound = 2;

    /**
     * 订单取消时是否要求填写取消原因
     */
    private Boolean isNeedReason = Boolean.FALSE;

    /**
     * 平台库存为零时，增量库存是否同步
     */
    private Boolean isMqInv = Boolean.TRUE;

    /**
     * 新增发送订单路由的owner
     */
    private String code ;
    /**
     * 是否开启应用新的退款审核规则
     */
    private Boolean isApplyAuditRule = false;
    /**
     *行业名称
     */
    private String industryName;

    /**
     * 海外订单是否挂起
     */
    private Boolean isHangByAbroadOrder = true;

    /**
     * 是否开启配送方式拆单
     */
    private Boolean isShippingMethods = false;

    /**
     * 开启ag退款
     */
    private Boolean openAgRefund;
}
