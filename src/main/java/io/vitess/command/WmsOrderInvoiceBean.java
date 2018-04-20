package io.vitess.command;

import java.io.Serializable;
import java.math.BigDecimal;

public class WmsOrderInvoiceBean implements Serializable {
	private static final long serialVersionUID = -3477334534458237323L;
	
	private Long wmsOrderInvoiceId;
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
	private String dtItem; // 商品
	private Long dtQty; // 数量
	private BigDecimal dtUnitPrice; // 单价
	private BigDecimal dtAmt; // 总金额
	private String dtLineNo; // 平台行号

	public Long getWmsOrderInvoiceId() {
		return wmsOrderInvoiceId;
	}

	public void setWmsOrderInvoiceId(Long wmsOrderInvoiceId) {
		this.wmsOrderInvoiceId = wmsOrderInvoiceId;
	}

	public Long getWmsOrderStatusId() {
		return wmsOrderStatusId;
	}

	public void setWmsOrderStatusId(Long wmsOrderStatusId) {
		this.wmsOrderStatusId = wmsOrderStatusId;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getWmsInvoiceCode() {
		return wmsInvoiceCode;
	}

	public void setWmsInvoiceCode(String wmsInvoiceCode) {
		this.wmsInvoiceCode = wmsInvoiceCode;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	public String getDtItem() {
		return dtItem;
	}

	public void setDtItem(String dtItem) {
		this.dtItem = dtItem;
	}

	public Long getDtQty() {
		return dtQty;
	}

	public void setDtQty(Long dtQty) {
		this.dtQty = dtQty;
	}

	public BigDecimal getDtUnitPrice() {
		return dtUnitPrice;
	}

	public void setDtUnitPrice(BigDecimal dtUnitPrice) {
		this.dtUnitPrice = dtUnitPrice;
	}

	public BigDecimal getDtAmt() {
		return dtAmt;
	}

	public void setDtAmt(BigDecimal dtAmt) {
		this.dtAmt = dtAmt;
	}

	public String getDtLineNo() {
		return dtLineNo;
	}

	public void setDtLineNo(String dtLineNo) {
		this.dtLineNo = dtLineNo;
	}

}
