package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

@TableName("t_wms_shipping")
public class WmsShipping extends SuperEntity {

	private static final long serialVersionUID = -9116511162106009899L;

	/** fk **/
	@TableField("WMS_CONFIRM_ORDER_ID")
	private Long wmsConfirmOrderId;
	/** 单据号，Wms单据唯一标识 **/
	@TableField("SHIPING_CODE")
	private String shipingCode;
	/** 单据所在仓库 **/
	@TableField("WH_CODE")
	private String whCode;
	/** 物流商简称 **/
	@TableField("TRANS_CODE")
	private String transCode;
	/** 运单号 **/
	@TableField("TRACKING_NO")
	private String trackingNo;
}
