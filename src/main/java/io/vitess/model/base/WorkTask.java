package io.vitess.model.base;

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
//@TableName("t_wf_workflow_task")
public class WorkTask extends SuperEntity {

    private static final long serialVersionUID = 6767686691630596326L;

    /**
     * 实例编码
     */
    private String taskNo;

    /**
     * 实例当前节点
     */
    private int currentNodeNo;

    /**
     * 实例开始时间
     */
    private Date startTime;

    /**
     * 实例结束时间
     */
    private Date endTime;

    /**
     * 实例当前节点进入时间
     */
    private Date enterTime;

    /**
     * 是否有效
     */
    private Boolean isAvailable = true;

    /**
     * 工作流实例状态
     */
    private Integer status;

    /**
     * 相关单据类型
     */
//    private SlipType refSlipType;
    private Integer refSlipType;

    /**
     * 相关单据编码
     */
    private String refSlipCode;

    /**
     * 相关单据Id
     */
    private Long refSlipId;

    /**
     * 相关单据店铺ID
     */
    private Long refSlipShopId;

    /**
     * VERSION
     */
    private int version;

    /**
     * 相关工作流
     */
    private WorkFlow workFlow;

    /**
     * 创建用户
     */
    private User createUser;

}
