package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 服务安装公司
 * @author fanht
 */
@Data
@TableName("t_ma_service_install_company")
public class ServiceInstallCompany extends SuperEntity {
    private static final long serialVersionUID = -2375857495487182428L;

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("PROVINCE")
	private String province;

	@TableField("CITY")
	private String city;

	@TableField("DISTRICT")
	private String district;

	@TableField("COMPANY_CODE")
	private String companyCode;

	@TableField("COMPANY_NAME")
	private String companyName;

	@TableField("IS_ENABLED")
	private Boolean isEnabled = true;

	@TableField("CREATE_TIME")
	private Date createTime = new Date();

	@TableField("CREATOR")
	private String creator;
    
}
