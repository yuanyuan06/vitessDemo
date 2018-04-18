package io.vitess.model.so;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_wms_order_cancel")
public class WmsOrderCancel implements Serializable {

	private static final long serialVersionUID = 4745079578898655197L;
	
	/** pk **/
	private Long id;
	/** 原始数据id（t_receive_wms_log） */
	private Long receiveWmsLogId;
	/** 订单号(唯一对接标识) **/
	private String orderCode;
	/** 状态(保留字段) **/
	private Integer wmsStatus;
	/** 订单类型 **/
	private Integer orderType;
	/** 备注 **/
	private String wmsMemo;
	/** tmalloms处理状态（0：未处理, 10: 已处理） **/
	private Integer dataStatus;
	/** tmalloms处理处理时间 **/
	private Date processTime;
	/** 处理结果 **/
	private String processResult;
	/** 操作备注（如：页面操作、系统自动操作[如取消待确认节点,需要根据指令程序自动取消]） **/
	private String optDesc;
	/** 放入时间 **/
	private Date inputTime;
	/** 明细行 **/
	private List<WmsOrderCancelDetail> wmsOrderCancelDetailList;

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="RECEIVE_WMS_LOG_ID")
	public Long getReceiveWmsLogId() {
		return receiveWmsLogId;
	}

	public void setReceiveWmsLogId(Long receiveWmsLogId) {
		this.receiveWmsLogId = receiveWmsLogId;
	}

	@Column(name="ORDER_CODE")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Column(name="WMS_STATUS")
	public Integer getWmsStatus() {
		return wmsStatus;
	}

	public void setWmsStatus(Integer wmsStatus) {
		this.wmsStatus = wmsStatus;
	}

	@Column(name="ORDER_TYPE")
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@Column(name="WMS_MEMO")
	public String getWmsMemo() {
		return wmsMemo;
	}

	public void setWmsMemo(String wmsMemo) {
		this.wmsMemo = wmsMemo;
	}

	@Column(name="DATA_STATUS")
	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	@Column(name="PROCESS_TIME")
	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	@Column(name="PROCESS_RESULT")
	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	@Column(name="OPT_DESC")
	public String getOptDesc() {
		return optDesc;
	}

	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}

	@Column(name="INPUT_TIME")
	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "wmsOrderCancel", orphanRemoval = true)
	public List<WmsOrderCancelDetail> getWmsOrderCancelDetailList() {
		return wmsOrderCancelDetailList;
	}

	public void setWmsOrderCancelDetailList(List<WmsOrderCancelDetail> wmsOrderCancelDetailList) {
		this.wmsOrderCancelDetailList = wmsOrderCancelDetailList;
	}

}
