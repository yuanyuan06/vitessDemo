package io.vitess.model.so;

import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 订单匹配的过仓顺序
 * @author hailiang.jiang
 * @date 2015年5月14日 下午5:29:26
 */
//@TableName("t_td_so_delivery_wh")
public class SoDeliveryWh extends SuperEntity {
    private static final long serialVersionUID = -5447659683465153839L;
    
    public static Integer STATUS_WAIT_DEAL = 0;
    public static Integer STATUS_PROCESSING = 1;
    public static Integer STATUS_FAIL = 5;
    public static Integer STATUS_SUCCESS = 10;


	private Long soId;


	private String whCode;


	private Integer priority;


	private Integer status;


	private String result;


	private String remark;


	private Date createTime;


	private Long shopId;
}
