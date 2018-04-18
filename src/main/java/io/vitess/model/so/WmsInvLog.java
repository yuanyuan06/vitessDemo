package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 库存流水
 * @date 2015年8月26日 下午5:59:06
 */
@Entity
@Table(name = "t_wms_inv_log")
public class WmsInvLog extends BaseModel {

	private static final long serialVersionUID = 4745079578898655197L;

	/** pk **/
	private Long id;
	/** fk **/
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
	
    /**
    * 是否可售
    */
    private Boolean marketAbility;
    
	/** Sn号 **/
	private List<WmsSkuSn> skuSnList;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(name = "SKU")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Column(name = "QTY")
	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	@Column(name = "BTACH_CODE")
	public String getBtachCode() {
		return btachCode;
	}

	public void setBtachCode(String btachCode) {
		this.btachCode = btachCode;
	}

	@Column(name = "BARCH_NO")
	public String getBarchNo() {
		return barchNo;
	}

	public void setBarchNo(String barchNo) {
		this.barchNo = barchNo;
	}

	@Column(name = "INV_STATUS")
	public String getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
	}

	@Column(name = "WAREHOUCE_CODE")
	public String getWarehouceCode() {
		return warehouceCode;
	}

	public void setWarehouceCode(String warehouceCode) {
		this.warehouceCode = warehouceCode;
	}

	@Column(name = "TRANSACTION_TIME")
	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	@Column(name = "INV_OWNER")
	public String getInvOwner() {
		return invOwner;
	}

	public void setInvOwner(String invOwner) {
		this.invOwner = invOwner;
	}
	
	@Column(name = "MARKET_ABILITY")
	public Boolean getMarketAbility() {
		return marketAbility;
	}

	public void setMarketAbility(Boolean marketAbility) {
		this.marketAbility = marketAbility;
	}

	@Transient
	public List<WmsSkuSn> getSkuSnList() {
		return skuSnList;
	}

	public void setSkuSnList(List<WmsSkuSn> skuSnList) {
		this.skuSnList = skuSnList;
	}

}
