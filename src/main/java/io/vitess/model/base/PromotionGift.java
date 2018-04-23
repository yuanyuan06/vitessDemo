package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import io.vitess.enums.ProductScope;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 促销赠品
 * 
 * @author zhouzheng.deng
 * 
 */
@Data
//@TableName("t_ma_promotion_gift")
public class PromotionGift extends SuperEntity {
    private static final long serialVersionUID = 1306160650881738719L;

    private Long Id;

    private int version;

    private BigDecimal amountLevel;

    private Integer maxGiftTimes;

    private Integer usedGiftTimes;

    private ProductScope productScope;

    private String includeLabel;

    private String excludeLabel;

    private Boolean isExceludeComboSku = Boolean.FALSE;

    private PromotionActivity promotion;

    private Integer ruleLevel;

    private String description;

    private Long shopId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 每次赠送数量
     */
    private Integer giftQty;

    /**
     * 赠品总数量[最大赠送次*每次赠送数量]
     */
    private Integer totalQty;

    /**
     * 主卖品
     */
    private Product product;

    /**
     * 相关促销活动
     */
    private PromotionRule promotionRule;

    //单个规则下绑定sku的Id用于标示，是否是到sku的促销应用
    private String bindId;

    private Long skuId;
	
}
