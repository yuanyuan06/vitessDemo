package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;


@Data
//@TableName("t_sms_data_param")
public class SmsDataParam extends SuperEntity {
	private static final long serialVersionUID = 7335941287235543749L;

	private java.lang.Long shopId;

	private java.lang.Integer dataType;

	private java.lang.String dataRemark;

	private java.lang.Integer smsType;

	private java.lang.String smsRemark;

	private java.lang.Integer processStatus;

	private java.lang.String processResult;

	private java.lang.String remark;

	private java.util.Date inputTime;

	private java.util.Date updateTime;

}
