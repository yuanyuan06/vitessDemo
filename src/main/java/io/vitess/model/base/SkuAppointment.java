package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @Description:  指定sku预约过仓表
 * @author zhiyong.shi
 * 2017年2月3日
 */

@Data
@TableName("t_ma_sku_appointment_wh")
public class SkuAppointment extends SuperEntity {

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("ACTIVITY_NAME")
	private String activityName;

	@TableField("SKU_ID")
	private Long skuId;

	@TableField("SKU_CODE")
	private String skuCode;

	@TableField("ACTIVITY_STATUS")
	private String activityStatus;

	@TableField("CREATE_USER_NO")
	private String createUserNo ;

	@TableField("SUSPEND_START_TIME")
	private Date suspendStartTime;

	@TableField("SUSPEND_END_TIME")
	private Date suspendEndTime;

	@TableField("CREATE_TIME")
	private Date CreateTime;

	@TableField("UPDATE_TIME")
	private Date UpdateTime;

	@TableField("UPDATE_USER_NO")
	private String updateUserNo;

	@TableField("LOG_OFF_TYPE")
	private int logOffType;

	@TableField("TO_WH_TIME")
	private int toWhTime;
    
}
