package io.vitess.model.base;

import io.vitess.common.SuperEntity;
import lombok.Data;

@Data
//@TableName("t_ma_sku_warehouse_rel")
public class SkuWarehouseRel extends SuperEntity{

	private static final long serialVersionUID = 88114513730634548L;

	private Long shopId;

	private String skuCode;

	private String skuType;

	private String whCode;

}