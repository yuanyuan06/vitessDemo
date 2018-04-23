package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 创单完成，工作流流转中间数据
 * @author fanht
 *
 */
@Data
@TableName("t_td_so_wf_task")
public class SoWfTask  extends SuperEntity {

	private static final long serialVersionUID = 5715739115578678372L;
    
    /**
     * taskId
     */
    @TableField("TASK_ID")
    private Long taskId;
    
    /**
     * 相关单据店铺ID
     */
    @TableField("SHOP_ID")
    private Long shopId;
    
    /**
     * 流转指令
     */
    @TableField("CMD")
    private String cmd;
    
    /**
     * 操作说明
     */
    @TableField("LOG")
    private String log;
    
	/**
	 * 创建时间
	 */
	@TableField("CREATE_TIME")
	private Date createTime = new Date();
	
	/** tmalloms处理状态（0：未处理, 10: 处理成功，5：处理失败） **/
	@TableField("PROCESS_STATUS")
	private Integer processStatus = 0;
	
	/** tmalloms处理数据结果 **/
	@TableField("PROCESS_RESULT")
	private String processResult;
	
	/** tmalloms处理处理时间 **/
	@TableField("PROCESS_TIME")
	private Date processTime;

}