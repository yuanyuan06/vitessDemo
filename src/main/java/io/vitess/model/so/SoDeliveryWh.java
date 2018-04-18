package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 订单匹配的过仓顺序
 * @author hailiang.jiang
 * @date 2015年5月14日 下午5:29:26
 */
@TableName("t_td_so_delivery_wh")
public class SoDeliveryWh extends SuperEntity {
    private static final long serialVersionUID = -5447659683465153839L;
    
    public static Integer STATUS_WAIT_DEAL = 0;
    public static Integer STATUS_PROCESSING = 1;
    public static Integer STATUS_FAIL = 5;
    public static Integer STATUS_SUCCESS = 10;

	@TableField("so_id")
	private Long soId;

	@TableField("wh_code")
	private String whCode;

	@TableField("priority")
	private Integer priority;

	@TableField("STATUS")
	private Integer status;

	@TableField("REMARK")
	private String result;

	@TableField("RESULT")
	private String remark;

	@TableField("create_time")
	private Date createTime;

	@TableField("SHOP_ID")
	private Long shopId;
}
