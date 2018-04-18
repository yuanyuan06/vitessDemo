package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

/**
 * 商品SN号
 * @date 2015年8月26日 下午6:00:14
 */

@TableName("t_wms_sku_sn")
public class WmsSkuSn extends SuperEntity {
	private static final long serialVersionUID = -2594509333788198082L;
	/** pk **/
	private Long id;
	/** fk [WmsInvLog] **/
	@TableField("WMS_INV_LOG_ID")
	private Long wmsInvLogId;
	/** sn **/
	@TableField("SN")
	private String sn;

}
