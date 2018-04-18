package io.vitess.model.so;

import com.jumbo.model.BaseModel;
import com.jumbo.model.TaskProcessStatus;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * 预约日期提交至仓库-任务列表
 * 
 * @className com.jumbo.model.sales.SoAppointmentToWhTask
 * @author hailiang.jiang
 * @date 2014年12月18日 下午2:54:09
 */
@Entity
@Table(name = "t_td_so_appointment_to_wh_task")
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION, dynamicUpdate=true)
public class SoAppointmentToWhTask extends BaseModel {

	private static final long serialVersionUID = 642285886307395903L;
	
	/** PK **/
    private Long id;
    
    /** VERSION **/
    private int version;
    
    /** 关联订单ID **/
    private Long salesOrderId;
    
    /** 订单预约前默认过仓的仓库,最原始的仓库编码 **/
    private String fromWhCode;
    
    /** 计划提交过仓的仓库 **/
    private String toWhCode;
    
    /** 计划过仓时间 **/
    private Date toWhTime;
    
    /** 预约过仓时间到达后是否直接过仓 */
    private Boolean isAutoTo;
    
    /** 处理状态 **/
    private TaskProcessStatus processStatus;
    
    /** 处理结果 **/
    private String processResult;
    
    /**  备注  **/
    private String remark;
    
    /**  创建时间  **/
    private Date createTime;
    
    /**  创建用户  **/
    private String createUserNo;
    
    /**  更新时间  **/
    private Date updateTime;
    
    /**  更新用户  **/
    private String updateUserNo;
    
    //店铺ID
    private Long shopId;

    @Id
    @Column(name="ID", nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
    @Column(name="VERSION", nullable=false)
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name="SALES_ORDER_ID", nullable=false)
	public Long getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(Long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	@Column(name="FROM_WH_CODE", nullable=false)
	public String getFromWhCode() {
		return fromWhCode;
	}

	public void setFromWhCode(String fromWhCode) {
		this.fromWhCode = fromWhCode;
	}

	@Column(name="TO_WH_CODE", nullable=false)
	public String getToWhCode() {
		return toWhCode;
	}

	public void setToWhCode(String toWhCode) {
		this.toWhCode = toWhCode;
	}

	@Column(name="TO_WH_TIME", nullable=false)
	public Date getToWhTime() {
		return toWhTime;
	}

	public void setToWhTime(Date toWhTime) {
		this.toWhTime = toWhTime;
	}

	@Column(name="IS_AUTO_TO", nullable=false)
	public Boolean getIsAutoTo() {
        return isAutoTo;
    }

    public void setIsAutoTo(Boolean isAutoTo) {
        this.isAutoTo = isAutoTo;
    }

    @Column(name="PROCESS_STATUS", columnDefinition="integer", nullable=false)
    @Type(type="loxia.dao.support.GenericEnumUserType", parameters={@Parameter(name="enumClass", value="com.jumbo.model.TaskProcessStatus")})
	public TaskProcessStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(TaskProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	@Column(name="PROCESS_RESULT", nullable=true)
	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	@Column(name="REMARK", nullable=true)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="CREATE_TIME", nullable=false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="CREATE_USER_NO", nullable=false)
	public String getCreateUserNo() {
		return createUserNo;
	}

	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo;
	}

	@Column(name="UPDATE_TIME", nullable=true)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name="UPDATE_USER_NO", nullable=true)
	public String getUpdateUserNo() {
		return updateUserNo;
	}

	public void setUpdateUserNo(String updateUserNo) {
		this.updateUserNo = updateUserNo;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
    
}
