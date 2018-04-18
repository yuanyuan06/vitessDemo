package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

@TableName("t_wms_order_cancel_detail")
public class WmsOrderCancelDetail extends SuperEntity {

	private static final long serialVersionUID = 4745079578898655197L;

	@TableField("SHIPPING_CODE")
	private String shippingCode;

	@TableField("STATUS_CODE")
	private String statusCode;

	@TableField("MEMO")
	private String memo;

	@TableField("PROCESS_STATUS")
	private Integer processStatus = 0;

	@TableField("PROCESS_RESULT")
	private String processResult = "未处理";

}
