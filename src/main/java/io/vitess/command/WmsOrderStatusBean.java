package io.vitess.command;

import java.io.Serializable;
import java.util.List;

/**
 * wms执行反馈：订单和物流信息（1-->*）
 * 
 * @date 2015年8月27日 下午5:40:20
 */
public class WmsOrderStatusBean implements Serializable {

	private static final long serialVersionUID = 7439698566524374741L;

	private String orderCode;
	private String shippingCode;
	private Integer wmsStatus;
	private Integer orderType;
	/******一个订单有多个物流时，订单随意匹配一个物流信息*****/
	/** 物流商简称 **/
	private String transCode;
	/** 运单号 **/
	private String transNo;
	/** 物流时效 **/
	private Integer transTimeType;
	//店铺ID
    private Long shopId;
	/** 包装发票信息 **/
	private List<WmsOrderInvoiceBean> orderInvoiceList;
	/** 库存流水信息 **/
	private List<WmsOrderInvLogBean> orderInvLogList;
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public Integer getWmsStatus() {
		return wmsStatus;
	}

	public void setWmsStatus(Integer wmsStatus) {
		this.wmsStatus = wmsStatus;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public Integer getTransTimeType() {
		return transTimeType;
	}

	public void setTransTimeType(Integer transTimeType) {
		this.transTimeType = transTimeType;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<WmsOrderInvoiceBean> getOrderInvoiceList() {
		return orderInvoiceList;
	}

	public void setOrderInvoiceList(List<WmsOrderInvoiceBean> orderInvoiceList) {
		this.orderInvoiceList = orderInvoiceList;
	}

	public List<WmsOrderInvLogBean> getOrderInvLogList() {
		return orderInvLogList;
	}

	public void setOrderInvLogList(List<WmsOrderInvLogBean> orderInvLogList) {
		this.orderInvLogList = orderInvLogList;
	}

}
