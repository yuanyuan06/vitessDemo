package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import io.vitess.enums.TaskProcessStatus;

import java.util.Date;

/**
 * 预约日期提交至仓库-任务列表
 * 
 * @className com.jumbo.model.sales.SoAppointmentToWhTask
 * @author hailiang.jiang
 * @date 2014年12月18日 下午2:54:09
 */

@TableName("t_td_so_appointment_to_wh_task")
public class SoAppointmentToWhTask extends SuperEntity {

	private static final long serialVersionUID = 642285886307395903L;

	@TableField("VERSION")
	private int version;

	@TableField("SALES_ORDER_ID")
	private Long salesOrderId;

	@TableField("FROM_WH_CODE")
	private String fromWhCode;

	@TableField("TO_WH_CODE")
	private String toWhCode;

	@TableField("TO_WH_TIME")
	private Date toWhTime;

	@TableField("IS_AUTO_TO")
	private Boolean isAutoTo;

	@TableField("PROCESS_STATUS")
	private TaskProcessStatus processStatus;

	@TableField("PROCESS_RESULT")
	private String processResult;

	@TableField("REMARK")
	private String remark;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("CREATE_USER_NO")
	private String createUserNo;

	@TableField("UPDATE_TIME")
	private Date updateTime;

	@TableField("UPDATE_USER_NO")
	private String updateUserNo;

	@TableField("SHOP_ID")
	private Long shopId;


}
