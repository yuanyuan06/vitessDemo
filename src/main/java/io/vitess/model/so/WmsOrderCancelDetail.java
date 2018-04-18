package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "t_wms_order_cancel_detail")
public class WmsOrderCancelDetail extends BaseModel {

	private static final long serialVersionUID = 4745079578898655197L;

	/** pk **/
	private Long id;
	/** 单据号，Wms单据唯一标识 **/
	private String shippingCode;
	/** 状态编码 **/
	private String statusCode;
	/** 备注 **/
	private String memo;
	/** 处理状态(0:未处理，5:处理失败，10:处理完毕) **/
	private Integer processStatus = 0;
	private String processResult = "未处理";
	
	private WmsOrderCancel wmsOrderCancel;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SHIPPING_CODE")
	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	@Column(name = "STATUS_CODE")
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name="PROCESS_STATUS")
	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	@Column(name="PROCESS_RESULT")
	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WMS_ORDER_CANCEL_ID")
	public WmsOrderCancel getWmsOrderCancel() {
		return wmsOrderCancel;
	}

	public void setWmsOrderCancel(WmsOrderCancel wmsOrderCancel) {
		this.wmsOrderCancel = wmsOrderCancel;
	}

}
