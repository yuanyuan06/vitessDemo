package io.vitess.constants;

public abstract class TopRdsConstants {
	//top api 调用地址
	public static final String TOP_URL = "http://gw.api.taobao.com/router/rest";
	//淘宝沙箱环境
//	public static final String TOP_URL = "http://gw.api.tbsandbox.com/router/rest";
	//测试环境 通过塔内转发 不可轻易使用
//	public static final String TOP_URL = "http://120.26.192.245:30006/router/rest";
	
	//top api by https调用地址
	public static final String TOP_URL_HTTPS = "https://eco.taobao.com/router/rest";
	//public static final String TOP_URL_HTTPS = "http://121.41.160.237:30003/router/rest";
	
	//淘宝退款
	public static final String TAOBAO_TB_REFUND_GET = "taobao.tb.refund.get";
	//淘宝分销退款
	public static final String TAOBAO_FX_REFUND_GET = "taobao.fx.refund.get";
	//根据外部ID取商品SKU
    public static final String TAOBAO_SKUS_CUSTOM_GET = "taobao.skus.custom.get";
    //根据外部ID取商品
    public static final String TAOBAO_ITEMS_CUSTOM_GET = "taobao.items.custom.get";
    //宝贝/SKU库存修改
    public static final String TAOBAO_ITEM_QUANTITY_UPDATE = "taobao.item.quantity.update";
	//淘宝订单
	public static final String TAOBAO_INCREMENT_TRADES_GET = "taobao.increment.trades.get";
	//分销订单
	public static final String TAOBAO_FENXIAO_ORDERS_GET = "taobao.fenxiao.orders.get";
	//经销订单
	public static final String TAOBAO_FENXIAO_DEALER_REQUISITIONORDER_GET = "taobao.fenxiao.dealer.requisitionorder.get";
	//ERP开票请求接口
	public static final String TAOBAO_EINVOICE_CREATEREQ = "alibaba.einvoice.createreq";
	//电子发票回流天猫
	public static final String TAOBAO_ELECTRONIC_INVOICE_DETAIL_UPLOAD = "alibaba.electronic.invoice.detail.upload";
	//订单全链路
    public final static String TAOBAO_PRODUCE_TOPIC="taobao_jds_TradeTrace";
	
  //apple发送未签收退货退款数据  by sunshanshan
  	public static final String APPLE_NO_SIGN_REPORT = "apple.no.sign.report";
    //创建rds round分页同步订单每页订单数
	public static final Integer PAGE_SIZE = 50;
	
	/** 七天无理由退换货 */
	public static final String REFUND_REASON_SEVEN_DAY = "7天无理由退换货";

	/**
	 * 淘宝接口调用状态
	 */
	public static final int API_CALL_SUCCESS = 1;//接口调用成功
	public static final int API_CALL_FAIL = -1;//接口调用失败
	public static final int API_NOT_CALL_YET = 0;//没有调用（
	public static final int API_CALLING = 2;//接口调用中
	public static final int API_INVALID_CALL = -2;//调用失败（无效调用）
	public static final int API_CALL_UNKOWN_EXCEPTION = -3;
	public static final int API_CALL_ERROR_NEED_MANUAL = -10;

	public static final String API_CALL_UNKNO_ERROR_CODE = "-99";
	
	public static final String TBTRADE = "tbTrade";//rds round类型（交易订单）
	
	public final static String TRADE_DESC_RDS = "RDS推送";
	
	public final static int SYNC_STATUS_NOT_YET = 0;//未发送
	public final static int SYNC_STATUS_SUCCESS = 1;//已发送(得到反馈，成功)
	public final static int SYNC_STATUS_NOT_NEED = 2;//不需要发送
	public final static int SYNC_STATUS_AWAIT = 3;//已发送(等待反馈)[淘宝项目中为发送加载中]
	
	public static final String JZ_LOGTICTICS_ERROR_MESSQGE_INS_MSG="物流服务商实体服务类型为带安装类型,而安装服务商实体为空";
	public static final String JZ_LOGTICTICS_ERROR_MESSQGE_TMS_CODE="40";
	
    /*****  淘宝到tmall-oms数据处理状态  *****/
	public static final int PROCESS_STATUS_NO = 0;//未处理
	public static final int PROCESS_STATUS_COMPLET = 1;//已处理
	public static final int PROCESS_STATUS_ERROR = -1;//处理失败
	
	/** 淘宝订单状态 **/
	public static final String SO_WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";  	  //等待卖家发货,即:买家已付款
	public static final String SO_WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";  //等待买家确认收货,即:卖家已发货
	public static final String SO_CONSIGNED_PART = "SELLER_CONSIGNED_PART";  			  //卖家部分发货
	
	public static final String SO_TRADE_FINISHED = "TRADE_FINISHED";  // 交易成功
	public static final String SO_TRADE_CLOSED = "TRADE_CLOSED";	  // 交易关闭
	
	/** 淘宝退款状态 **/
	public static final String RF_SUCCESS = "SUCCESS"; //退款完成
	
    //只做数据分析的店铺
	public static final Long[] DA_SHOP_ARRAY = {2082L,481L,2762L,2903L,4463L,4742L,5242L,6939L,7186L,7187L};
	
	//奇门沙箱地址
//	public static final String QIMEN_URL = "http://qimenapi.tbsandbox.com/router/qimen/service";
	//奇门正式环境地址
	public static final String QIMEN_URL = "http://qimen.api.taobao.com/router/qimen/service";
	//奇门本地环境测试地址 通过塔内转发 不可轻易使用
//	public static final String QIMEN_URL = "http://120.26.192.245:30003/router/qimen/service";
	
	//method 
	public static final String QIMEN_STOREINVENTORY_ITEMQUERY = "taobao.qimen.storeinventory.itemquery";
	//method
	public static final String QIMEN_STOREINVENTORY_ITEMINITIAL = "taobao.qimen.storeinventory.iteminitial";
	//method
	public static final String QIMEN_STOREINVENTORY_ITEMUPDATE = "taobao.qimen.storeinventory.itemupdate";
}
