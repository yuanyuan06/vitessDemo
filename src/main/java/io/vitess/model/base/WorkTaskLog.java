package io.vitess.model.base;

import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 工作流实例日志
 * 
 * @author Benjamin
 * 
 */
//@TableName("t_wf_workflow_task_log")
public class WorkTaskLog extends SuperEntity {

    private static final long serialVersionUID = -6836940745120659062L;

    private Long taskId;

    private Integer fromNodeNo;

    private Integer toNodeNo;

    private String transitionCode;

    private Date transitionTime;

    private Date enterTime;

    private Long userId;

    private String memo;

    private Long shopId;

}
