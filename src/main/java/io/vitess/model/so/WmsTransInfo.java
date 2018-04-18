package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 物流信息
 * @date 2015年8月26日 下午6:00:36
 */

@TableName("t_wms_trans_info")
public class WmsTransInfo implements Serializable {

	private static final long serialVersionUID = 4745079578898655197L;

	/** fk **/
	@TableField("WMS_ORDER_STATUS_ID")
	private Long wmsOrderStatusId;
	/** 物流商简称 **/
	@TableField("TRANS_CODE")
	private String transCode;
	/** 运单号 **/
	@TableField("TRANS_NO")
	private String transNo;
	/** 物流时效 **/
	@TableField("TRANS_TIME_TYPE")
	private Integer transTimeType;

}
