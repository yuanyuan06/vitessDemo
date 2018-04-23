package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 过单pac、过仓都不再使用该类（逐渐其他地方也不再推荐使用）
 * @author fanht
 *
 */
@Data
//@TableName("t_td_order_autotask_info")
public class OrderAutoTaskInfo extends SuperEntity {

    private static final long serialVersionUID = 3824593079955543749L;

    /** 订单ID */
    private Long orderId;
    /** 流程实例ID */
    private Long task;
//    private WorkTask task;

    /** 同步至PACS时间 */
    private Date syncToPacsTime;
    /** 提交至仓库时间 */
    private Date submitToWhTime;
    /** 交易确认时间 */
    private Date tradeConfirmTime;
    
    //店铺ID
    private Long shopId;
    
}
