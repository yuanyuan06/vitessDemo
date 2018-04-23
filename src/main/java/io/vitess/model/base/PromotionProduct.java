package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 促销活动主卖品
 * 
 * @author zhouzheng.deng
 * 
 */
@Data
//@TableName("t_ma_promotion_product")
public class PromotionProduct extends SuperEntity{
    private static final long serialVersionUID = 5986153031358529284L;

    private int version;

    private Date createTime;

    private Integer quantityLevel;

    private Product product;

    private PromotionRule promotionRule;

    private ProductCategoryTag productCategoryTag;

    private Long shopId;

}
