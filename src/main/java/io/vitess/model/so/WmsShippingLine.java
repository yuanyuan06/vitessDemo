package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;


@TableName("t_wms_shipping_line")
public class WmsShippingLine extends SuperEntity {

	private static final long serialVersionUID = -9116511162106009899L;
	
	/** pk **/
	private Long id;
	/** FK **/
	@TableField("WMS_SHIPPING_ID")
	private Long wmsShippingId;
	/** 平台行号 **/
	@TableField("PLATFORM_LINE_NO")
	private String platformLineNo;
	/** 商品CODE **/
	@TableField("SKU_CODE")
	private String skuCode;
	/** 数量 **/
	@TableField("QTY")
	private Integer qty;

}
