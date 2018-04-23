package io.vitess.model.base;

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
//@TableName("t_ma_promotion_rule")
public class PromotionRule extends SuperEntity {

    private static final long serialVersionUID = 7370752642473993843L;

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
	
}
