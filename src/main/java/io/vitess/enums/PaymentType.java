package io.vitess.enums;

import java.util.HashMap;

/**
 * 支付方式
 * @author 李光辉
 * @date 2014年7月7日 下午2:56:29
 * @since
 */
public enum PaymentType {
    /** 1.货到付款 */
    CASH_ON_DELIVERY(1, "货到付款"),
    /** 2.银行电汇 */
    BANK_WIRE(2, "银行电汇"),
    /** 3.网银在线 */
    CHINA_BANK_ONLINE(3, "网银在线"),
    /** 6.支付宝 */
    ALIPAY(6, "支付宝"),
    /** 7.快钱 */
    KUAIQIAN(7, "快钱"),
    /** 9.现金收款 */
    CASH(9, "现金收款"),
    /** 13.招行直连 */
    CMB_DIRECT(13, "招行直连"),
    /** 18.支付宝快捷支付 */
    ALIPAY_QUICK_PAYMENT(18, "支付宝快捷支付"),
    /** 19.支付宝快捷支付分期付款 */
    ALIPAY_QP_INSTALLMENT(19, "支付宝快捷支付分期付款"),
    /** 20.零元购 */
    LING_YUN_GOU(20, "零元购"),
    /** 104.新华一城卡 */
    XIN_HUA_YI_CHENG_KA(104, "新华一城卡"),
    /** 108.LEVIS淘宝B2C－支付宝 */
    LEVIS_TAOBAO_B2C(108, "LEVIS淘宝B2C－支付宝"),
    /** 200.百付宝(汇付天下) */
    BAIDU_PAY(200, "百付宝(汇付天下)"),
    /** 300.财付通 */
    TEN_PAY(300, "财付通"),
    /** 310.中国移动在线支付(手机支付) */
    CHINA_MOBILE_ONLINE(310, "中国移动在线支付(手机支付)"),
    /** 320.银联在线 */
    UNIONPAY_ONLINE(320, "银联在线"),
    /** 330.国际信用卡支付 */
    INTERNATIONAL_CREDIT_CARD(330, "国际信用卡支付"),
    /** 340.浦发直连 */
    SPD_BANK_DIRECT(340, "浦发直连"),
    /** 400.账户支付 */
    ACCOUNT_PAYMENT(400, "账户支付"),
    /** 401.网上支付 */
    ONLINE_PAYMENT(401, "网上支付"),
    /** 403.邮局汇款 */
    CHINA_POST_REMITTANCE(403, "邮局汇款"),
    /** 404.银行转账 */
    BANK_TRANSFER(404, "银行转账"),
    /** 405.POS机 */
    POS(405, "POS机"),
    /** 406.万里通 */
    WAN_LI_TONG(406, "万里通"),
    /** 407.分期付款 */
    INSTALLMENT(407, "分期付款"),
    /** 408.合同账期 */
    CONTRACT_PAYMENT_TERM(408, "合同账期"),
    /** 409.货到转账 */
    ARRIVAL_TRANSFER(409, "货到转账"),
    /** 410.货到付支票 */
    ARRIVAL_CHECK(410, "货到付支票"),
    /** 601.支付宝手机支付 */
    ALIPAY_MOBILE_PAYMENT(601, "支付宝手机支付"),
    /** 610.建行直连 */
    CCB_DIRECT(610, "建行直连"),
    /** 611.建行分期3期 */
    CCB_3_INSTALLMENT(611, "建行分期3期"),
    /** 612.建行分期6期 */
    CCB_6_INSTALLMENT(612, "建行分期6期"),
    /** 613.建行分期12期 */
    CCB_12_INSTALLMENT(613, "建行分期12期"),
    /** 900.预付卡支付 */
    PREPAID_CARD(900, "预付卡支付"),
    /** 901.积分支付 */
    INTEGRAL_PAYMENT(901, "积分支付"),
    /** 999.0元支付 */
    LING_YUAN_ZHI_FU(999, "0元支付");
    
    /** 用于通过value，获取枚举实例 */
    private static HashMap<Integer, PaymentType> map;
    
    private int value;
    
    private String name;

    private PaymentType(int value, String name) {
        this.value = value;
        this.name = name;
        
        initMap(value, this);
    }

    private static void initMap(int key, PaymentType type) {
        if (map == null) {
            map = new HashMap<Integer, PaymentType>(40);
        }
        map.put(key, type);
    }
    
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static PaymentType valueOf(int value) {
        PaymentType type = map.get(value);
        if (type != null) {
            return type;
        }
        
        throw new IllegalArgumentException();
    }
}
