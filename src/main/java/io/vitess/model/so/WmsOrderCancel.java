package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


@TableName("t_wms_order_cancel")
public class WmsOrderCancel implements Serializable {

	private static final long serialVersionUID = 4745079578898655197L;

	@TableField("RECEIVE_WMS_LOG_ID")
	private Long receiveWmsLogId;

	@TableField("ORDER_CODE")
	private String orderCode;

	@TableField("WMS_STATUS")
	private Integer wmsStatus;

	@TableField("ORDER_TYPE")
	private Integer orderType;

	@TableField("WMS_MEMO")
	private String wmsMemo;

	@TableField("DATA_STATUS")
	private Integer dataStatus;

	@TableField("PROCESS_TIME")
	private Date processTime;

	@TableField("PROCESS_RESULT")
	private String processResult;

	@TableField("OPT_DESC")
	private String optDesc;

	@TableField("INPUT_TIME")
	private Date inputTime;

}
