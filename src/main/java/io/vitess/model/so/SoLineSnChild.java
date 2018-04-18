package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;

/**
 * 销售出库时，商品类型为N合一时，出库SN对应的子SN
 * @author fanht
 *
 */
@Entity
@Table(name = "t_td_so_line_sn_child")
public class SoLineSnChild extends BaseModel {
	
	private static final long serialVersionUID = 1829711040114822775L;

	private Long id;
	
	//订单id
	private Long soId;
	
	//订单行id
	private Long soLineId;
	
	//SN
	private String sn;
	
	//PARENT_SN
	private String parentSn;
	
	//SO_LINE_SN_ID
	private Long soLineSnId;
	
	//状态：0 无状态；1 正常状态；2 冻结；
	private Integer status = 0;
	
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

	@Column(name = "SO_ID")
	public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}

	@Column(name = "SO_LINE_ID")
	public Long getSoLineId() {
		return soLineId;
	}

	public void setSoLineId(Long soLineId) {
		this.soLineId = soLineId;
	}

	@Column(name = "SN", length = 50)
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
	@Column(name = "PARENT_SN", length = 50)
	public String getParentSn() {
		return parentSn;
	}

	public void setParentSn(String parentSn) {
		this.parentSn = parentSn;
	}
	
	@Column(name = "SO_LINE_SN_ID")
	public Long getSoLineSnId() {
		return soLineSnId;
	}

	public void setSoLineSnId(Long soLineSnId) {
		this.soLineSnId = soLineSnId;
	}
	
	@Column(name = "STATUS", columnDefinition = "TINYINT")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "SHOP_ID",updatable=false,nullable= false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}




}
