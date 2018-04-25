package io.vitess.model.base;




import io.vitess.common.SuperEntity;
import io.vitess.enums.SlipType;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 工作流定义
 * 
 * @author Benjamin
 * 
 */
@Data
//@TableName("t_wf_workflow")
public class WorkFlow extends SuperEntity {

    private static final long serialVersionUID = -2842914365447422502L;

    /**
     * 工作流编码
     */

    private String code;

    /**
     * 工作流名称
     */

    private String name;

    /**
     * 是否有效
     */

    private Boolean isAvailable = true;

    /**
     * 工作流描述
     */

    private String description;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 最近修改时间
     */

    private Date lastModifyTime;

    /**
     * 相关单据类型
     */

    private SlipType slipType;

    /**
     * VERSION
     */
    private int version;

    /**
     * 最近修改用户，没有创建用户是因为目前定义所有工作流都由脚本进行初始化
     */

    private User lastModifyUser;

    private List<WorkFlowNode> flowNodes = new ArrayList<WorkFlowNode>();


}
