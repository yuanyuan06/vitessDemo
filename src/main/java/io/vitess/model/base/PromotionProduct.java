package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("t_ma_promotion_product")
public class PromotionProduct extends SuperEntity{
    private static final long serialVersionUID = 5986153031358529284L;

    @TableField("VERSION")
    private int version;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("QUANTITY_LEVEL")
    private Integer quantityLevel;

    @TableField("PRODUCT_ID")
    private Product product;

    @TableField("PROMOTION_RULE_ID")
    private PromotionRule promotionRule;

    @TableField("PC_TAG_ID")
    private ProductCategoryTag productCategoryTag;

    @TableField("SHOP_ID")
    private Long shopId;

}
