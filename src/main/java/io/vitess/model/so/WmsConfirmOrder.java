package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.Date;

@TableName("t_wms_confirm_order")
public class WmsConfirmOrder extends SuperEntity {

	private static final long serialVersionUID = -9116511162106009899L;

	@TableField("RECEIVE_WMS_LOG_ID")
	private Long receiveWmsLogId;

	@TableField("PLATFORM_ORDER_CODE_N")
	private String platformOrderCodeN;

	@TableField("OWNER")
	private String owner;

	@TableField("ORDER_TYPE")
	private Integer orderType;

	@TableField("IS_SPLIT_ORDER")
	private Boolean isSplitOrder;

	@TableField("WMS_STATUS")
	private Integer wmsStatus;

	@TableField("WMS_MEMO")
	private String wmsMemo;

	@TableField("PROCESS_STATUS")
	private Integer processStatus = 0;

	@TableField("PROCESS_RESULT")
	private String processResult;

	@TableField("PROCESS_TIME")
	private Date processTime;

	@TableField("INPUT_TIME")
	private Date inputTime;

	@TableField("SHOP_ID")
	private Long shopId;

}
