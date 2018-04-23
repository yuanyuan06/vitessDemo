package io.vitess.model.base;

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
//@TableName("t_ma_promotion_gift_sku")
public class PromotionGiftSku extends SuperEntity {

    private static final long serialVersionUID = 1306160650881738719L;
    private int version;

    private Date createTime;

    private Integer giftQty;

    private Integer totalQty;

    private Long promotionGiftId;

    private Integer userGiftNum;

    private Long skuId;

    private Integer skuGiftLevel;

    private Long shopId;
	
}
