package io.vitess.constants;

import java.util.HashMap;
import java.util.Map;

public abstract class PacsConstants {
	
    //店铺门店、店铺的特殊商品
    public static final String SHOP_STORE = "SHOP_STORE";
    public static final String SPECIAL_SKU = "SPECIAL_SKU";
    
    public static final String SYS_CODE_STR = "pacs.sys.code.str";

    public static final String SOURCE_SYS_CODE = "pacs.source.sys.code";
    
    public static final String ACCOUNT = "pacs.account";
    
    public static final String PACS_MD5_PASSWORD = "pacs.md5.password";
    
    public static final String PACS_AES_KEY = "pacs.aes.key";
    
    public static final String OMS2PACS_CONFIRM = "oms2pacs.confirm";
    
    public static final String OP_TIME_FORMAT = "yyyyMMddHHmmss";
    
    public static final Map<String,String> receiveLineMap = new HashMap<String, String>();
    static {
        receiveLineMap.put(InterfaceCodeConstants.O1_1,"omsOrderCode");
        receiveLineMap.put(InterfaceCodeConstants.R1_1,"omsRaCode");
        receiveLineMap.put(InterfaceCodeConstants.F1_1,"omsRfCode");
        receiveLineMap.put(InterfaceCodeConstants.M1_1,"pacsShopCode");
        receiveLineMap.put(InterfaceCodeConstants.M1_2,"code");
        receiveLineMap.put(InterfaceCodeConstants.M1_3,"code");
        receiveLineMap.put(InterfaceCodeConstants.I1_1,"shopCode");
        receiveLineMap.put(InterfaceCodeConstants.I2_2,"occupyKey");
        receiveLineMap.put(InterfaceCodeConstants.I10_1,"channelCode");
        receiveLineMap.put(SHOP_STORE,"shopId");
        receiveLineMap.put(SPECIAL_SKU,"shopId");
    }
    
    public static final String SKU_ONSALE = "item-1";
    
    public static final String TAOBAO_TRADE_UPDATE = "tu-1";
    
//    pacs 店铺上的销售模式
    public static final String PAYMENT = "1001";						//1001:付款经销
    public static final String SETTLEMENT = "1002";						//1002:结算经销
    public static final String CONSIGNMENT = "1003";					//1003:代销
    public static final String PAYMENT_OR_SETTLEMENT = "1005";			//1005:付款经销 OR结算经销
    
//    pacs 商品上的销售模式
    public static final String PAYMENT_FOR_SKU = "0";					//付款经销
    public static final String CONSIGNMENT_FOR_SKU = "1"; 				//代销
    public static final String SETTLEMENT_FOR_SKU = "2";				//结算经销
    public static final String SETTLEMENT_OR_CONSIGNMENT_FOR_SKU = "3";	//结算经销 OR 代销
}