package io.vitess.model.base;



import io.vitess.common.SuperEntity;
import io.vitess.enums.WorkFlowNodeType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作流节点定义
 * 
 * @author Benjamin
 * 
 */
@Data
//@TableName("t_wf_workflow_node")
public abstract class WorkFlowNode extends SuperEntity {

    private static final long serialVersionUID = 8905563189130164524L;

    /**
     * ID
     */
    protected Long id;

    /**
     * 节点类型
     */
    protected WorkFlowNodeType type;

    /**
     * 节点编号
     */

    protected Integer nodeNo;

    /**
     * 节点名称
     */
    protected String name;

    /**
     * 节点最大操作等待时间(以秒为单位)，0代表不设置
     */
    protected Long ttl = 0L;

    /**
     * 工作流
     */

    protected WorkFlow workFlow;

    protected List<WorkFlowNodeActor> actors = new ArrayList<WorkFlowNodeActor>();

    protected List<WorkFlowTransition> transitions = new ArrayList<WorkFlowTransition>();

}
