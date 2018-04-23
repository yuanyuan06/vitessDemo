package io.vitess.model.base;

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
//@TableName("t_ma_def_trans_template")
public class DefaultTransTemplete extends SuperEntity{

    private static final long serialVersionUID = 8896970291216745685L;

    private String code;

    private String name;

    private String remark;

    private Date lastModifyTime;

    private String lastModifyUser;

    /**
     * 是否排除EMS匹配逻辑
     */
    private Boolean isExcludeEMS;
    
    /**
     * COD默认物流服务商
     */
    private Transportator codTrans;
    
    /**
     * VERSION
     */
    private Integer version;


    /**
     * 相关明细
     */
    private List<DefaultTransTempleteDetail> tempDetails;
    
}
