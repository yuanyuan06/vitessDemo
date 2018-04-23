package io.vitess.model.so;

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
//@TableName("t_ma_promotion_apply_log")
public class SoPromotionApplyLog extends SuperEntity{
	private static final long serialVersionUID = 5397094333207917229L;


	private Integer version;


	private String promotionCode;


	private String description;


	private Date createTime;


	private PromotionType type;


	private String GiftType;


	private BigDecimal discount;


	private SalesOrderLine soLine;


	private Integer giftQty;


	private Long promotionGiftId;


	private Long shopId;
	
}
