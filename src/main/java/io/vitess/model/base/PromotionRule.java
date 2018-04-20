package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.ProductScope;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 促销活动规则
 * 
 * @author zhouzheng.deng
 * 
 */
@Data
@TableName("t_ma_promotion_rule")
public class PromotionRule extends SuperEntity {

    private static final long serialVersionUID = 7370752642473993843L;

    @TableField("VERSION")
    private int version;

    @TableField("AMOUNT_LEVEL")
    private BigDecimal amountLevel;

    @TableField("MAX_GIFT_TIMES")
    private Integer maxGiftTimes;

    @TableField("USED_GIFT_TIMES")
    private Integer usedGiftTimes;

    @TableField("loxia.dao.support.GenericEnumUserType")
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
	
}
