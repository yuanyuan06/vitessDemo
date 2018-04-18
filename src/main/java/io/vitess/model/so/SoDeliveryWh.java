package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单匹配的过仓顺序
 * @author hailiang.jiang
 * @date 2015年5月14日 下午5:29:26
 */
@Entity
@Table(name = "t_td_so_delivery_wh")
public class SoDeliveryWh extends BaseModel {
    private static final long serialVersionUID = -5447659683465153839L;
    
    public static Integer STATUS_WAIT_DEAL = 0;
    public static Integer STATUS_PROCESSING = 1;
    public static Integer STATUS_FAIL = 5;
    public static Integer STATUS_SUCCESS = 10;
    
    /**
     * ID
     */
    private Long id;
    /**
     * 订单ID
     */
    private Long soId;
    /**
     * 仓库编码
     */
    private String whCode;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 处理状态
     * 0：未处理
     * 1：正在处理
     * 5：处理失败
     * 10：处理成功
     */
    private Integer status;
    /**
     * 处理结果
     */
    private String result;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    
    //店铺ID
    private Long shopId;
    
    public SoDeliveryWh() {
		super();
	}

	public SoDeliveryWh(Long soId, String whCode, Integer priority, Integer status, Date createTime, Long shopId) {
		super();
		this.soId = soId;
		this.whCode = whCode;
		this.priority = priority;
		this.status = status;
		this.createTime = createTime;
		this.shopId = shopId;
	}

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "so_id")
    public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}

	@Column(name = "priority")
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

    @Column(name = "wh_code")
    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    @Column(name = "STATUS")
    public Integer getStatus() {
		return status;
	}
    
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "REMARK")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "RESULT")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "create_time")
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
