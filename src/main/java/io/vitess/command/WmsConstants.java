package io.vitess.command;

import java.util.HashMap;
import java.util.Map;


public final class WmsConstants {

	/**
	 * 在容器启动时，会重新赋值
	 * @see com.jumbo.ContextInstantiationListener
	 */
	public static String SYSTEM_KEY = "toms";			// 系统来源标识
	public static String CONTACT_CODE = "TOMS";
	
	public static String EC_WMS = "WAREHOUSE";
	
	public static String STORE = "STORE";

	public static String BZ_NEW_WMS = "BZ_NEW_WMS";
	public static String SHOP_DOG = "SHOPDOG";
	public static String SHOP_DOG_SYSCODE = "BZ_SD";
	public static String SHOP_DOG_WHCODE = "SDPickUp";
	public static String BZ_WMS = "BZ_WMS";
	public static String LF_WMS = "LF_WMS";
	//物流服务斯凯奇新增
	public static String OMS_SKX = "OMS_SKX";
	public static String SGCN_WMS = "SGCN_WMS";

	public static String DATA_SOURCE = "tmall";
	public static final int PROCESS_STATUS_CREATE = 0; //0：新建
	public static final int PROCESS_STATUS_SUCCESS = 10; //处理成功
	public static final int PROCESS_STATUS_FAIL = 5; //处理失败
	
	public static final int WMS_STATUS_SUCCESS = 1;
	// wms4.0 异步的反馈 只会返回成功。。。。用10表示 WMS4.0处理成功
	public static final int WMS_BACK_SUCCESS = 10;
	
	public static final int WMS_BACK_FALES = 0;
	
	public static final int WMS_CONFIRM_BACK_FALES = 88;
	//取消失败
	public static String CANCEL_FALSE_1 = "3005";
	//单据不允许取消
	public static String CANCEL_FALSE_2 = "3007";
	
	public static Map<String, String> invStatusMap = new HashMap<String, String>();
	static {
		invStatusMap.put("normal", "良品");
		invStatusMap.put("damage", "残次品");
		invStatusMap.put("pending", "待处理品");
	}
	public static Map<String, String> invStatusMapNewWms = new HashMap<String, String>();
	static {
		invStatusMapNewWms.put("良品", "normal");
		invStatusMapNewWms.put("残次可销售", "damage");
		invStatusMapNewWms.put("残次不可销售", "damage");
		invStatusMapNewWms.put("待处理", "pending");
		invStatusMapNewWms.put("待报废", "pending");
		invStatusMapNewWms.put("临近保质期", "pending");
		invStatusMapNewWms.put("残次品", "damage");
		invStatusMapNewWms.put("待处理品", "pending");
	}
}
