package io.vitess.model.so;

import com.jumbo.model.BaseModel;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

/**
 * T_SO_CREATE_LOG_LINE
 * 
 * @author wudan
 * 
 */
@Entity
@Table(name = "t_td_create_log_line")
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION)
public class TradeCreateLogLine extends BaseModel{

	private static final long serialVersionUID = -8995584493628211496L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * version
	 */
	private int version;

	/**
	 * 备注/失败原因
	 */
	private String remark;

	/**
	 * 外部订单号/关联编码
	 */
	private String platformOrderCode;

	/** 交易ID */
	private Trade trade;

	/**
	 * 外部订单号/关联编码
	 */
	private Boolean isSuccessed;

	private TradeCreateLog tdCreateLog;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion(){
		return version;
	}

	public void setVersion(int version){
		this.version = version;
	}

	@Column(name = "REMARK")
	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	@Column(name = "PLATFORM_ORDER_CODE")
	public String getPlatformOrderCode(){
		return platformOrderCode;
	}

	public void setPlatformOrderCode(String platformOrderCode){
		this.platformOrderCode = platformOrderCode;
	}

	@Column(name = "IS_SUCCESSED")
	public Boolean getIsSuccessed(){
		return isSuccessed;
	}

	public void setIsSuccessed(Boolean isSuccessed){
		this.isSuccessed = isSuccessed;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TD_CREATELOG_ID")
	public TradeCreateLog getTdCreateLog(){
		return tdCreateLog;
	}

	public void setTdCreateLog(TradeCreateLog tdCreateLog){
		this.tdCreateLog = tdCreateLog;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRADE_ID")
	public Trade getTrade(){
		return trade;
	}

	public void setTrade(Trade trade){
		this.trade = trade;
	}

}
