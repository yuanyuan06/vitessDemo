package io.vitess.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

@Data
@TableName("t_ma_sku_warehouse_rel")
public class SkuWarehouseRel extends SuperEntity{

	private static final long serialVersionUID = 88114513730634548L;

	@TableField("SHOP_ID")
	private Long shopId;

	@TableField("SKU_CODE")
	private String skuCode;

	@TableField("SKU_TYPE")
	private String skuType;

	@TableField("WH_CODE")
	private String whCode;

}