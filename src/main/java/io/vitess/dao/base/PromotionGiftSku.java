package io.vitess.dao.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 促销赠品表
 * @author zhiyong.shi
 * @time 2016年12月9日
 * @param
 * @updateBy zhiyong.shi
 */
@Data
@TableName("t_ma_promotion_gift_sku")
public class PromotionGiftSku extends SuperEntity {

    private static final long serialVersionUID = 1306160650881738719L;
    @TableField("VERSION")
    private int version;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("GIFT_QTY")
    private Integer giftQty;

    @TableField("TOTAL_QTY")
    private Integer totalQty;

    @TableField("PROMOTION_GIFT_ID")
    private Long promotionGiftId;

    @TableField("USED_GIFT_NUM")
    private Integer userGiftNum;

    @TableField("SKU_ID")
    private Long skuId;

    @TableField("SKU_GIFT_LEVEL")
    private Integer skuGiftLevel;

    @TableField("SHOP_ID")
    private Long shopId;
	
}
