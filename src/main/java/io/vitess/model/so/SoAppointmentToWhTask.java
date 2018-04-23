package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import io.vitess.enums.TaskProcessStatus;
import lombok.Data;

import java.util.Date;

/**
 * 预约日期提交至仓库-任务列表
 * 
 * @className com.jumbo.model.sales.SoAppointmentToWhTask
 * @author hailiang.jiang
 * @date 2014年12月18日 下午2:54:09
 */
@Data
//@TableName("t_td_so_appointment_to_wh_task")
public class SoAppointmentToWhTask extends SuperEntity {

	private static final long serialVersionUID = 642285886307395903L;

	private int version;

	private Long salesOrderId;

	private String fromWhCode;

	private String toWhCode;

	private Date toWhTime;

	private Boolean isAutoTo;

	private TaskProcessStatus processStatus;

	private String processResult;

	private String remark;

	private Date createTime;

	private String createUserNo;

	private Date updateTime;

	private String updateUserNo;

	private Long shopId;


}
