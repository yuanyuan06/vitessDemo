package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 库存流水
 * @date 2015年8月28日 上午10:58:15
 */
@TableName("t_td_so_inv_flow")
public class SoInvFlow extends SuperEntity {

	private static final long serialVersionUID = 1L;
	@TableField("SO_ID")
	private Long soId;

	@TableField("SKU_CODE")
	private String skuCode;

	@TableField("QTY")
	private Long qty;

	@TableField("BTACH_CODE")
	private String btachCode;

	@TableField("BARCH_NO")
	private String barchNo;

	@TableField("INV_STATUS")
	private String invStatus;

	@TableField("WH_CODE")
	private String whCode;

	@TableField("TRANSACTION_TIME")
	private Date transactionTime;

	@TableField("INV_OWNER")
	private String invOwner;

	@TableField("INPUT_TIME")
	private Date inputTime;

	@TableField("DATA_TYPE")
	private Integer dataType;

	@TableField("MARKET_ABILITY")
	private Boolean marketAbility;

	@TableField("SHOP_ID")
	private Long shopId;

}
