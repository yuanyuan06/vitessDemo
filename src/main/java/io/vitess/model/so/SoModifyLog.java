package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_so_modify_log")
public class SoModifyLog extends BaseModel {
	private static final long serialVersionUID = -3957366275486503394L;
	
	private Long id;
	
	private String orderCode;
	
	private String content;
	
    private Date createTime = new Date();
    
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

    @Column(name = "ORDER_CODE")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
    
}
