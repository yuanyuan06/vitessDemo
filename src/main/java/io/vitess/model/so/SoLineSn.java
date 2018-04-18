package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;

/**
 * 销售出库时，出库SN
 * @author fanht
 *
 */
@Entity
@Table(name = "t_td_so_line_sn")
public class SoLineSn extends BaseModel {
	private static final long serialVersionUID = -8937396455362773350L;
	
	private Long id;
	
	//订单id
	private Long soId;
	
	//订单行id
	private Long soLineId;
	
	//SN
	private String sn;
	
	//状态：0 无状态；1 正常状态；2 冻结；
	private Integer status = 0;
	
    //店铺ID
    private Long shopId;
    
    //是否有子SN
    private Integer isHaveSub = 0;

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

	@Column(name = "STATUS", columnDefinition = "TINYINT")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	
	@Column(name = "IS_HAVE_SUB")
	public Integer getIsHaveSub() {
		return isHaveSub;
	}

	public void setIsHaveSub(Integer isHaveSub) {
		this.isHaveSub = isHaveSub;
	}

}
