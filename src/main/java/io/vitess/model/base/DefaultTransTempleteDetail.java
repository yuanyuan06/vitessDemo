package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 物流模板明细
 * @author fanht
 *
 */
@Data
//@TableName("t_ma_def_trans_temp_detail")
public class DefaultTransTempleteDetail extends SuperEntity {
    private static final long serialVersionUID = -6405589590181418021L;
    
    /**
     * 省
     */
    private String province;
    /**
     * 开通区域(城市)
     */
    private String reachableArea;
    /**
     * 未开通区域(城市)
     */
    private String unReachableArea;
    /**
     * 物流服务商
     */
    private Transportator transportator;

    private DefaultTransTemplete defTemp;
    
    /**
     * VERSION
     */
    private int version;
}
