package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 过单pac、过仓都不再使用该类（逐渐其他地方也不再推荐使用）
 * @author fanht
 *
 */
@Data
//@TableName("t_so_taobao_send")
public class SoTaoBaoSend extends SuperEntity {

	private static final long serialVersionUID = 4258703437459065240L;


	private String platformOrderCode;


	private Long shopId;


	private String targetCode;


	private String targetType;


	private Integer processStatus = 0;


	private String processResult;


	private Long salesOrderId;


	private int type;
	
   
    
}
