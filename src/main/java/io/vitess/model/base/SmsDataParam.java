package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;


@Data
@TableName("t_sms_data_param")
public class SmsDataParam extends SuperEntity {
	private static final long serialVersionUID = 7335941287235543749L;

	@TableField("SHOP_ID")
	private java.lang.Long shopId;

	@TableField("DATA_TYPE")
	private java.lang.Integer dataType;

	@TableField("DATA_REMARK")
	private java.lang.String dataRemark;

	@TableField("SMS_TYPE")
	private java.lang.Integer smsType;

	@TableField("SMS_REMARK")
	private java.lang.String smsRemark;

	@TableField("PROCESS_STATUS")
	private java.lang.Integer processStatus;

	@TableField("PROCESS_RESULT")
	private java.lang.String processResult;

	@TableField("REMARK")
	private java.lang.String remark;

	@TableField("INPUT_TIME")
	private java.util.Date inputTime;

	@TableField("UPDATE_TIME")
	private java.util.Date updateTime;

}
