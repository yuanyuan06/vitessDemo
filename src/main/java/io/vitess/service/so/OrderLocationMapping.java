package io.vitess.service.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("t_ma_location_mapping")
public class OrderLocationMapping extends SuperEntity{
	private static final long serialVersionUID = -1730519845870860416L;

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("SKU_CODE")
	private String skuCode;

	@TableField("OWNER_CODE")
	private String ownerCode;

	@TableField("LOCATION_CODE")
	private String locationCode;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("BUSINESS_TYPE")
	private Integer businessType;
	
}
