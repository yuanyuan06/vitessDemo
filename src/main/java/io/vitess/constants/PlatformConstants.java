package io.vitess.constants;

/**
 * @author YSH4807
 * @date 2018/4/11 10:57
 */
public class PlatformConstants {

    /** config file name **/
//	public static ResourceBundle JUMBO_CONFIG_FILE = ResourceBundle.getBundle("config/work_jumbo_config");

//	public static ResourceBundle JUMBO_NIKE_CONFIG_FILE = ResourceBundle.getBundle("config/work_jumbonike_config");

    /** taobao interfase */
//	public static String TAOBAO_ROUTER_URL = String.valueOf(JUMBO_CONFIG_FILE.getString("taobaoRouterUrl"));
    /** tb temp trade status 新建 1 */
    public static final Integer SO_TB_TRADE_STATUS_NEW = 1; // 新建
    /** tb temp trade status 等待转换 2 */
    public static final Integer SO_TB_TRADE_STATUS_WAITING = 2; // 等待转换

    /** 无需创建 5 */
    public static final Integer SO_TB_TRADE_STATUS_CREATE_ERROR = 5; // 无需创建
    /** 完成 */
    public static final Integer SO_TB_TRADE_STATUS_FINISHED = 10; // 完成

    public static final String SO_TB_TRADE_TYPE_TMALL = "Tmall"; // Tmall
    public static final String SO_TB_TRADE_TYPE_ACCOUNT = "Account"; // 对账信息
    public static final String SO_TB_TRADE_TYPE_PT = "PT"; // 百事官方商城
    public static final String SO_TB_TRADE_TYPE_MQ_TMALL = "MQTmall"; // MQ
    // Tmall
    public static final String SO_TB_TRADE_TYPE_MQ_FENXIAO = "MQFenxiao"; // MQ

    public static final String SO_TB_TRADE_TYPE_MQ_DEALER = "MQDealer"; // MQ
    // Tmall
    public static final String SO_TB_TRADE_TYPE_MQ_PAIPAI = "MQPaipai"; // MQ
    // Tmall
    public static final String SO_TB_TRADE_TYPE_MQ_YHD = "MQYhd"; // MQ Tmall

    /** 淘宝订单类型:一口价 */
    public static final String SO_TYPE_TB_FIXED = "fixed";
    /** 淘宝订单类型:分销 */
    public static final String SO_TYPE_TB_FENXIAO = "fenxiao";
    /** 淘宝订单类型:万人团 */
    public static final String SO_TYPE_TB_STEP = "step";
    /** 淘宝订单类型:自动发货 */
    public static final String SO_TYPE_TB_AUTO_DELIVERY = "auto_delivery";
    /** 淘宝订单类型:一口价、拍卖 */
    public static final String SO_TYPE_TB_GUARANTEE_TRADE = "guarantee_trade";

    // so status
    public static final Integer SALES_ORDER_STATUS_SUBMITTED = 1; // 已提交
    public static final Integer SALES_ORDER_STATUS_SUBMITTED_NOT_APPROVE = 2; // 新建
    // 未审核
    public static final Integer SALES_ORDER_STATUS_APPROVE_NOT_PASSED = 3; // 审核未通过
    public static final Integer SALES_ORDER_STATUS_CONFIRMED = 4; // 已确认
    public static final Integer SALES_ORDER_STATUS_WH_HANDLING = 5; // 库房准备中
    public static final Integer SALES_ORDER_STATUS_DELIVERIED = 6; // 在途
    public static final Integer SALES_ORDER_STATUS_CANCELED = 9; // 会员取消
    public static final Integer SALES_ORDER_STATUS_SYS_CANCELED = 10;
    public static final Integer SALES_ORDER_STATUS_FINISHED = 15; // 交易完成//
    // 尊宝网取消
    public static final Integer SALES_ORDER_STATUS_RETURNED = 17; // 会员拒收
    public static final Integer SALES_ORDER_STATUS_HANDLING = 99; // 处理中
    public static final Integer SALES_ORDER_STATUS_CLOSED = 12; // 交易关闭(退换货)

    public static final String TB_TRADE_STATUS_BUYER_PAY = "TradeBuyerPay";
    public static final String TB_TRADE_STATUS_SUCCESS = "TradeSuccess";
    public static final String TB_TRADE_STATUS_CLOSE = "TradeClose";
    /** 已发货 */
    public static final String TB_TRADE_STATUS_SHIP = "TradeSellerShip";
    public static final String TB_TRADE_STATUS_END = "DS_DEAL_END_NORMAL";

    // so finance status
    public static final Integer SALES_ORDER_FISTATUS_NO_PAYMENT = 1; // 未收款

    public static final Integer SALES_ORDER_FISTATUS_PART_PAYMENT = 2; // 部分收款

    public static final Integer SALES_ORDER_FISTATUS_FULL_PAYMENT = 3; // 已全额收款

    // so invoice status
    public static final Integer SALES_ORDER_INSTATUS_NO_MAKE = 0; // 未开票

    public static final Integer SALES_ORDER_INSTATUS_PART_MAKE = 1; // 部分开票

    public static final Integer SALES_ORDER_INSTATUS_FULL_MAKE = 2; // 已开票

    public static final Integer SALES_ORDER_INSTATUS_WILL_NOT_MAKE = 99; // 不开票

    public static final String TAOBAO_PLATFORM = "1"; // 淘宝商城
    public static final String RAKUTEN_PLATFORM = "2";// 百度乐酷天
    public static final String PAIPAI_PLATFORM = "3";// 拍拍商城
    public static final String JD_PLATFORM = "4";// 京东商城
    public static final String STORE_PLATFORM = "5";// 官方商城
    public static final String YHD_PLATFORM = "6";// 一号店商城
    public static final String ZS_PLATFORM = "7";// 招商信用卡商城
    public static final String TMALL_PLATFORM = "8";//天猫商城
    // taobao trade info
    public static final String TRADE_TYPE_PAYMENT = "交易付款";
    public static final String TRADE_TYPE_TRANSFER = "转账";

    public static final String TRADE_TYPE_TRANSFER_COMMISION = "天猫佣金";
    public static final String TRADE_TYPE_RETURN_TRANSFER_COMMISION = "退佣金";

    public static final String TRADE_TYPE_TRANSFER_INTEGRAL = "返点积分";

    public static final String APP_KEY = "21325305";
    public static final String APP_SECRET = "44ddf917458123851b94a0b272ce2654";

    //经销
    public static final String DEALER_WAIT_FOR_SUPPLIER_AUDIT1 = "WAIT_FOR_SUPPLIER_AUDIT1"; //分销商提交申请，待供应商审核
    public static final String DEALER_SUPPLIER_REFUSE = "SUPPLIER_REFUSE"; //供应商驳回申请，待分销商确认
    public static final String DEALER_WAIT_FOR_APPLIER_AUDIT = "WAIT_FOR_APPLIER_AUDIT"; //供应商修改后，待分销商确认
    public static final String DEALER_WAIT_FOR_SUPPLIER_AUDIT2 = "WAIT_FOR_SUPPLIER_AUDIT2"; //分销商拒绝修改，待供应商再审核
    public static final String DEALER_BOTH_AGREE_WAIT_PAY = "BOTH_AGREE_WAIT_PAY"; //审核通过下单成功，待分销商付款
    public static final String DEALER_TRADE_CLOSED = "TRADE_CLOSED"; //经销采购单关闭

    // fenxiao
    public static final String FENXIAO_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

    public static final String FENXIAO_TRADE_CLOSED = "TRADE_CLOSED";

    public static final String TB_USER_NAME = "TAOBAO_JM";

    public static final String TB_SKU_GET_ERROR = "获取SKU接口无法连接，更新库存失败！";

    public static final String TB_OUTER_SKU_ID_NOT_EXISTS = "外部商家编码不存在，更新库存失败！";

    public static final String TB_INVENTORY_UPDATE_ERROR = "库存接口无法连接，更新库存失败！";

    public static final String TB_ITEM_DELISTING_SUCCES = "ITEM下架成功";

    public static final String TB_ITEM_DELISTING_ERROR = "ITEM下架失败，ITEM不存在或者淘宝连接失败！";

    public static final String TB_ORDER_CODE_LENGTH_ERROR = "淘宝编码长度错误";

    public static final String TB_PRODUCT_GET_ERROR = "获取产品接口无法连接，更新库存失败！";

    public static final String TB_NO_NEED_UPDATE_INV = "无需更新SKU库存";

    public static final String TB_WULIUBAO_INV_QUERY_ERROR = "查询物流宝库存接口调用失败";

    public static final String TB_WULIUBAO_INV_UPDATE_ERROR = "更新物流宝库存接口调用失败";

    // 发货错误信息
    public static final String TB_DELIVERY_CODE_ERROR = "订单编号错误，请检查订单编号";

    public static final String TB_DELIVERY_INVOKE_API_ERROR = "请求api失败";

    public static final String TB_DELIVERY_ORDER_STATUS_ERROR = "订单的物流状态不对，请求发货失败";

    /**
     * 淘宝付款经销
     */
    public static final String SHOP_TYPE_TB_01 = "TB01"; // 付款经销

    /** 淘宝代销+不发货 */
    public static final String SHOP_TYPE_TB_02 = "TB02"; // 淘宝代销+不发货

    /**淘宝代销+发货 */
    public static final String SHOP_TYPE_TB_03 = "TB03"; // 淘宝代销+发货
    /** 结算经销*/
    public static final String SHOP_TYPE_TB_04 = "TB04"; // 结算经销

    // consignee+delivery
    public static final Integer SHOP_STATUS_OPENING = 1; // 开店中

    public static final Integer SHOP_STATUS_CLOSED = 10; // 已关店***
    /** 完全全量  -- nike*/
    public static final Integer SHOP_SETTLEMENT_MODE_FINISHED_ALL = 1;
    /** 完全增量*/
    public static final Integer SHOP_SETTLEMENT_MODE_FINISHED_PARTLY = 3;
    /**在途全量*/
    public static final Integer SHOP_SETTLEMENT_MODE_DELIVERY_ALL = 5;
    /**在途增量*/
    public static final Integer SHOP_SETTLEMENT_DELIVERY_PARTLY = 7;

    /**
     * 平台同步状态
     */
    public static final Integer PLATFORM_SYNC_STATUS_NEW = 0;
    public static final Integer PLATFORM_SYNC_STATUS_DOING = 2;
    public static final Integer PLATFORM_SYNC_STATUS_END = 5;

    // tb
    public static final String WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS"; // 等待买家发货

    public static final String WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS"; // 卖家已发货

    public static final String TRADE_CLOSED_BY_TAOBAO = "TRADE_CLOSED_BY_TAOBAO"; // 卖家已关闭
    /** 卖家已发货 */
    public static final String TRADE_CLOSED_FINISHED = "TRADE_CLOSED";
    /** 交易成功 */
    public static final String TRADE_FINISHED = "TRADE_FINISHED";
    /** 库存出入库类型更新 */
    public static final Integer INVENTORY_QUEUE_METHOD_INCREMENT_TYPE = 0;
    /** 覆盖更新 */
    public static final Integer INVENTORY_QUEUE_METHOD_OVER_TYPE = 10;
    /** 发货成功 */
    public static final Integer TB_DELIVERY_LOG_STATUS_SUCCESS = 5;
    /** 7:已发货 */
    public static final Integer TB_RAKUTEN_SHIPMENTSTATUS_IN_HAND = 7;
    public static final String SO_RAKUTEN_PROCESS_STATUS = "S00001";
    public static final Integer TB_DELIVERY_STATUS_DELIVERED = 5; // 已发货成功
    // tb so delivery log status
    public static final Integer TB_DELIVERY_LOG_STATUS_FAILURE = 0; // **发货失败
    public static final Integer TB_DELIVERY_LOG_STATUS_FAILURE_NOT_REPEAT = 2; // **发货失败不再发货

    public static final String INVENTORY_UPDATE_METHOD_INCREMENT_ERROR = "【库存出入库类型更新】";
    public static final String INVENTORY_UPDATE_METHOD_OVER_ERROR = "【覆盖更新】";

    public static final Integer INVENTORY_QUEUE_ADD_TYPE = 1; // 库存入库类型跟新
    public static final Integer INVENTORY_QUEUE_SUBTRACT_TYPE = 2;// 库存出库类型跟新
    public static final Integer INVENTORY_QUEUE_OVER_TYPE = 10; // 覆盖跟新
    public static final Integer INVENTORY_QUEUE_NO_TYPE = 99; // 不跟新

    public static final String EXCEL_TPLT_INVENTORY_TO_TB = "tplt_update_inventory_to_tb";

    public static final String EXCEL_TPLT_INVENTORY_RESULT = "tplt_update_inventory_result";
    // 所有商品库存数为0
    public static final String FORBIDDEN_QUANTITY_ZERO_ERROR = "isv.item-update-service-error:FORBIDDEN_QUANTITY_ZERO_ERROR";
    public static final String FORBIDDEN_QUANTITY_ZERO_ERROR_TMALL = "isv.item-update-service-error:FORBIDDEN_QUANTITY_ZERO_ERROR-tmall";
    public static final String TB_DELIVERY_ERROR_0 = "发货接口无法连接，发货失败！";

    public static final String TB_DELIVERY_ERROR_1 = "物流订单不存在";

    public static final String TB_DELIVERY_ERROR_2 = "订单状态不正确";

    public static final String TB_DELIVERY_ERROR_3 = "其他原因，需要手动发货！";

    public static final String TB_DELIVERY_ERROR_4 = "其他原因，系统会再次执行发货！";
    // 发货
    // 物流订单不存在
    public static final String DELIVERY_ERROR_B01 = "isv.logistics-delivery-service-error:B01";

    // 订单状态不正确

    public static final String DELIVERY_ERROR_B04 = "isv.logistics-delivery-service-error:B04";

    // 创建task
    public static final String SALES_ORDER_TASK = "SO";

    public static final String COUNTRY_CN = "中国";

    public static final String SHOP_TOP_GNC = "gnc健安喜宝尊专卖店";
}
