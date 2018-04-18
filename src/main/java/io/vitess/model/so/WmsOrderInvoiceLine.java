package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.math.BigDecimal;

/**
 * 发票明细
 * @date 2015年8月26日 下午5:59:53
 */

@TableName("t_wms_order_invoice_line")
public class WmsOrderInvoiceLine extends SuperEntity {

	private static final long serialVersionUID = 4745079578898655197L;

	/** pk **/
	@TableField("WMS_ORDER_INVOICE_ID")
	private Long wmsOrderInvoiceId;
	@TableField("ITEM")
	private String item; // 商品
	@TableField("QTY")
	private Long qty; // 数量
	@TableField("UNIT_PRICE")
	private BigDecimal unitPrice; // 单价
	@TableField("AMT")
	private BigDecimal amt; // 总金额
	@TableField("LINE_NO")
	private String lineNo; // 平台行号

}
