package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

/**
 * 地区信息
 * 
 * @author fanht
 * 
 */
@Data
//@TableName("t_sys_area")
public class District extends SuperEntity {
    private static final long serialVersionUID = -8746487809043590643L;
    
    public static final String COUNTRY_CN = "中国"; // 中国

    private String country = COUNTRY_CN;

    private String province;

    private String city;

    private String district;

    private String sfCode;

}
