package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 物流模板
 * @author fanht
 *
 */
@Data
@TableName("t_ma_def_trans_template")
public class DefaultTransTemplete extends SuperEntity{

    private static final long serialVersionUID = 8896970291216745685L;

    @TableField("CODE")
    private String code;

    @TableField("NAME")
    private String name;

    @TableField("REMARK")
    private String remark;

    @TableField("LAST_MODIFY_TIME")
    private Date lastModifyTime;

    @TableField("LAST_MODIFY_USER")
    private String lastModifyUser;

    /**
     * 是否排除EMS匹配逻辑
     */
    @TableField("IS_EXCLUDE_EMS")
    private Boolean isExcludeEMS;
    
    /**
     * COD默认物流服务商
     */
    @TableField("COD_TRANS_ID")
    private Transportator codTrans;
    
    /**
     * VERSION
     */
    @Version
    private Integer version;


    /**
     * 相关明细
     */
    private List<DefaultTransTempleteDetail> tempDetails;
    
}
