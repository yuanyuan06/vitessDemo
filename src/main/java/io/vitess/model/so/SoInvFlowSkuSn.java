package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;


@TableName("t_td_so_inv_flow_sku_sn")
public class SoInvFlowSkuSn extends SuperEntity {

	private static final long serialVersionUID = 576311060480489598L;
	@TableField("SO_INVENTORY_FLOW_ID")
	private Long soInventoryFlowId;

	@TableField("SN")
	private String sn;

	@TableField("SHOP_ID")
	private Long shopId;


}
