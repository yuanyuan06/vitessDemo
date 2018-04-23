package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("t_wf_workflow_action_reg")
public class WorkFlowActionReg extends SuperEntity {

    private static final long serialVersionUID = 3473823165477046157L;

    @TableField("NAME")
    private String name;

    @TableField("SORT_NO")
    private int sortNo;

    @TableField("ACTION_CLASS_NAME")
    private String actionClassName;

    @TableField("IDX_WFAG_WFT")
    private WorkFlowTransition flowTransition;

    @TableField("PARAM_VALUE")
    private Map<String,String> params = new HashMap<String, String>();
}
