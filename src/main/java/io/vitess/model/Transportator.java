package io.vitess.model;

import io.vitess.enums.DefaultLifeCycleStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YSH4807
 * @date 2018/4/11 11:28
 */
@Data
public class Transportator implements Serializable {

    public static final String EMS = "EMS";
    public static final String EMS_COD = "EMSCOD";
    public static final String SF = "SF";
    public static final String SFCOD = "SFCOD";
    public static final String ZTKY_CODE = "中铁物流";
    public static final String ZTKY_NAME = "中铁物流";
    public static final String SF_LOGO_IMAGE = "print_img/top.jpg";


    /**
     * PK
     */
    private Long id;

    /**
     * 生命周期
     */
    private DefaultLifeCycleStatus lifeCycleStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * Version
     */
    private Date version;

    /**
     * 物流商名称（平台名称）
     */
    private String name;

    /**
     * 物流商全称
     */
    private String fullName;

    /**
     * K3编码
     */
    private String k3Code;

    /**
     * 内部平台对接编码，全局唯一
     */
    private String expCode;

    /**
     * 外部平台对接编码
     */
    private String platformCode;

    /**
     * 是否支持COD
     */
    private Boolean isSupportCod;
    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    /**
     * 电子面单模板
     */
    private String jasperOnLine;
    /**
     * 普通电子面单
     */
    private String jasperNormal;

    /**
     * 阿里物流商id add by chenping 20170518
     */
    private String platformId;

}
