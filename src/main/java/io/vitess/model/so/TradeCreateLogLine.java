package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

/**
 * T_SO_CREATE_LOG_LINE
 * 
 * @author wudan
 * 
 */
@TableName("t_td_create_log_line")
public class TradeCreateLogLine extends SuperEntity{

	private static final long serialVersionUID = -8995584493628211496L;

	@TableField("VERSION")
	private int version;

	@TableField("REMARK")
	private String remark;

	@TableField("PLATFORM_ORDER_CODE")
	private String platformOrderCode;

	@TableField("TRADE_ID")
	private Trade trade;

	@TableField("IS_SUCCESSED")
	private Boolean isSuccessed;

	@TableField("TD_CREATELOG_ID")
	private TradeCreateLog tdCreateLog;

}
