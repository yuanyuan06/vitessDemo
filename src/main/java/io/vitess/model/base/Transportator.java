package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import io.vitess.common.SuperEntity;

import java.util.Date;

/**
 * 
 * 物流商
 * 
 * @author sjk
 * 
 */
@TableName("t_ma_transportator")
public class Transportator extends SuperEntity {
    private static final long serialVersionUID = -7812676025062855078L;

    public static final String EMS = "EMS";
    public static final String EMS_COD = "EMSCOD";
    public static final String SF = "SF";
    public static final String SFCOD = "SFCOD";
    public static final String ZTKY_CODE = "中铁物流";
    public static final String ZTKY_NAME = "中铁物流";
    public static final String SF_LOGO_IMAGE = "print_img/top.jpg";

    /**
     * 生命周期
     */
//    private DefaultLifeCycleStatus lifeCycleStatus;
    @TableField("LIFE_CYCLE_STATUS")
    private Integer lifeCycleStatus;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * Version
     */
    @Version
    private Date version;

    /**
     * 物流商名称（平台名称）
     */
    @TableField("NAME")
    private String name;

    /**
     * 物流商全称
     */
    @TableField("FULL_NAME")
    private String fullName;

    /**
     * K3编码
     */
    @TableField("K3_CODE")
    private String k3Code;

    /**
     * 内部平台对接编码，全局唯一
     */
    @TableField("EXP_CODE")
    private String expCode;

    /**
     * 外部平台对接编码
     */
    @TableField("PLATFORM_CODE")
    private String platformCode;

    /**
     * 是否支持COD
     */
    @TableField("IS_SUPPORT_COD")
    private Boolean isSupportCod;
    /**
     * 最后修改时间
     */
    @TableField("LAST_MODIFY_TIME")
    private Date lastModifyTime;

    /**
     * 电子面单模板
     */
    @TableField("JASPER_ONLINE")
    private String jasperOnLine;
    /**
     * 普通电子面单
     */
    @TableField("JASPER_NORMAL")
    private String jasperNormal;
    
    /**
     * 阿里物流商id add by chenping 20170518
     */
    @TableField("PLATFORM_ID")
    private String platformId;
    
}
