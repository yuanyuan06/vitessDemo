package io.vitess.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 缓存订单
 * @author hailiang.jiang
 * @date 2014年7月22日
 * @description SessionCacheSalesOrderVo
 */
public class SessionCacheSalesOrderVo implements Serializable {
	
	private static final long serialVersionUID = -6913484323022994312L;

	/**
	 * 【原始订单】OMS订单号
	 */
	private String omsOrderCode;
	
	/**
	 * 【原始订单】平台子订单
	 */
	private String platformOrderCodeN;
	
	/**
	 * 页面每次请求的标识
	 */
	private String timestampToken;
	
	/**
	 * 子订单订单明细行ID
	 */
	private List<Long> salesOrderLineIdList;
	
	/**
	 * 【新订单】子订单名称
	 */
	private String childOrderCodeTitle;
	
	/**
	 * 订单总金额
	 */
	private BigDecimal salesOrderTotalFee;
	
	/**
	 * 订单商品数量
	 */
	private Integer skuQty;
	
	/**
	 * 子订单
	 */
	private SalesOrderCommand childSalesOrder ;
	
	public String getOmsOrderCode() {
		return omsOrderCode;
	}

	public void setOmsOrderCode(String omsOrderCode) {
		this.omsOrderCode = omsOrderCode;
	}

	public String getPlatformOrderCodeN() {
		return platformOrderCodeN;
	}

	public void setPlatformOrderCodeN(String platformOrderCodeN) {
		this.platformOrderCodeN = platformOrderCodeN;
	}

	public String getTimestampToken() {
		return timestampToken;
	}

	public void setTimestampToken(String timestampToken) {
		this.timestampToken = timestampToken;
	}

	public List<Long> getSalesOrderLineIdList() {
		return salesOrderLineIdList;
	}

	public void setSalesOrderLineIdList(List<Long> salesOrderLineIdList) {
		this.salesOrderLineIdList = salesOrderLineIdList;
	}

	public String getChildOrderCodeTitle() {
		return childOrderCodeTitle;
	}

	public void setChildOrderCodeTitle(String childOrderCodeTitle) {
		this.childOrderCodeTitle = childOrderCodeTitle;
	}

	public BigDecimal getSalesOrderTotalFee() {
		return salesOrderTotalFee;
	}

	public void setSalesOrderTotalFee(BigDecimal salesOrderTotalFee) {
		this.salesOrderTotalFee = salesOrderTotalFee;
	}

	public Integer getSkuQty() {
		return skuQty;
	}

	public void setSkuQty(Integer skuQty) {
		this.skuQty = skuQty;
	}

	public SalesOrderCommand getChildSalesOrder() {
		return childSalesOrder;
	}

	public void setChildSalesOrder(SalesOrderCommand childSalesOrder) {
		this.childSalesOrder = childSalesOrder;
	}

}
