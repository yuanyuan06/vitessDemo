package io.vitess.model.so;

import io.vitess.common.SuperEntity;

/**
 * 结算订单，同步给pacs
 * @date 2015年9月7日 下午3:49:16
 */

//@TableName("t_td_tally_order_to_pacs")
public class TallyOrderToPacs extends SuperEntity {
	private static final long serialVersionUID = 2780093165415532167L;

	/** 相关单据号ID（如：销售订单、退换货订单） **/
	private java.lang.Long refSlipId;
	/** 相关单据号CODE（如：销售订单、退换货订单） **/
	private java.lang.String refSlipCode;
	/** 单据类型（2：销售单，3：退换货申请，6：换货出库） **/
	private java.lang.Integer dataType;
	/** 0：新建、5：处理失败、10：处理成功 **/
	private java.lang.Integer processStatus;
	/** 错误消息 **/
	private java.lang.String errorMsg;
	/** 放入时间 **/
	private java.util.Date inputTime;
	/** 数据更新时间 **/
	private java.util.Date updateTime;
    //店铺ID
    private Long shopId;

}
