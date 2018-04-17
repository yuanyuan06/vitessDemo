package io.vitess.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YSH4807
 * @date 2018/4/11 13:36
 */
@Data
public class MqSoPromotionLog implements Serializable {

    /**
     * 整单金额促销
     */
    public static Integer SO_PLATFORM_PROMOTION_SCOPE_TYPE_ORDER = new Integer(1);

    /**
     * 单行金额促销
     */
    public static Integer SO_PLATFORM_PROMOTION_SCOPE_TYPE_LINE = new Integer(2);

    private Long Id;
    private Long soLogId;

    private Date version;

    /**
     * 平台订单号
     */
    private String platformOrderCode;

    /**
     * 平台订单行ID
     */
    private Long platformLineId;
    private Long soLineLogId;

    /**
     * 优惠信息的名称
     */
    private String  promotionName;

    /**
     * 优惠金额（免运费、限时打折时为空）,单位：元
     */
    private BigDecimal discountFee;

    /**
     * 满就送商品时，所送商品的名称
     */
    private String  giftItemName;

    /**
     * 赠品的宝贝id
     */
    private String giftItemId;

    /**
     * 满就送礼物的礼物数量
     */
    private Integer giftItemNum;

    /**
     * 优惠活动的描述
     */
    private String description;

    /**+
     * 优惠id，(由营销工具id、优惠活动id和优惠详情id组成，结构为：营销工具id-优惠活动id_优惠详情id，如mjs-123024_211143）
     */
    private String  promotionId;

    /**
     * 促销范围
     * 1:整单金额促销 2:单行金额促销 3：整单平摊
     */
    private Integer scopeType;

    //店铺ID
    private Long shopId;

    private Long soLineLog;

    private Long soLog;
}
