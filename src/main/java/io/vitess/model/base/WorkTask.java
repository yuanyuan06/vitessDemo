package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.common.SuperEntity;
import io.vitess.common.User;
import io.vitess.common.WorkFlow;
import lombok.Data;

import java.util.Date;

/**
 * 工作流实例
 * 
 * @author Benjamin
 * 
 */
@Data
@TableName("t_wf_workflow_task")
public class WorkTask extends SuperEntity {

    private static final long serialVersionUID = 6767686691630596326L;

    /**
     * 实例编码
     */
    @TableField("TASK_NO")
    private String taskNo;

    /**
     * 实例当前节点
     */
    @TableField("CURRENT_NODE_NO")
    private int currentNodeNo;

    /**
     * 实例开始时间
     */
    @TableField("START_TIME")
    private Date startTime;

    /**
     * 实例结束时间
     */
    @TableField("END_TIME")
    private Date endTime;

    /**
     * 实例当前节点进入时间
     */
    @TableField("ENTER_TIME")
    private Date enterTime;

    /**
     * 是否有效
     */
    @TableField("IS_AVAILABLE")
    private Boolean isAvailable = true;

    /**
     * 工作流实例状态
     */
    @TableField("STATUS")
//    private WorkTaskStatus status;
    private Long status;

    /**
     * 相关单据类型
     */
    @TableField("REF_SLIP_TYPE")
//    private SlipType refSlipType;
    private Long refSlipType;

    /**
     * 相关单据编码
     */
    @TableField("REF_SLIP_CODE")
    private String refSlipCode;

    /**
     * 相关单据Id
     */
    @TableField("REF_SLIP_ID")
    private Long refSlipId;

    /**
     * 相关单据店铺ID
     */
    @TableField("REF_SLIP_SHOP_ID")
    private Long refSlipShopId;

    /**
     * VERSION
     */
    @Version
    private int version;

    /**
     * 相关工作流
     */
    @TableField("WORKFLOW_ID")
    private WorkFlow workFlow;

    /**
     * 创建用户
     */
    @TableField("CREATE_USER_ID")
    private User createUser;

}
