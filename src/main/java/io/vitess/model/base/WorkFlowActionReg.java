package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 工作流动作定义
 * 
 * @author Benjamin
 * 
 */
@Data
//@TableName("t_wf_workflow_action_reg")
public class WorkFlowActionReg extends SuperEntity {

    private static final long serialVersionUID = 3473823165477046157L;

    private String name;

    private int sortNo;

    private String actionClassName;

    private WorkFlowTransition flowTransition;

    private Map<String,String> params = new HashMap<String, String>();
}
