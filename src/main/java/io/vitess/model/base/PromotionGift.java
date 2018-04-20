package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("t_ma_promotion_gift")
public class PromotionGift extends SuperEntity {
    private static final long serialVersionUID = 1306160650881738719L;

    private Long Id;

    @TableField("VERSION")
    private int version;

    @TableField("AMOUNT_LEVEL")
    private BigDecimal amountLevel;

    @TableField("MAX_GIFT_TIMES")
    private Integer maxGiftTimes;

    @TableField("USED_GIFT_TIMES")
    private Integer usedGiftTimes;

    @TableField("PRODUCT_SCOPE")
    private ProductScope productScope;

    @TableField("INCLUDE_LABEL")
    private String includeLabel;

    @TableField("EXCLUDE_LABEL")
    private String excludeLabel;

    @TableField("IS_EXCELUDE_COMBO_SKU")
    private Boolean isExceludeComboSku = Boolean.FALSE;

    @TableField("PROMOTION_ID")
    private PromotionActivity promotion;

    @TableField("RULE_LEVEL")
    private Integer ruleLevel;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("SHOP_ID")
    private Long shopId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 每次赠送数量
     */
    @TableField("GIFT_QTY")
    private Integer giftQty;

    /**
     * 赠品总数量[最大赠送次*每次赠送数量]
     */
    @TableField("TOTAL_QTY")
    private Integer totalQty;

    /**
     * 主卖品
     */
    @TableField("PRODUCT_ID")
    private Product product;

    /**
     * 相关促销活动
     */
    @TableField("PROMOTION_RULE_ID")
    private PromotionRule promotionRule;

    //单个规则下绑定sku的Id用于标示，是否是到sku的促销应用
    @TableField("BIND_ID")
    private String bindId;

    @TableField("SKU_ID")
    private Long skuId;
	
}
