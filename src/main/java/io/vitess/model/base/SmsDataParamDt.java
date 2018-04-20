package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 短信发送-参数名/参数值
 * @author hailiang.jiang
 * @date 2015年9月25日 下午1:27:52
 */
@Data
@TableName("t_sms_data_param_dt")
public class SmsDataParamDt extends SuperEntity {
	private static final long serialVersionUID = 6004121417708110666L;

	@TableField("SMS_DATA_PARAM_ID")
	private java.lang.Long smsDataParamId;

	@TableField("PARAM")
	private java.lang.String param;

	@TableField("VALUE")
	private java.lang.String value;

	@TableField("REMARK")
	private String remark;

	@TableField("SHOP_ID")
	private Long shopId;

}
