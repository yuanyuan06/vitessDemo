package io.vitess.enums;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class Constants {

    public static final String EXCEL_RESULT_FILE = "excel/excel_import_result.xls";
    public static final String EXCEL_RESULT_LIST_NAME = "resultList";
    public static final String EXCEL_RESULT_SO_GIFT_BATCH_LIST_NAME = "salesOrderGiftBackResultList";
    public static final String EXCEL_MQSOLOG_LINE_EXPORT = "excel/mqsolog_line_export.xls";
    public static final String EXPORT_SO_GIFT_RESULT_LIST = "excel/tpl_export_so_gift_result_list.xls";
    public static final Integer ADD_GIFT = 0;
    public static final Integer DEL_GIFT = 1;
    public static final Integer IS_BAR_CODE = 1;
    public static final Integer IS_SKU_CODE = 0;
    public static final String BAR_CODE = "条形码";
    public static final String SKU_CODE = "SKU编码";
    public static final String CANCEL_ORDER_REASON_CATEGORY = "soCancelReason";
    public static final String TPL_SO_BATCH_CANCEL_INFO_RESULT = "excel/tpl_salesorder_batch_cancel_info_result" + Constants.EXCEL_XLS;
    public static final String TPL_SPECIFY_RESULT = "excel/specify-sku-appointment-wh_result" + Constants.EXCEL_XLS;
    /** 子订单号分隔符 */
    public static final String PLATFORMORDERCODEN_TAG = "-";

    public static final String PACS_ORDER_CODE = "pacsOrderCode";

    /** 同步至天猫的日期格式 */
    public static final String TB_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** 取消订单的保留时间 */
    public static final int CANCELED_LEAVE_DAY = 30;

    /** 1积分=0.01元 */
    public static final BigDecimal POINT_PRICE = new BigDecimal("0.01");

    /** 哈根达斯淘宝旗舰店CODE */
    public static final String HD_INNER_SHOP_CODE = "281";
    /** 哈根达斯 天猫特供款 月光宝盒月饼礼盒 */
    public static final String HD_SKU = "HD00272,HD00273";
    /** AF店铺CODE */
    public static final String AF_INNER_SHOP_CODE = "4803";

    public static final String SYSTEM_CODE = "oms-tmall";
    
    // -- 系统CODE：针对与PAC交互认证
    public static final String PAC_SYS_CODE = "tmalloms";

    public static final String CHOOSEOPTION_CATEGORY_CODE_PRODUCTTYPE = "productType";

    public static final String CHOOSEOPTION_CATEGORY_CODE_INDUSTRY = "industry";

    public static final String DF_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 订单状态同步到hub, t_send_hub_msg
	 * 同步中
	 */
	public static final String IN_PROCESS = "同步中";
	/**
	 * 同步成功
	 */
	public static final String SYNC_SUCCESS = "成功";
	/**
	 * 同步失败
	 */
	public static final String SYNC_FAIL = "失败";

    
    // -- 状态：使用中|已禁用
    public static final String CHOOSEOPTION_CATEGORY_CODE_STATUS = "status";

    // public static final String SALESMODE = "salesMode";

    public static final String KEMU = "kemu";

    public static final String ISAVAILABLE = "isAvailable";

    /******************************************** oms *************************************************************/

    public static final String STATUS_TRUE = "1";

    public static final String STATUS_FALSE = "0";

    public static final String RS_TRUE = "true";

    public static final String RS_FALSE = "false";

    public static final String UTF_8 = "UTF-8";

    public static final String GBK = "GBK";

    public static final String EXCEL_CSV = ".csv";

    public static final String EXCEL_XLS = ".xls";

    public static final String ZIP = ".zip";

    public static final String EXCEL_XLSX = ".xlsx";

    public static final String STA_SKUS_SLIPT_STR = ":";

    public static final String STA_INV_SKUS_SLIPT_STR = "_";

    public static final String ISO_8859_1 = "ISO_8859_1";

    public static final Integer LOCATION_SIZE = 10;

    public static final Integer YES = 1;

    public static final Integer NO = 0;

    // -- 权限：组织结构管理
    public static final String PRIVILEGE_ACL_ROOT_OU_MAINTAIN = "ACL_ROOT_OU_MAINTAIN";

    // 经销商：宝尊自己
    public static final String SUPPLIER_BAOZUN = "BAOZUN";

    // 订单发货MQ队列
    public static final String O2O2HB_ORDER_LOGISTICS_MQ = "o2o2hb_order_logistics_mq";

    /**
     * 运送方式(快递附加服务) 1:普通 4:空运 6:陆运 7:特惠
     */
    public static Integer TRANS_SERVICE_TYPE_NORMAL = new Integer(1);

    /**
     * 快递时间限制（快递附加服务） 1：普通 5：当日 6：次日
     */
    public static Integer TRANS_TIME_TYPE_NORMAL = new Integer(1);

//    public static final Long DISNEY_SALES_SHOP_ID = 3022L;

    public static final int BUFFER = 2048;

    public static final String SEMICOLON = ";";

    public static final String IS_YES = "Y";
    public static final String IS_NO = "N";

    // // -- 状态：使用中|已禁用
    public static final String CHOOSEOPTION_CATEGORY_CODE_SALESORDER = "salesOrderType";
    public static final String CHOOSEOPTION_CATEGORY_CODE_PAYMENTTYPE = "paymentType";
    public static final String CHOOSEOPTION_CATEGORY_CODE_SALESMODE = "salesMode";
    public static final String CHOOSEOPTION_CATEGORY_CODE_SHOP_STATUS = "shopStatus";
    public static final String CHOOSEOPTION_CATEGORY_CODE_MQ_SO_STATUS = "mqSoLogStatus";
    public static final String CHOOSEOPTION_CATEGORY_CODE_VMIORDERTYPE = "vmiOrderOpType";
    public static final String CHOOSEOPTION_CATEGORY_CODE_SOGOODSTYPE = "soGoodsType";

    public static final String CHOOSEOPTION_CATEGORY_CODE_SHOPTYPE = "shopType";// 店铺类型
    public static final String CHOOSEOPTION_CATEGORY_CODE_PLATFORMTYPE = "platformType";// 平台类型
    public static final String CHOOSEOPTION_CATEGORY_CODE_SHOP_WHMODEL = "shopWhModel";// 店铺仓储模式
    public static final String CHOOSEOPTION_CATEGORY_CODE_OPERATION_TYPE = "shopOperationType";// 店铺主要经营模式
    public static final String CHOOSEOPTION_CATEGORY_CODE_COST_CENTER_CODE = "costCenterCode";// 成本中心编码
    public static final String CHOOSEOPTION_CATEGORY_CODE_ASSET_BIGTYPE = "assetBigType";// 资产大类
    public static final String CHOOSEOPTION_CATEGORY_CODE_DEF_INVOICE_TITLE_TYPE = "defInvoiceTitleType";// 默认发票抬头类型


    // -- 权限：组织结构管理

    public static final String CHOOSEOPTION_STATUS = "optionStatus";
    public static final String CUSTOM_OPTION = "自定义";
    public static final String LIFECYCLESTATUS = "lifeCycleStatus";
    public static final String CUSTOMERTYPE = "customerType";
    public static final String PO_PAYMENT_TYPE = "poPaymentType";
    public static final String PO_CREDIT_TYPE = "poCreditType";
    public static final String PO_ACTIVITY_TYPE = "ActivityType";
    public static final String PO_PRIORITY = "poPriority";
    public static final String PO_STATUS = "poStatus";

    public static final Integer PRODUCT_LIFE_CYCLE_STATUS_ENABLED = new Integer(0); // 不可用
    public static final Integer PRODUCT_LIFE_CYCLE_STATUS_DISABLED = new Integer(1); // 可用

    public static final int disAvailable = 0;
    public static final int isAvailable = 1;
    public static final String IMPORT_EXL_SUCC_RESULT = "IMPORT_EXL_SUCC_RESULT";
    public static final String IMPORT_EXL_ERROR_RESULT = "IMPORT_EXL_ERROR_RESULT";

    public static final String STORE_PRODUCT_OPERATETYPE = "storeProductOperationType";

    // 系统导出文件存放路径
    public static final String FILE_PATH_EXPORT_FILE = "./ExportFile";
    public static final String FILE_PATH_INVOICE = FILE_PATH_EXPORT_FILE + "/Invoice";
    public static final String FILE_PATH_EXPRESS_BILL = FILE_PATH_EXPORT_FILE + "/ExpressBill";

    /** chooseOption值 begin */
    public static final String CHOOSE_OPTION_PAYMENT_TYPE = "paymentType";
    public static final String CHOOSE_OPTION_SALESORDER_TYPE = "salesOrderType";
    public static final String CHOOSE_OPTION_SO_STATUS = "salesOrderStatus";
    public static final String CHOOSE_OPTION_SOINVOICETYPE = "soInvoiceType";
    public static final String CHOOSE_OPTION_PAYMENT_STATUS = "soPaymentStatus";
    public static final String CHOOSE_OPTION_poPaymentType = "poPaymentType";
    public static final String CHOOSE_OPTION_poCreditType = "poCreditType";
    public static final String CHOOSE_OPTION_poPriority = "poPriority";
    public static final String CHOOSE_OPTION_poStatus = "poStatus";
    public static final String CHOOSE_OPTION_trueOrFalse = "trueOrFalse";
    public static final String CHOOSE_OPTION_SUSPEND_REASON_TYPE = "suspendReasonType";
    public static final String CHOOSE_OPTION_SALESORDER_SPECIAL_TYPE = "soSpecialType";
    /** 0/1下拉选项 */
    public static final String CHOOSE_OPTION_TRUEFALSE = "trueFalse";
    public static final String CHOOSE_OPTION_ProductScope = "ProductScope";
    public static final String CHOOSE_OPTION_PromotionStatus = "PromotionStatus";
    public static final String CHOOSE_OPTION_ApplyMode = "ApplyMode";
    public static final String CHOOSE_OPTION_PromotionProductType = "PromotionProductType";
    public static final String CHOOSE_OPTION_CommonStatus = "commonStatus";
    public static final String CHOOSE_OPTION_SmsType = "SmsType";
    public static final String CHOOSE_OPTION_SmsParams = "SmsTemplateParam_";
    public static final String CHOOSE_OPTION_SmsSuppliers = "SmsSuppliers";
    public static final String CHOOSE_OPTION_OuTypes = "OuTypes";
    public static final String CHOOSE_OPTION_ADJUSTREASON_TYPE = "adjustReasonType";
    public static final String CHOOSE_OPTION_TRANSSERVICE_TYPE = "transServiceType";
    /** 最大同步订单数 */
    public static final String CHOOSE_OPTION_SYNCLISTMAXSIZE = "syncListMaxSize";
    /** 可取消节点nodeNo列表 */
    public static final String CHOOSE_OPTION_CANCELNODENOS = "cancelNodeNos";
    /** 可修改节点nodeNo列表 */
    public static final String CHOOSE_OPTION_MODIFYNODENOS = "modifyNodeNos";
    /** 可修改发票nodeNo列表 */
    public static final String CHOOSE_OPTION_INVOICENODENOS = "invoiceNodeNos";
    /** 可修改物流服务商nodeNo列表 */
    public static final String CHOOSE_OPTION_TRANSNODENOS = "transNodeNos";
    /** 订单赠品行编辑节点列表 */
    public static final String CHOOSE_OPTION_GIFTLINENODES = "editSalesOrderGiftLineNodes";

    public static final String TRANS_LIST = "transList";

    /** chooseOption值 end */
    // task code
    public static final String TASK_CODE_SO = "SO";
    public static final String TASK_CODE_RA = "RA";
    public static final String TASK_CODE_RF = "RF";

    public static final String CHOOSEOPTION_CATEGORY_CODE_RETURNAPP_STATUS = "returnappstatus";
    // 退换货类型
    public static final String RETURN_APPLICATION_TYPE = "returnapptype";
    public static final String CHOOSEOPTION_CATEGORY_CODE_RTNTRANSFEE_PAYMENTTYPE = "rtntransfeepaymenttype";
    public static final String CHOOSEOPTION_CATEGORY_CODE_RTN_REASON = "returnreason";
    public static final String CHOOSEOPTION_CATEGORY_CODE_RTN_SKU_INV_STATUS = "returnskuinvstatus";

    // 退货方式
    public static final String REFUND_APPLICATION_TYPE = "refundtype";

    // 联通商城订单正式店铺ID
    public static final String UNICOM_ALLSHOP_ID = "unicomAllShopId";
    // 退款状态
    public static final String REFUND_APPLICATION_STATUS = "refundstatus";
    // 退款方式
    public static final String REFUND_APPLICATION_RETURN_WAY = "refundreturnWay";
    // 退款原因
    public static final String REFUND_APPLICATION_REASON = "refundreason";
    // 退款创建方式
    public static final String REFUND_APPLICATION_CREATE_MODE = "refundCreateMode";
    // 索赔对象
    public static final String REFUND_CLAIM_TARGET = "claimTarget";
    // 信息确认
    public static final String INFO_IS_VALIDATE = "infoIsValidate";
    // 退款类型(未发货退款)renqi.li
    public static final String REFUND_APPLICATION_CREATE_UNDELIVERED = "refundCreateUndelivered";
    // 退款类型(退货退款)renqi.li
    public static final String REFUND_APPLICATION_CREATE_RETURN = "refundCreateReturn";
    // 退款类型(补偿退款)renqi.li
    public static final String REFUND_APPLICATION_CREATE_COMPENSATE = "refundCreateCompensate";
    // 退款类型(索赔退款)renqi.li
    public static final String REFUND_APPLICATION_CREATE_CLAIMS = "refundCreateClaims";
    // 退款类型(其他退款)renqi.li
    public static final String REFUND_APPLICATION_CREATE_OTHERS = "refundCreateOthers";
    // 事件状态
    public static final String EVENT_STATUS = "eventStatus";
    public static final String EVENT_PROCESS_RESULT = "eventProcessResult";
    public static final String EVENT_SIGN = "eventSign";
    // 官方商城MQ接口相关常量
    // 测试
    // public static Long GODIVA_BS_SHOP_ID = 1902L; //Godiva官方商城
    // public static final Long APPLE_SHOP_ID = 5017L;//APPLE 官方旗舰店
    // 正式
    public static final Long TMALL_GDV_BS_SHOP_ID = 2362L; // GODIVA TMALL店铺ID
    public static final Long MICROSOFT_BS_ID = 2522L;// MICROSOFT_BS_ID 2522L
    public static final Long MICROSOFT_BS_ID_TEST = 2065L;// MICROSOFT_BS_ID
    public static final Long MKF_BS_ID = 4164L;// 卖客疯测试店铺ID:开发库：4145，测试库：4123，正式库：4164
    public static Long GODIVA_BS_SHOP_ID = 1902L; // GODIVA官方商城
    public static final Long COACH_BS_ID = 2442L; // coach官方商城店铺ID
    public static final Long CONVERSE_BS_SHOP_ID = 2306L; // CONVERSE官方商城 public static final Long
                                                          // COACH_BS_ID = 2442L; // coach官方商城店铺ID
    public static final Long PHILIPS_BS_SHOP_ID = 4602L;// philips官方商城 4602L
    public static final Long NBA_MOBILE_BS_SHOP_ID = 4523L; // NBA Mobile官方商城
    public static final Long HAAGENDAZS_BS_SHOP_ID = 3802L; // 哈根达斯官网
    public static final Long ESP_BS_SHOP_ID = 1842L; // ESP官方商城店铺ID
    public static final Long NIKE_TMALL_SHOP_ID = 2042L; // Nike官方旗舰店ID
    public static final Long MS_TB_SHOP_ID = 2622L; // 马莎淘宝官方旗舰店
    public static final Long MAS_FOOD_SHOP_ID = 3702L; // 马莎食品旗舰店
    public static final Long IT_TB_SHOP_ID = 1482L; // it官方旗舰店
    public static final Long APPLE_OU_ID = 3462L;// APPLE 组织id(测试)
    public static final Long APPLE_SHOP_ID = 3742L;// APPLE 官方旗舰店 (测试)
    public static final Long APPLE_OU_ID_OFFICIAL = 3802L;// APPLE 组织id(正式)
    public static final Long APPLE_SHOP_ID_OFFICIAL = 4062L;// APPLE 官方旗舰店 (正式)
    public static final String VIM_WH_SOURCE_AEO_IDS = "AEO_IDS";
    public static final Long COACH_SHOP_ID = 2442L;
    public static final Long ROOKIE_BS_SHOP_ID = 3222L;// philips官方商城
    public static final Long UA_TMALL_SHOP_ID = 150000041L;// ua TMALL店铺

    /** 发票默认抬头 */
    public static final String COMMON_SO_INVOICE_TAX_TITLE = "个人";
    public static final String COMMON_SO_INVOICE_TAX_FIXD_OPERATING_ITEM = "商品"; // 如果店铺上没有配置固定经营项目,

    public static final BigDecimal COMMON_SO_INVOICE_TAX_MAX_AMT = new BigDecimal(10000); // 单张发票最大开票金额
    // 使用该值
    public static final int TAX_MEMO_SUPPORTED_MAX_LENGTH = 252; // 发票备注栏输出支持的最大长度
    public static final int SN_RESERVED_LENGTH = 40; // SN号最大预留长度
    public static final int TAX_MEMO_RESERVED_LENGTH = 25; // 发票备注预留长度
    public static final String TAX_DEFAULT_PAYEE = "钱芳芳"; // 票面收款人,用于机打发票票面打印
    public static final String TAX_DEFAULT_DRAWER = "杨招东"; // 票面开票人,用于机打发票票面打印

    public static final String SKU_LOGISTIC_TYPE_EXPRESS = "01"; // 尊宝快递（1～4天）
    public static final String SHOP_TYPE_TB_02 = "TB02"; // 淘宝代销+不发货
    public static final Integer SALES_ORDER_STATUS_SUBMITTED = 1; // 已提交

    // private static ResourceBundle config = ResourceBundle.getBundle("interface");
    // public static final String WMS_SO_CANCEL = config.getString("wms.so.cancel");
    // public static final String WMS_PO_STOCKIN = config.getString("wms.po.stockin");
    // public static final String WMS_RETURNPO_CREATE = config.getString("wms.returnpo.create");
    // public static final String WMS_RETURNPO_CANCEL = config.getString("wms.returnpo.cancel");
    // // public static final String WMS_RETURN_CANCEL = config.getString("wms.return.cancel");
    //
    // public static final String CAS_USER_REGISTER = config.getString("cas.user.register");
    // public static final String CAS_USER_PWD_UPDATE = config.getString("cas.user.pwd.update");
    // public static final String CAS_USER_PWD_UPDATE_SU =
    // config.getString("cas.user.pwd.update.su");
    //
    // public static final String WMS_SOURCE = "SYSTEM";
    // public static final String RETURN_PO_STATUS = "returnPoStatus";
    // public static final String RETURN_PO_PICKUPTYPE = "returnPoPickupType";
    //
    // public static ResourceBundle upload_file_config =
    // ResourceBundle.getBundle("config/config_upload_file");
    // public static String returnPoFilePath =
    // String.valueOf(upload_file_config.getString("returnPo.fileUploadPath"));
    // public static String adPricePoFilePath =
    // String.valueOf(upload_file_config.getString("adPricePo.fileUploadPath"));
    // public static String refundFilePath =
    // String.valueOf(upload_file_config.getString("refund.fileUploadPath"));

    // 其他物流资源
    public static final String OTHER_TRANS_CODE = "V_OTHERS";

    public static final Integer ORDER_CANCEL_REASON_ONE_KEY_SPLIT = 13;//一键拆单的订单取消原因默认为拆单
    public static final Integer ORDER_CANCEL_REASON_DEFAULT = 0;//无订单取消原因

    // 组合商品状态： 有效/作废
    public static final String COM_BO_SKU_STATUS = "comBoSkuStatus";
    // 客户K3类型： 公司/个人
    public static final String K3_CUSTOMER_TYPE = "k3CustomerType";
    // 付款类型
    public static final String PAYMENT_TYPE = "paymentType";
    // 管理模式
    public static final String MANAGER_MODEL = "managerModel";

    // 客户编码
    public static final String PAYMENTTYPE_001TOWN = "104"; // 一城支付方式
    public static final String SO_PAYMENT_TYPE_COD = "1"; // 货到付款
    public static final String YICHEN_BIG_CUSTOMER_CODE = "K00014"; // 一城卡支付的大客户以对应的客户编码
    public static final String SANKE_CUSTOMER_CODE = "SK001"; // 散客对应的客户编码

    public static final String COMPANY_SHOP_NAME_MICROSOFT = "MicroSoft官方商城";

    public static final int SO_SALES_ORDER_WORKFLOW_CANCEL_NODE = 16; // 销售单对应取消节点
    // public static final Integer TASK_CURRENTNODENO = 24;//订单 待取消确定

    /** 玛莎写入中间表数据 start ***/
    public static final String VMI_LOG_OP_TYPE_SEND = "1";// 发送成功
    public static final String VMI_LOG_OP_TYPE_CONFIRM_SUCCESS = "2";// 订单确认成功
    public static final String VMI_LOG_OP_TYPE_CONFIRM_FAULT = "3";// 订单确认失败
    public static final String VMI_LOG_OP_TYPE_OUT_SUCCESS = "4";// 出库成功
    public static final String VMI_LOG_OP_TYPE_DEAl_SUCCESS = "5";// 取消处理完成
    public static final String VMI_LOG_OP_TYPE_NO_CALE = "6";// 未发送
    /** 玛莎写入中间表数据 end ***/

    public static final Long SKU_TEMPLATE_ID = 521L;
    public static final Long SKU_TEMPLATE_ID_NOCOLOR = 501L;
    public static final Long SKU_TEMPLATE_ID_NOSIZE = 561L;
    public static final Long SKU_TEMPLATE_ID_NOSIZE_NOCOLOR = 481L;

    // sku life cycle status
    public static final Integer SKU_STATUS_ONSALES = 5;
    public static final Integer SKU_STATUS_OFFSALES = 1;
    public static final Integer SKU_STATUS_DISABLED = 0;

    public static final String IT_NATURAL_PROPERTY_CODE = "1243.03"; // 服装

    // T_MAS_ORDER_STATUS表状态已更新

    public static final Integer T_MAS_ORDER_STATUS_DATA_Status_NO = new Integer(4); // 订单已取消
    public static final Integer T_MAS_ORDER_STATUS_DATA_Status_OK = new Integer(5); // 订单已确认

    public static final Integer MAS_ORDER_STATUS_CONFIRM_UN_DONE = new Integer(0); // 待处理
    public static final Integer MAS_ORDER_STATUS_CONFIRM_SUCCESS = new Integer(1); // 处理成功
    public static final Integer MAS_ORDER_STATUS_CONFIRM_IGNORE = new Integer(-1); // 跳过不处理(由于订单当前状态不满足)

    public static final String MAS_TRANSFER_FEE_BARCODE = "000000000020155050";
    public static final String MAS_TRANSFER_FEE_SKUCODE = "97021193";

    public static final String VMI_LOG_MEMO_SEND = "发送成功";
    public static final String VMI_LOG_MEMO_OUT = "出库成功";
    public static final String VMI_LOG_MEMO_CONFIRM_SUCCESS = "订单确认成功";
    public static final String VMI_LOG_MEMO_CONFIRM_FAULT = "订单确认失败";
    public static final String VMI_LOG_MEMO_DEAl_SUCCESS = "订单取消处理完成";
    public static final String VMI_LOG_MEMO_NO_CALE = "订单取消未发送";
    public static final String VMI_LOG_MEMO_DEAl_UNEXCEIT = "订单不存在";
    public static final Integer T_CARRIER_CONSIGNMENT_STATUS_SUCCESS = 10; // 订单在途处理成功
    // 平台类型
    public static final String PLTF_TAOBAO = "1";
    public static final Integer T_CARRIER_CONSIGNMENT_STATUS_FAILE = 5; // 订单在途处理失败
    public static final String T_CARRIER_CONSIGNMENT_STATUS_MEMO = "订单处理失败,当前订单状态非已确认";
    public static final Integer TB_DELIVERY_STATUS_NEW = 1; // 未发货

    /**
     * 查询时间跨度15天
     */
    public static final Integer TIME_RANGE_LIMIT_15 = -15;
    
    public static final String TRANSPORTATOR_EXP_CODE_OTHER = "OTHER";

    // FTP
    public static final String FTP_TYPE_UPLOAD = "upload";
    public static final String FTP_TYPE_DOWNLOAD = "download";
    public static final String FTP_STATUS_SUCCESS = "success";
    public static final String FTP_STATUS_FAILED = "failed";
    public static final String FTP_STATUS_BLOCK = "block";

    public static final String ENCODING_GBK = "GBK";
    public static final Integer FTP_SERVER_TIMEOUT = 12000;

    // CONVERSE-FTP
    public static final String CONFIG_GROUP_CONVERSEFTP = "CONVERSEFTP";
    public static final String CONVERSE_FTP_URL = "url";
    public static final String CONVERSE_FTP_PORT = "port";
    public static final String CONVERSE_FTP_USERNAME = "username";
    public static final String CONVERSE_FTP_PASSWORD = "password";
    public static final String CONVERSE_FTP_DOWNPATH = "remotedownPath";
    public static final String CONVERSE_FTP_DOWN_LOCALPATH = "localDownPath";
    public static final String CONVERSE_FTP_STYLE = "style";
    public static final String CONVERSE_FTP_FINISH_DIR = "BAK";
    public static final String CONVERSE_FTP_PRICE = "price";
    public static final String CONVERSE_FTP_EAN = "EAN";
    public static final String CONVERSE_FTP_PRODUCTINFO = "productinfo";
    public static final String CONVERSE_FTP_LISTPRCE = "DUPSTYLE";

    public static final String CONVERSE_SALES_DIR = "sales";
    public static final String CONVERSE_ERP_DATA_GROUP = "CONVERSERP";

    // 尊宝网接口使用常量
    public static final String SHOPEX_TYPE_CREATE_OUTER_ORDERS = "【创建订单_SHOPEX】";
    public static final String SHOPEX_TYPE_GET_SKU_INVENTORY = "【获取商品库存_SHOPEX】";
    public static final String SHOPEX_LOG_SUCCESS = "成功";
    // TODO 因为尊宝网两个网站在运行 及时同步库存的时候两个都有 所以及时库存信息用此次在新尊宝网上用另外一个编号
    public static final Long SO_JUMBOMART_SHOP_ID = 6220L;
    public static final Long SO_JUMBOMART_SHOP_ID_ = 622L;
    public static final String SHOPEX_TYPE_GET_SKU_LIST_PRICE = "【获取商品售价_SHOPEX】";
    public static final String SHOPEX_TYPE_EXECUTE_OUTER_ORDERS_TO_FINISH = "【订单状态变更为完成_SHOPEX】";

    // 订单创建时行金额计算方式
    public static final Integer NEEDED_ASSIGN = 1;// 基于行sum(line.unitPrice*qty)-so.totalActual及订单头上积分进行[行金额]计算
                                                  // 　　　assignPromotionfee
    public static final Integer NEEDED_CALCULATE_FEE = 0;// 基于平台折后行总计(扣除行优惠及整单优惠)及平摊到行的积分进行[行金额]计算　　calculateTaxFee
    public static final Integer NOT_NEEDED_ASSIGN = -1;// 不参与行金额计算
    public static final Integer SO_ASSIGN_BY_PLATFORMTOTALACTUAL = 2;// 基于sum(平台行总计)-so.totalActual及订单头上totalPointUsed进行平摊进行[行金额]计算
    public static final Integer SO_ASSIGN_BY_VC = 3;// 基于行sum(line.unitPrice*qty)-so.totalActual及订单头上totalVc进行[行金额]计算
                                                    // 　assignByVC　　

    public static final String COUNTRY = "中国";
    public static final String COUNTRY_EN = "China";
    public static final String PROVINCE_HK = "香港";
    public static final String PROVINCE_MO = "澳门";
    public static final String PROVINCE_TW = "台湾";
    public static final String APPLE_WAREHOUSE_CODE = "00000021";
    public static final String APPLE_WAREHOUSE_CODE_OFFICIAL = "apple-0001";

    public static final BigDecimal defaultTaxRate = new BigDecimal(0.17);// 商品默认税率

    public static Integer TRANS_SERVICE_TYPE_AIR = new Integer(4);
    public static Integer TRANS_SERVICE_TYPE_LAND = new Integer(6);
    public static Integer TRANS_SERVICE_TYPE_SPECIALS = new Integer(7);

    public static Integer TRANS_TIME_TYPE_DAY = new Integer(5);
    public static Integer TRANS_TIME_TYPE_NEXT_DAY = new Integer(6);

    public static final String INNERSHOPCODE_WITH_CONVERSE = "1CONVERSE官方旗舰店";
    public static final String INNERSHOPCODE_WITH_CONVERSE_CHILD = "1converse童鞋旗舰店";

    public static final Long CCH_SHOP_ID = 2542L;// Cache-cache官方商城店铺ID

    // I.T
    public static final String IT_VENDER = "i.t";
    // VMI
    // ----------------Mas ----start--------------
    public static final String MASFTP_CONFIG_GROUP = "MASFTP";
    public static final String MAS_FTP_URL = "url";
    public static final String MAS_FTP_PORT = "port";
    public static final String MAS_FTP_USERNAME = "username";
    public static final String MAS_FTP_PASSWORD = "password";
    public static final String MAS_FTP_LOCALPATH = "localPath";
    public static final String MAS_FTP_REMOTE = "remotePath";
    public static final String MAS_FTP_IN = "In";
    public static final String MAS_FTP_OUT = "Out";
    public static final String MAS_FTP_BAK = "BAK";
    public static final String MAS_VMI_CODE = "MAS";
    // ----------------Mas ----end--------------
    public static final String CONFIG_GROUP_NIKE_FTP = "NIKEFTP";
    public static final String CONFIG_GROUP_GUESSFTP = "GUESSFTP";
    public static final String CONFIG_GROUP_IDSFTP = "IDSFTP";
    public static final String CONFIG_GROUP_VMIFTP = "VMIFTP";
    public static final String VMI_FTP_REMOTE_BAK_PATH = "remotedownBakPath";
    public static final String VMI_FTP_URL = "url";
    public static final String VMI_FTP_PORT = "port";
    public static final String VMI_FTP_USERNAME = "username";
    public static final String VMI_FTP_PASSWORD = "password";
    public static final String VMI_FTP_DOWNPATH = "remotedownPath";
    public static final String VMI_FTP_DOWN_LOCALPATH = "localDownPath";
    public static final String VMI_FTP_UP_LOCALPATH = "localUpPath";
    public static final String VMI_FTP_UPPATH = "remoteUPPath";
    public static final String VMI_FTP_TIMEOUT = "timeout";
    public static final String IT_FTP_FINISH_DIR = "BAK";
    // ----------------Coach ------------------
    public static final String COACH_VMI_CODE = "coach";

    public static final String CONVERSE_LOCATION_CODE = "IDS";
    public static final String CONVERSE_SHOP_VMICODE = "1290";

    public static final String IT_VMI_CODE_TB = "EITCN";
    public static final String IT_VMI_CODE_BS = "EIXCN";
    public static final String ESPRIT_TMALL = "4046655009032";
    public static final String ESPRIT_CN = "4046655009049";
    public static final String ESP_VIRTUAL_SHOP_VMICODE = "4046655000664";
    // 正式
    public static final Long IT_BS_SHOP_ID = 1863L; // IT官方商城店铺ID

    public static final Map<String, Long> BS_VMI_CODE_REF_SHOP_ID;
    static {
        BS_VMI_CODE_REF_SHOP_ID = new HashMap<String, Long>();
        BS_VMI_CODE_REF_SHOP_ID.put(IT_VMI_CODE_TB, IT_BS_SHOP_ID);
        BS_VMI_CODE_REF_SHOP_ID.put(IT_VMI_CODE_BS, IT_BS_SHOP_ID);
        BS_VMI_CODE_REF_SHOP_ID.put(ESPRIT_TMALL, ESP_BS_SHOP_ID);
        BS_VMI_CODE_REF_SHOP_ID.put(ESPRIT_CN, ESP_BS_SHOP_ID);
        BS_VMI_CODE_REF_SHOP_ID.put(ESP_VIRTUAL_SHOP_VMICODE, ESP_BS_SHOP_ID);
    }

    public static final String MICROSOFT_BS_SO_PLATFORM_CODE_PREFIX = "MSFT-";
    // NIKE 发票导出类型CHOOSEOPTION
    public static final String CHOOSEOPTION_CATEGORY_CODE_SOINVOICETAXNIKE_TYPE = "soinvoicetaxniketype";
    // WMS销售小票接口切换时间
    public static String COASH_BS_CS2000_INTERFACE_EXCHANGE_TIME = "2014-05-13 17:00:00";
    // COACH 销售小票上销售单起始号码
    public static final String COACH_START_INDEX_SONUMBER = "01000001";

    // 尊宝网发货同步队列
    public static final String SYNCED_NEW = "1";

    public static final String SYNCED_WAIT = "2";

    public static final String SYNCED_FAILED = "3";

    public static final String SYNCED_FINISH = "5";

    public static final String SHOPEX_TYPE_SEND_ORDER_TO_DELIVERYS_INFO = "【订单状态变更为在途_SHOPEX】";

    public static final String IT_SS = "01";
    public static final String IT_RF = "21";
    public static final String IT_EX = "31";

    // VMI模板
    public static final String PRODUCT_TEMPLATE_COLOR_SIZE_CODE = "TPL_APPAREL_COMMON_2";// 颜色/尺寸
    public static final String PRODUCT_TEMPLATE_SIZE_CODE = "TPL_APPAREL_COMMON_1";// 尺寸
    public static final String PRODUCT_TEMPLATE_COLOR_CODE = "TPL_APPAREL_COMMON_3";// 颜色
    public static final String PRODUCT_TEMPLATE_NO_PROPERTY_CODE = "OTHERS";// 无属性(一些不需要规格、型号、特点等信息的商品可维护在里面，一般代销商品使用该模版)

    public static final String INV_STATUS_GOOD = "良品";//
    // customerCode
    public static final String UserGroupCustomer = "CUS_BAOZUN";// 客户宝尊
    public static final String PhilipsBsCustomer = "CUS_PHILIPS";// 客户飞利浦
    public static final String QiaoZhiCustomer = "CUS_QIAOZHI";// 客户俏治

    public static final int WAREHOUSE_QUEUE_SIZE = 200;

    public static final String PRODUC_TSTATUS = "productStatus"; // 商品状态

    // 微软报表配置
    public static final String MICROSOFTTEST_FTP_GROUP = "MICROSOFTTEST";
    public static final String MICROSOFTTEST_FTP_URL = "url";
    public static final String MICROSOFTTEST_FTP_PORT = "port";
    public static final String MICROSOFTTEST_FTP_USERNAME = "username";
    public static final String MICROSOFTTEST_FTP_PASSWORD = "password";
    public static final String MICROSOFTTEST_FTP_UPPATH = "uploadPath";
    public static final String MICROSOFTTEST_FTP_LOCAL_UPPATH = "localUpPath";
    public static final String MICROSOFTTEST_FTP_UPPATH_BACKUP = "localUpPathBackup";

    // apple学生营销活动支持常量
    public static Integer SPECIAL_TYPE_NORMAL = new Integer(0);
    public static Integer SPECIAL_TYPE_APPLE_STUDENT_CAMP = new Integer(1);

    // levis
    public static final String LEVIS_SALES_FILE = "LSCN_PRSALETRN_";
    public static final String LEVIS_DISCOUNT_FILE = "LSCN_PRDISCTRN_";
    // cas返回消息定义
    public static final String CAS_REGISTER_SUCCESS = "11";// 用户注册成功（注册请求返回）
    public static final String CAS_REGISTER_ERROR = "12";// 找到注册过的用户，且工号匹配（注册请求返回）
    public static final String CAS_REGISTER_ERROR1 = "13";// 找到注册过的用户，但工号不匹配（注册请求返回）
    public static final String CAS_PWDMODFIFY_SUCCESS = "21";// 更新密码成功（更新密码请求返回）
    public static final String CAS_PWDMODFIFY_ERROR = "22";// 更新密码失败，找不到用户（更新密码请求返回）
    public static final String CAS_PWDMODFIFY_ERROR1 = "23";// 更新密码失败，原密码错误（更新密码请求返回）
    public static final String CAS_SUPERMODIFY_SUCCESS = "41";// 超级用户更新密码成功（超级用户更新密码请求返回）
    public static final String CAS_SUPERMODIFY_ERROR = "42";// 超级用户更新密码失败，找不到用户（超级用户更新密码请求返回）
    public static final String CAS_SUPERMODIFY_ERROR1 = "43";// 超级用户更新密码失败，超级用户密码错误（超级用户更新密码请求返回）

    public static final String CHOOSE_OPTION_SKUONSALETYPE = "skuOnSaleType";

    public static final String CATEGORYCODE = "shopRunType";

    public static final String SALESMODEL = "salesModel";
    
    public static final String AUTO = "AUTO";			// 自动拆单
    public static final String MANUAL = "MANUAL";		// 手动拆单
    public static final String SHIPPING = "SHIPPING";	// wms拆单
    public static final String AGAUTO = "AGAUTO";			// AG自动拆单

    public static final String CHOOSE_OPTION_PLATFORMSLPACKINGINFO_TYPE = "packType";



    public static final int SO_SUSPEND_NODE = 5;
    
    // 精确到毫秒，这里设置30分钟
    // 毫秒 * 秒 * 分钟
    public static final long TASK_RUN_TIMEOUT = 1000 * 60 * 30;
    public static final String TASK_RUN_VALUE = "";
    //直连WMS时，erp订单号是没有的
    public static final String ERP_ORDER_CODE_FOR_WMS = "直连WMS订单，无ERP订单号";
    
    /** nike官方旗舰店的ID: 正式：2042 */
    public static final Long NIKE_SHOP_ID = 2042L;
    
    /**自动发货订单 */
    public static final String PLATFORM_TRADE_TYPE_AUTO_DELIVERY = "auto_delivery";
    
}
