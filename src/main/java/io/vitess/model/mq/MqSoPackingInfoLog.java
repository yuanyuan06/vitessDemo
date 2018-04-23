package io.vitess.model.mq;

import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 
 * 包装信息
 * 
 */
@Data
//@TableName("t_mq_so_packing_info_log")
public class MqSoPackingInfoLog extends SuperEntity {

	private static final long serialVersionUID = 7561168602691151990L;
    
    /**
     * 包装类型级别(整单级别/商品行级别)
     */
//    private MqSoPackingInfoLevel piLevel;
    private Integer piLevel;

    /**
     * 包装类型
     */
//    private PackageType type;
    private Integer type;

    /**
     * 备注
     */
    private String memo;
    
    //店铺ID
    private Long shopId;
    
    /**
     * 相关订单
     */
    private Long mqSoLog;
    
    /**
     * 相关订单行
     */
    private Long mqSoLineLog;
    
}
