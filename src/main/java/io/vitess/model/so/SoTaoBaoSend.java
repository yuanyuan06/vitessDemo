package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 过单pac、过仓都不再使用该类（逐渐其他地方也不再推荐使用）
 * @author fanht
 *
 */
@Data
@TableName("t_so_taobao_send")
public class SoTaoBaoSend extends SuperEntity {

	private static final long serialVersionUID = 4258703437459065240L;

	@TableField("PLATFORM_ORDER_CODE")
	private String platformOrderCode;

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("TARGET_CODE")
	private String targetCode;

	@TableField("TARGET_TYPE")
	private String targetType;

	@TableField("PROCESS_STATUS")
	private Integer processStatus = 0;

	@TableField("PROCESS_RESULT")
	private String processResult;

	@TableField("SALES_ORDER_ID")
	private Long salesOrderId;

	@TableField("TYPE")
	private int type;
	
   
    
}
