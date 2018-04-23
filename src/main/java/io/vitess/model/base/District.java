package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 地区信息
 * 
 * @author fanht
 * 
 */
@Data
@TableName("t_sys_area")
public class District extends SuperEntity {
    private static final long serialVersionUID = -8746487809043590643L;
    
    public static final String COUNTRY_CN = "中国"; // 中国

    @TableField("COUNTRY")
    private String country = COUNTRY_CN;

    @TableField("PROVINCE")
    private String province;

    @TableField("CITY")
    private String city;

    @TableField("DISTRICT")
    private String district;

    @TableField("SFCODE")
    private String sfCode;

}
