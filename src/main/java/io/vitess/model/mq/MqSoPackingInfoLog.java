package io.vitess.model.mq;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

/**
 * 
 * 包装信息
 * 
 */
@TableName("t_mq_so_packing_info_log")
public class MqSoPackingInfoLog extends SuperEntity {

	private static final long serialVersionUID = 7561168602691151990L;
    
    /**
     * 包装类型级别(整单级别/商品行级别)
     */
    @TableField("PI_LEVEL")
//    private MqSoPackingInfoLevel piLevel;
    private Integer piLevel;

    /**
     * 包装类型
     */
    @TableField("TYPE")
//    private PackageType type;
    private Integer type;

    /**
     * 备注
     */
    @TableField("MEMO")
    private String memo;
    
    //店铺ID
    @TableField("SHOP_ID")
    private Long shopId;
    
    /**
     * 相关订单
     */
    @TableField("SO_LOG_ID")
    private Long mqSoLog;
    
    /**
     * 相关订单行
     */
    @TableField("SO_LINE_LOG_ID")
    private Long mqSoLineLog;
    
}
