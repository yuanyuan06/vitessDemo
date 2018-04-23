package io.vitess.model.so;

import io.vitess.common.SuperEntity;

/**
 * T_SO_CREATE_LOG_LINE
 * 
 * @author wudan
 * 
 */
//@TableName("t_td_create_log_line")
public class TradeCreateLogLine extends SuperEntity{

	private static final long serialVersionUID = -8995584493628211496L;

	private int version;

	private String remark;

	private String platformOrderCode;

	private Trade trade;

	private Boolean isSuccessed;

	private TradeCreateLog tdCreateLog;

}
