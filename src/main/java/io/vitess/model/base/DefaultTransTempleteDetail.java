package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.common.SuperEntity;

/**
 * 物流模板明细
 * @author fanht
 *
 */
@TableName("t_ma_def_trans_temp_detail")
public class DefaultTransTempleteDetail extends SuperEntity {
    private static final long serialVersionUID = -6405589590181418021L;
    
    /**
     * 省
     */
    @TableField("PROVINCE")
    private String province;
    /**
     * 开通区域(城市)
     */
    @TableField("REACHABLE_AREA")
    private String reachableArea;
    /**
     * 未开通区域(城市)
     */
    @TableField("UN_REACHABLE_AREA")
    private String unReachableArea;
    /**
     * 物流服务商
     */
    @TableField("TRANS_ID")
    private Transportator transportator;

    @TableField("TEMP_ID")
    private DefaultTransTemplete defTemp;
    
    /**
     * VERSION
     */
    @Version
    private int version;
}
