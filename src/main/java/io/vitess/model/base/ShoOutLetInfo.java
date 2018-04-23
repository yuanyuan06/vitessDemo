package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 地区字典表
 * @author cp
 *
 */
@Data
//@TableName("t_ma_shop_outlet")
public class ShoOutLetInfo extends SuperEntity{

    private static final long serialVersionUID = 3828432859681673212L;


	private Long shopId;


	private String storeId;


	private String storeCode;


	private String companyName;


	private String storeName;


	private String storeType;


	private String mainCategory;


	private String startTime;


	private String endTime;


	private String storeStatus;


	private String storeDescription;


	private String createUser;


	private String lastModifyUser;


	private Date createTime;


	private Date lastModifyTime;


	private String remark;
	
}
