package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.PromotionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 赠品应用日志
 * 
 * @author fanht
 * 
 */

@Data
@TableName("t_ma_promotion_apply_log")
public class SoPromotionApplyLog extends SuperEntity{
	private static final long serialVersionUID = 5397094333207917229L;

	@TableField("VERSION")
	private Integer version;

	@TableField("PROMOTION_CODE")
	private String promotionCode;

	@TableField("DESCRIPTION")
	private String description;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("loxia.dao.support.GenericEnumUserType")
	private PromotionType type;

	@TableField("GIFT_TYPE")
	private String GiftType;

	@TableField("DISCOUNT")
	private BigDecimal discount;

	@TableField("SOLINE_ID")
	private SalesOrderLine soLine;

	@TableField("GIFT_QTY")
	private Integer giftQty;

	@TableField("PROMOTION_GIFT_ID")
	private Long promotionGiftId;

	@TableField("SHOP_ID")
	private Long shopId;
	
}
