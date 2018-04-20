package io.vitess.command;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 地区字典表
 * @author cp
 *
 */
@Data
@TableName("t_ma_shop_outlet")
public class ShoOutLetInfo extends SuperEntity{

    private static final long serialVersionUID = 3828432859681673212L;

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("STORE_ID")
	private String storeId;

	@TableField("STORE_CODE")
	private String storeCode;

	@TableField("COMPANY_NAME")
	private String companyName;

	@TableField("STORE_NAME")
	private String storeName;

	@TableField("STORE_TYPE")
	private String storeType;

	@TableField("MAIN_CATEGORY")
	private String mainCategory;

	@TableField("START_TIME")
	private String startTime;

	@TableField("END_TIME")
	private String endTime;

	@TableField("STORE_STATUS")
	private String storeStatus;

	@TableField("STORE_DESCRIPTION")
	private String storeDescription;

	@TableField("CREATE_USER")
	private String createUser;

	@TableField("LAST_MODIFY_USER")
	private String lastModifyUser;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("LAST_MODIFY_TIME")
	private Date lastModifyTime;

	@TableField("REMARK")
	private String remark;
	
}
