package io.vitess.model.so;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 服务安装公司
 * @author fanht
 */
@Data
//@TableName("t_ma_service_install_company")
public class ServiceInstallCompany extends SuperEntity {
    private static final long serialVersionUID = -2375857495487182428L;

	private Long shopId;

	private String province;

	private String city;

	private String district;

	private String companyCode;

	private String companyName;

	private Boolean isEnabled = true;

	private Date createTime = new Date();

	private String creator;
    
}
