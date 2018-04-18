package io.vitess.model.so;

import com.jumbo.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_wms_confirm_order")
public class WmsConfirmOrder extends BaseModel {

	private static final long serialVersionUID = -9116511162106009899L;
	
	/** pk **/
	private Long id;
	/** 原始数据id（t_receive_wms_log） */
	private Long receiveWmsLogId;
	/** 订单号(唯一对接标识) **/
	private String platformOrderCodeN;
	/** 订单所属 **/
	private String owner;
	/** 订单类型（21:普通销售订单，23：oto订单（送货至门店订单），31：qs订单（nike使用），40：换货订单，41：退货入库订单，42：换货出库订单） **/
	private Integer orderType;
	/** 是否拆单 **/
	private Boolean isSplitOrder;
	/** WMS接收处理状态（0:接收失败, 10:处理成功, 8001:处理失败-系统异常, 8100-8199:处理失败-验证失败***, 8200-82**:处理失败-库存不足, 8300-83**:处理失败-仓库物流无法配送） **/
	private Integer wmsStatus;
	/**WMS接收处理备注  **/
	private String wmsMemo;
	/** tmalloms处理状态（0：未处理, 10: 处理成功，5：处理失败） **/
	private Integer processStatus = 0;
	/** tmalloms处理数据结果 **/
	private String processResult;
	/** tmalloms处理处理时间 **/
	private Date processTime;
	/** 放入时间 **/
	private Date inputTime;
	
    //店铺ID
    private Long shopId;

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

	@Column(name="PLATFORM_ORDER_CODE_N")
	public String getPlatformOrderCodeN() {
		return platformOrderCodeN;
	}

	public void setPlatformOrderCodeN(String platformOrderCodeN) {
		this.platformOrderCodeN = platformOrderCodeN;
	}

	@Column(name="OWNER")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name="ORDER_TYPE")
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@Column(name="IS_SPLIT_ORDER")
	public Boolean getIsSplitOrder() {
		return isSplitOrder;
	}

	public void setIsSplitOrder(Boolean isSplitOrder) {
		this.isSplitOrder = isSplitOrder;
	}

	@Column(name="WMS_STATUS")
	public Integer getWmsStatus() {
		return wmsStatus;
	}

	public void setWmsStatus(Integer wmsStatus) {
		this.wmsStatus = wmsStatus;
	}

	@Column(name="WMS_MEMO")
	public String getWmsMemo() {
		return wmsMemo;
	}

	public void setWmsMemo(String wmsMemo) {
		this.wmsMemo = wmsMemo;
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

	@Column(name="PROCESS_TIME")
	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	@Column(name="INPUT_TIME")
	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	
	@Column(name = "SHOP_ID",updatable=false)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
