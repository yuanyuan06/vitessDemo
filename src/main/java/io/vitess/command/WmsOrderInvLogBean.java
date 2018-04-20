package io.vitess.command;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存流水，及其对应的sn好
 * @author hailiang.jiang
 * @date 2015年8月28日 下午4:51:26
 */
public class WmsOrderInvLogBean implements Serializable {

	private static final long serialVersionUID = 3158871680911513204L;

	private Long wmsInvLogId;
	private Long wmsOrderStatusId;
	/** 商品 **/
	private String sku;
	/** 数量 **/
	private Long qty;
	/** 批次号 **/
	private String btachCode;
	/** 商品批次 **/
	private String barchNo;
	/** 库存状态 **/
	private String invStatus;
	/** 仓库编码 **/
	private String warehouceCode;
	/** 库存事务时间 **/
	private Date transactionTime;
	/** 库存渠道 **/
	private String invOwner;
	/** sn **/
	private String sn;
	
    /**
    * 是否可售
    */
    private Boolean marketAbility;

	public Long getWmsInvLogId() {
		return wmsInvLogId;
	}

	public void setWmsInvLogId(Long wmsInvLogId) {
		this.wmsInvLogId = wmsInvLogId;
	}

	public Long getWmsOrderStatusId() {
		return wmsOrderStatusId;
	}

	public void setWmsOrderStatusId(Long wmsOrderStatusId) {
		this.wmsOrderStatusId = wmsOrderStatusId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public String getBtachCode() {
		return btachCode;
	}

	public void setBtachCode(String btachCode) {
		this.btachCode = btachCode;
	}

	public String getBarchNo() {
		return barchNo;
	}

	public void setBarchNo(String barchNo) {
		this.barchNo = barchNo;
	}

	public String getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
	}

	public String getWarehouceCode() {
		return warehouceCode;
	}

	public void setWarehouceCode(String warehouceCode) {
		this.warehouceCode = warehouceCode;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getInvOwner() {
		return invOwner;
	}

	public void setInvOwner(String invOwner) {
		this.invOwner = invOwner;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Boolean getMarketAbility() {
		return marketAbility;
	}

	public void setMarketAbility(Boolean marketAbility) {
		this.marketAbility = marketAbility;
	}

}
