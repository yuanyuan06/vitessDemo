package io.vitess.model.base;

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
//@TableName("t_ma_sku_appointment_wh")
public class SkuAppointment extends SuperEntity {

	private Long shopId;

	private String activityName;

	private Long skuId;

	private String skuCode;

	private String activityStatus;

	private String createUserNo ;

	private Date suspendStartTime;

	private Date suspendEndTime;

	private Date CreateTime;

	private Date UpdateTime;

	private String updateUserNo;

	private int logOffType;

	private int toWhTime;
    
}
