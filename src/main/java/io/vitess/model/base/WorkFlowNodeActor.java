package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import io.vitess.enums.ActorType;
import lombok.Data;

/**
 * 节点操作人，可以基于职位定义，可以基于人定义
 * 
 * @author Benjamin
 * 
 */
@Data
//@TableName("t_wf_workflow_node_actor")
public abstract class WorkFlowNodeActor extends SuperEntity {

    private static final long serialVersionUID = 4023979621297738804L;

    /**
     * ID
     */
    protected Long id;

    /**
     * 操作人类型
     */
    protected ActorType type;

    /**
     * 相关工作流节点
     */
    protected WorkFlowNode flowNode;

}
