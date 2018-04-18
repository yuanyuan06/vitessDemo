package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 库存流水
 * @date 2015年8月28日 上午10:58:15
 */
@Entity
@Table(name = "t_td_so_inv_flow")
public class SoInvFlow extends BaseModel {

	private static final long serialVersionUID = 1L;
	/** pk **/
	private Long id;
	/** fk **/
	private Long soId;
	/** 商品 **/
	private String skuCode;
	/** 数量 **/
	private Long qty;
	/** 批次号 **/
	private String btachCode;
	/** 商品批次 **/
	private String barchNo;
	/** 库存状态 **/
	private String invStatus;
	/** 仓库编码 **/
	private String whCode;
	/** 库存事务时间 **/
	private Date transactionTime;
	/** 库存渠道 **/
	private String invOwner;
	/** 数据放入时间 **/
	private Date inputTime;
	/** 类型 @see com.jumbo.model.sales.enums.SlipType **/
	private Integer dataType;
	
    /**
    * 是否可售
    */
    private Boolean marketAbility;
	
	private List<SoInvFlowSkuSn> skuSnList;
	
    //店铺ID
    private Long shopId;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="SO_ID")
	public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}

	@Column(name = "SKU_CODE")
	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	@Column(name = "WH_CODE")
	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
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

	@Column(name = "INPUT_TIME")
	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	
	@Column(name = "DATA_TYPE")
	public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Transient
	public List<SoInvFlowSkuSn> getSkuSnList() {
		return skuSnList;
	}

	public void setSkuSnList(List<SoInvFlowSkuSn> skuSnList) {
		this.skuSnList = skuSnList;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	
	@Column(name = "MARKET_ABILITY")
	public Boolean getMarketAbility() {
		return marketAbility;
	}

	public void setMarketAbility(Boolean marketAbility) {
		this.marketAbility = marketAbility;
	}

}
