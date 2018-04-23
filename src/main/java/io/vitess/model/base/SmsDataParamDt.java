package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 短信发送-参数名/参数值
 * @author hailiang.jiang
 * @date 2015年9月25日 下午1:27:52
 */
@Data
//@TableName("t_sms_data_param_dt")
public class SmsDataParamDt extends SuperEntity {
	private static final long serialVersionUID = 6004121417708110666L;

	private java.lang.Long smsDataParamId;

	private java.lang.String param;

	private java.lang.String value;

	private String remark;

	private Long shopId;

}
