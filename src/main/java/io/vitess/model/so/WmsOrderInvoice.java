package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

import java.math.BigDecimal;

/**
 * 发票
 * @date 2015年8月26日 下午5:59:32
 */

@TableName("t_wms_order_invoice")
public class WmsOrderInvoice extends SuperEntity {

	private static final long serialVersionUID = 4745079578898655197L;
	@TableField("WMS_ORDER_STATUS_ID")
	private Long wmsOrderStatusId;

	@TableField("INVOICE_DATE")
	private String invoiceDate;

	@TableField("PAYER")
	private String payer;

	@TableField("INVOICE_CODE")
	private String invoiceCode;

	@TableField("WMS_INVOICE_CODE")
	private String wmsInvoiceCode;

	@TableField("ITEM")
	private String item;

	@TableField("INVOICE_NO")
	private String invoiceNo;

	@TableField("QTY")
	private Long qty;

	@TableField("UNIT_PRICE")
	private BigDecimal unitPrice;

	@TableField("AMT")
	private BigDecimal amt;

	@TableField("MEMO")
	private String memo;

	@TableField("PAYEE")
	private String payee;

	@TableField("COMPANY")
	private String company;

	@TableField("DRAWER")
	private String drawer;

}
