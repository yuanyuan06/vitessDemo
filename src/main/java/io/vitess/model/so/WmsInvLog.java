package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 库存流水
 * @date 2015年8月26日 下午5:59:06
 */
@TableName("t_wms_inv_log")
public class WmsInvLog extends SuperEntity {

	private static final long serialVersionUID = 4745079578898655197L;
	@TableField("WMS_ORDER_STATUS_ID")
	private Long wmsOrderStatusId;

	@TableField("SKU")
	private String sku;

	@TableField("QTY")
	private Long qty;

	@TableField("BTACH_CODE")
	private String btachCode;

	@TableField("BARCH_NO")
	private String barchNo;

	@TableField("INV_STATUS")
	private String invStatus;

	@TableField("WAREHOUCE_CODE")
	private String warehouceCode;

	@TableField("TRANSACTION_TIME")
	private Date transactionTime;

	@TableField("INV_OWNER")
	private String invOwner;

	@TableField("MARKET_ABILITY")
	private Boolean marketAbility;


}
