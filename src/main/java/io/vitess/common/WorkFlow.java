package io.vitess.common;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.enums.SlipType;

import java.util.Date;

/**
 * 工作流定义
 * 
 * @author Benjamin
 * 
 */
@TableName("t_wf_workflow")
public class WorkFlow extends SuperEntity {

    private static final long serialVersionUID = -2842914365447422502L;

    /**
     * 工作流编码
     */
    @TableField("CODE")
    private String code;

    /**
     * 工作流名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 是否有效
     */
    @TableField("IS_AVAILABLE")
    private Boolean isAvailable = true;

    /**
     * 工作流描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 最近修改时间
     */
    @TableField("LAST_MODIFY_TIME")
    private Date lastModifyTime;

    /**
     * 相关单据类型
     */
    @TableField("SLIP_TYPE")
    private SlipType slipType;

    /**
     * VERSION
     */
    @Version
    private int version;

    /**
     * 最近修改用户，没有创建用户是因为目前定义所有工作流都由脚本进行初始化
     */
    @TableField("LAST_MODIFY_USER_ID")
    private User lastModifyUser;

}
