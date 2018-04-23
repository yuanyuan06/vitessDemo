package io.vitess.common;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.enums.WorkFlowNodeType;
import io.vitess.model.base.WorkFlowNodeActor;
import io.vitess.model.base.WorkFlowTransition;
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
@TableName("t_wf_workflow_node")
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
    @TableField("NODE_NO")
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
    @TableField("WORKFLOW_ID")
    protected WorkFlow workFlow;

    protected List<WorkFlowNodeActor> actors = new ArrayList<WorkFlowNodeActor>();

    protected List<WorkFlowTransition> transitions = new ArrayList<WorkFlowTransition>();

}
