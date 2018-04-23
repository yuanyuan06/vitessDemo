package io.vitess.model.mq;

import io.vitess.common.SuperEntity;
import lombok.Data;

@Data
//@TableName("t_so_platform_so_log")
public class PlatformSoLog extends SuperEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5866845488423813437L;

    private Long id;

    private String code;

    private Long shopId;

    private Long mqSoLogId;

    private String sourceMsg;

}
