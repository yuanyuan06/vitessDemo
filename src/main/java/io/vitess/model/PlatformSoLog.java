package io.vitess.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YSH4807
 * @date 2018/4/11 10:58
 */

@Data
public class PlatformSoLog implements Serializable {

    private Long id;

    private String code;

    private Long shopId;

    private Long mqSoLogId;

    private String sourceMsg;

}
