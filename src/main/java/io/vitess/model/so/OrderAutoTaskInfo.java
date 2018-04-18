package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 过单pac、过仓都不再使用该类（逐渐其他地方也不再推荐使用）
 * @author fanht
 *
 */
@TableName("t_td_order_autotask_info")
public class OrderAutoTaskInfo extends SuperEntity {

    private static final long serialVersionUID = 3824593079955543749L;

    /** 订单ID */
    @TableField("ORDER_ID")
    private Long orderId;
    /** 流程实例ID */
    @TableField("TASK_ID")
    private Long task;
//    private WorkTask task;

    /** 同步至PACS时间 */
    @TableField("SYNC_TO_PACS_TIME")
    private Date syncToPacsTime;
    /** 提交至仓库时间 */
    @TableField("SUBMIT_TO_WH_TIME")
    private Date submitToWhTime;
    /** 交易确认时间 */
    @TableField("TRADE_CONFIRM_TIME")
    private Date tradeConfirmTime;
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;
    
}
