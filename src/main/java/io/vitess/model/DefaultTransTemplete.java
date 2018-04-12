package io.vitess.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author YSH4807
 * @date 2018/4/11 11:27
 */
@Data
public class DefaultTransTemplete implements Serializable {


    private Long id;
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
     * 相关明细
     */
    private List<DefaultTransTempleteDetail> tempDetails;

    /**
     * COD默认物流服务商
     */
    private Transportator codTrans;

    /**
     * VERSION
     */
    private Integer version;
}
