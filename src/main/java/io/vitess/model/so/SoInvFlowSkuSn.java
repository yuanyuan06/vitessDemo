package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "t_td_so_inv_flow_sku_sn")
public class SoInvFlowSkuSn extends BaseModel {

	private static final long serialVersionUID = 576311060480489598L;
	/** pk **/
	private Long id;
	/** fk **/
	private Long soInventoryFlowId;
	/** 商品 **/
	private String sn;
	
    //店铺ID
    private Long shopId;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "SO_INVENTORY_FLOW_ID")
	public Long getSoInventoryFlowId() {
		return soInventoryFlowId;
	}

	@Column(name = "SN")
	public String getSn() {
		return sn;
	}

	public void setSoInventoryFlowId(Long soInventoryFlowId) {
		this.soInventoryFlowId = soInventoryFlowId;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
