package io.vitess.constants;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author YSH4807
 * @date 2018/4/11 11:40
 */
public class AppleConstants {


    private static Properties prop;

    /** 拒绝退款接口的凭证文件 */
    public static byte[] APPLE_LOGO2;

    static {
        ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        Resource resource = resourceLoader.getResource("classpath:apple.properties");
        Resource apple_logo2Resource = resourceLoader.getResource("classpath:file/apple_logo2.jpg");

        try {
            prop = PropertiesLoaderUtils.loadProperties(resource);
            APPLE_LOGO2 = FileCopyUtils.copyToByteArray(apple_logo2Resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String APPLE_PROFILE = prop.getProperty("APPLE_PROFILE");
    /** 测试 */
    public static final String APPLE_PROFILE_TEST = "test";

    /** applestore官方旗舰店的ID 测试店铺-, 正式店铺-4062 */
    public static final Long APPLE_SHOP_ID = Long.parseLong(prop.getProperty("APPLE_SHOP_ID"));

    /* ------------------------- 订单XML ------------------------ */
    /** 测试店铺-060704780001000, 正式店铺-805611720 */
    public static final String XMLTRANSMISSION_RECEIVER = prop.getProperty("XMLTRANSMISSION_RECEIVER");

    public static final String XMLTRANSMISSION_SENDER = "BAOZUN_PRD";

    /* -------------------------        AppleOrder ------------------------ */
    public static final String DATE_FORMAT = "yyyyMMdd";

    public static final String TIME_FORMAT = "HHmmss";

    public static final String DATE_TIME_FORMAT = "yyyyMMdd HHmmss";

    /** 天猫日期格式，例如：2015-06-24 16:25:10 */
    public static final String TB_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String HUB_REPORT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** 9.普通发票 */
    public static final String INVOICE_TYPE_NORMAL = "9";
    public static final String INVOICE_TYPE_NORMAL_NAME = "普通商业零售发票";

    /** 8.增值发票 */
    public static final String INVOICE_TYPE_VAT = "8";
    public static final String INVOICE_TYPE_VAT_NAME = "增值税专用发票";

    /** 发票默认抬头 */
    public static final String INVOICE_DEFAULT_TEXT = "个人";

    /** COMBO.混合 */
    public static final String PAYMENTMETHOD_COMBO = "COMBO";

    /** POINT.积分 */
    public static final String PAYMENTMETHOD_POINT = "POINT";

    /** PROMO.折扣价 */
    public static final String PAYMENTMETHOD_PROMO = "PROMO";

    /** VOUCHER */
    public static final String PAYMENTMETHOD_VOUCHER = "VOUCHER";

    /** 每次查询的订单数 */
    public static final int QUERY_ORDER_SIZE = 1000;

    /** 每次发送的最大订单数 */
    public static final int SEND_ORDER_MAX_SIZE = 100;

    /** COMBO订单标识(Tmall定义) */
    public static final String COMBO_TYPE = "1";

    /* ------------------------- Trade Memo  ------------------------ */
    /** 最大重试次数 */
    public static final int RETRY_MAX_COUNT = 3;

    /* ------------------------- 退款 refund  ------------------------ */
    /** 0.未处理 */
    public static final Integer REFUND_PROCESS_STATUS_CREATE = 0;
    /** 1.已处理 */
    public static final Integer REFUND_PROCESS_STATUS_DONE = 1;
    /** 2.天猫解析异常 */
    public static final Integer REFUND_PROCESS_STATUS_ERROR = 2;
    /** 3.数据异常 */
    public static final Integer REFUND_PROCESS_STATUS_DATA_ERROR = 3;
    /** 4.系统异常 */
    public static final Integer REFUND_PROCESS_STATUS_SYSTEM_ERROR = 4;

    // 天猫退款状态
    /** WAIT_SELLER_AGREE(买家已经申请退款，等待卖家同意)  */
    public static final String REFUND_WAIT_SELLER_AGREE = "WAIT_SELLER_AGREE";

    /** WAIT_BUYER_RETURN_GOODS(卖家已经同意退款，等待买家退货) */
    public static final String REFUND_WAIT_BUYER_RETURN_GOODS ="WAIT_BUYER_RETURN_GOODS";

    /** WAIT_SELLER_CONFIRM_GOODS(买家已经退货，等待卖家确认收货) */
    public static final String REFUND_WAIT_SELLER_CONFIRM_GOODS = "WAIT_SELLER_CONFIRM_GOODS";

    /** SELLER_REFUSE_BUYER(卖家拒绝退款)  */
    public static final String REFUND_SELLER_REFUSE_BUYER = "SELLER_REFUSE_BUYER";

    /** CLOSED(退款关闭) */
    public static final String REFUND_CLOSED = "CLOSED";

    /** SUCCESS(退款成功) */
    public static final String REFUND_SUCCESS = "SUCCESS";

    /* ------------------------- 退款 refund  ------------------------ */
    /** Apple物流服务商映射编码 */
    public static final String MAPPING_APPLETRANS = "appleTrans";

    /* --------------------------- 同HUB交互相关 ----------------------------- */
    /** 苹果订单错误报表 */
    public static final String MQ_KEY_SEND_ORDER_WRONG = "AP-0";
    /** 苹果订单同步 */
    public static final String MQ_KEY_SEND_ORDER = "AP-1";
    /** 苹果退款信息 */
    public static final String MQ_KEY_SEND_REFUND = "AP-2";
    /** 苹果退款退货信息 */
    public static final String MQ_KEY_SEND_RETURN = "AP-3";
    /** 苹果前一天的销售报表 */
    public static final String MQ_KEY_SEND_ORDER_REPORT = "AP-4";
    /** 苹果前一天的发货失败报表 */
    public static final String MQ_KEY_SEND_DELI_FAIL_REPORT = "AP-5";
    /** 苹果超过48小时未发货预警 */
    public static final String MQ_KEY_SEND_DELI_DELAYED_REPORT = "AP-6";
    /** 苹果quote报表 */
    public static final String MQ_KEY_SEND_QUOTE_REPORT = "AP-7";
    /** 苹果新的B2B接口 */
    public static final String MQ_KEY_SEND_B2B = "AP-8";
    /** 苹果自动退换货报表 */
    public static final String MQ_KEY_SEND_AUTORETURN = "AP-9";
    /** 苹果预警 */
    public static final String MQ_KEY_SEND_APPLE_NOTICE = "AP-10";
    /** 苹果未创单预警(外部预警) */
    public static final String MQ_KEY_APPLE_NO_CREATE_ORDER_NOTICE = "AP-11";
    /** 苹果订单状态查询和退款状态查询 */
    public static final String MQ_KEY_APPLE_STATUS_QUERY_NOTICE = "AP-12";
    /** 苹果自动退换货报表(取消) */
    public static final String MQ_KEY_SEND_AUTORETURN_FOR_CANCEL = "AP-13";

    /** 苹果自动退换货前一天数据 */
    public static final String MQ_KEY_DAY_AUTORETURN = "AP-14";
    /** 苹果订单取消前一天数据**/
    public static final String MQ_KEY_DAY_AUTORETURN_FOR_CANCEL = "AP-15";

    /* --------------------------- 发货 ----------------------------- */
    public static final String SHIPMENT_SMS_CONTENT = "您的订单%d件商品中的%d件已发货. 快递单号%s由%s为您送货. 更多信息: %s. 发票将在商品寄出后的约3个工作日寄出, 请耐心等待. [Apple Store官方旗舰店]";

    /* --------------------------- 序列 ----------------------------- */
    public static final String SEQ_CODE_APPLEORDER = "AppleOrder";

    /* --------------------------- quote ----------------------------- */
    /** 删除 */
    public static final Integer DELETION_INDICATOR_Y = 1;

    /** 不删除 */
    public static final Integer DELETION_INDICATOR_N = 0;

    /** 天猫店铺的ID */
    public static final String DEFAULT_SOLD_TO_ID = "91962";

    /* ---------------------------  npi  ------------------------------- */
    /** 每次查询的NPI订单数 */
    public static final Integer NPI_QUERY_ORDER_SIZE = Integer.parseInt(prop.getProperty("NPI_QUERY_ORDER_SIZE"));
    /** 每次发送的最大订单数 */
    public static final Integer NPI_MAX_SEND_ORDER_SIZE = Integer.parseInt(prop.getProperty("NPI_MAX_SEND_ORDER_SIZE"));

    /* ---------------------------  auto return  ------------------------------- */
    /** 未签收 R43 */
    public static final Integer SHIPPING_NO = 0;
    /** 已签收 R22 */
    public static final Integer SHIPPING_YES = 1;
    /** B2B轮询重试最大次数 */
    public static final Integer B2B_RETRY_COUNT = 3;
    /** 异常重试最大次数 */
    public static final Integer AR_RETRY_COUNT = 3;

    /** 订单状态OK，可以往下走 */
    public static final String B2B_ORDER_STATUS_OK = "BOS_OK";
    /** 订单状态不OK，不可以往下走 */
    public static final String B2B_ORDER_STATUS_NO = "BOS_NO";
    /** 苹果店铺在天猫的ADDRESS ID，测试店铺 120768121，正式店铺 116036696 */
    public static final Long ADDRESS_ID = Long.parseLong(prop.getProperty("APPLE_SHOP_ADDRESSID"));

    /* ---------------------------  预警  ------------------------------- */
    /** 缺少物流商mapping信息 */
    public static final String NOTICE_TYPE_NO_LP = "not.logistic.company";
    /** 调用天猫发货接口异常 */
    public static final String NOTICE_TYPE_TM_ERROR = "logistic.call.tmall.fail";

    /** apple 订单hub创单未反馈 **/
    public static final String HUB_NOT_CONFIRM = "hub.not.confirm";

    private AppleConstants() {}
}
