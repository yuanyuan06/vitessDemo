package io.vitess.service.so;



import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;

/**
 * 创单时根据skuCode和业务类型查找对应的发货库位
 * 业务类型如预售
 * @author xin.feng
 *
 */
@Data
//@TableName("t_ma_location_mapping")
public class OrderLocationMapping extends SuperEntity{
	private static final long serialVersionUID = -1730519845870860416L;


	private Long shopId;


	private String skuCode;


	private String ownerCode;


	private String locationCode;


	private Date createTime;


	private Integer businessType;
	
}
