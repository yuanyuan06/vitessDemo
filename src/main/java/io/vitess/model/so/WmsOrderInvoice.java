package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableName;
import com.jumbo.model.BaseModel;
import io.vitess.common.SuperEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 发票
 * @date 2015年8月26日 下午5:59:32
 */

@TableName("t_wms_order_invoice")
public class WmsOrderInvoice extends SuperEntity {

	private static final long serialVersionUID = 4745079578898655197L;
	
	/** pk **/
	private Long id;
	/** fk **/
	private Long wmsOrderStatusId;
	/** 开票日期 **/
	private String invoiceDate;
	/** 发票抬头 **/
	private String payer;
	/** 发票编码 **/
	private String invoiceCode;
	/** 仓库发票编码 **/
	private String wmsInvoiceCode;
	/** 商品 **/
	private String item;
	/** 发票号 **/
	private String invoiceNo;
	/** 数量 **/
	private Long qty;
	/** 单价 **/
	private BigDecimal unitPrice;
	/** 总金额 **/
	private BigDecimal amt;
	/** 发票备注 **/
	private String memo;
	/** 收款人 **/
	private String payee;
	/** 公司 **/
	private String company;
	/** 开票人 **/
	private String drawer;
	
	@Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="WMS_ORDER_STATUS_ID")
	public Long getWmsOrderStatusId() {
		return wmsOrderStatusId;
	}

	public void setWmsOrderStatusId(Long wmsOrderStatusId) {
		this.wmsOrderStatusId = wmsOrderStatusId;
	}
	@Column(name="INVOICE_DATE")
	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Column(name="PAYER")
	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	@Column(name="INVOICE_CODE")
	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	@Column(name="WMS_INVOICE_CODE")
	public String getWmsInvoiceCode() {
		return wmsInvoiceCode;
	}

	public void setWmsInvoiceCode(String wmsInvoiceCode) {
		this.wmsInvoiceCode = wmsInvoiceCode;
	}

	@Column(name="ITEM")
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	@Column(name="INVOICE_NO")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name="QTY")
	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	@Column(name="UNIT_PRICE")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name="AMT")
	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	@Column(name="MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name="PAYEE")
	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	@Column(name="COMPANY")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name="DRAWER")
	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

}
