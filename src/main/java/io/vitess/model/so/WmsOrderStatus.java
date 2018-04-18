package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单出入库状态
 * @date 2015年8月26日 下午5:48:13
 */
@Data
@TableName("t_wms_order_status")
public class WmsOrderStatus extends SuperEntity {

	private static final long serialVersionUID = 4745079578898655197L;

	/** 原始数据id（t_receive_wms_log） */
	@TableField("RECEIVE_WMS_LOG_ID")
	private Long receiveWmsLogId;
	/** 订单号(唯一对接标识) **/
	@TableField("ORDER_CODE")
	private String orderCode;
	/** 单据号，Wms单据唯一标识 **/
	@TableField("SHIPPING_CODE")
	private String shippingCode;
	/** 状态(保留字段) **/
	@TableField("WMS_STATUS")
	private Integer wmsStatus;
	/** 订单类型 **/
	@TableField("ORDER_TYPE")
	private Integer orderType;
	/** tmalloms处理状态（0：未处理, 5: 处理失败, 10: 已处理） **/
	private Integer processStatus;
	/** tmalloms处理处理时间 **/
	@TableField("PROCESS_TIME")
	private Date processTime;
	/** 备注 **/
	@TableField("ERROR_MSG")
	private String errorMsg;
	/** 放入时间 **/
	@TableField("INPUT_TIME")
	private Date inputTime;
    //店铺ID
	@TableField("SHOP_ID")
    private Long shopId;

	/** 物流信息 **/
	private List<WmsTransInfo> transInfos;
	/** 发票信息 **/
	private List<WmsOrderInvoice> invoices;
	/** 库存流水 **/
	private List<WmsInvLog> invLogs;


}
