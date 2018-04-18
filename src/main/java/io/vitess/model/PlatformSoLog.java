package io.vitess.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

@TableName("t_so_platform_so_log")
public class PlatformSoLog extends SuperEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5866845488423813437L;

    @TableField("ID")
    private Long id;

    @TableField("CODE")
    private String code;

    @TableField("SHOP_ID")
    private Long shopId;

    @TableField("MQ_SO_LOG_ID")
    private Long mqSoLogId;

    @TableField("SOURCE_MSG")
    private String sourceMsg;

}
