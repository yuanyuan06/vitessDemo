package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 工作流实例日志
 * 
 * @author Benjamin
 * 
 */
@TableName("t_wf_workflow_task_log")
public class WorkTaskLog extends SuperEntity {

    private static final long serialVersionUID = -6836940745120659062L;

    @TableField("TASK_ID")
    private Long taskId;

    @TableField("FROM_NODE_NO")
    private Integer fromNodeNo;

    @TableField("TO_NODE_NO")
    private Integer toNodeNo;

    @TableField("TRANSITION_CODE")
    private String transitionCode;

    @TableField("TRANSITION_TIME")
    private Date transitionTime;

    @TableField("ENTER_TIME")
    private Date enterTime;

    @TableField("USER_ID")
    private Long userId;

    @TableField("MEMO")
    private String memo;

    @TableField("SHOP_ID")
    private Long shopId;

}
