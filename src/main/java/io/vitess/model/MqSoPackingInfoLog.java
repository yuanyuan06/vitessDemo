package io.vitess.model;

import io.vitess.enums.MqSoPackingInfoLevel;
import io.vitess.enums.PackageType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @author YSH4807
 * @date 2018/4/11 11:05
 */

@Data
public class MqSoPackingInfoLog implements Serializable {

    /**
     * ID
     */
    private Long id;

    private String memo;
    /** piLevel */
    private Integer piLevel;
    /** type */
    private Integer type;
    /** soLineLogId */
    private Long soLineLogId;
    /** soLogId */
    private Long soLogId;
    /** 店铺id */
    private Long shopId;
}
