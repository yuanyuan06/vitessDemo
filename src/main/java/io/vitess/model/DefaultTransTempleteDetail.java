package io.vitess.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YSH4807
 * @date 2018/4/11 11:27
 */
@Data
public class DefaultTransTempleteDetail implements Serializable {


    private Long id;
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
