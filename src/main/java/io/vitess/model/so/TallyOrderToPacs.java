package io.vitess.model.so;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.vitess.common.SuperEntity;

/**
 * 结算订单，同步给pacs
 * @date 2015年9月7日 下午3:49:16
 */

@TableName("t_td_tally_order_to_pacs")
public class TallyOrderToPacs extends SuperEntity {
	private static final long serialVersionUID = 2780093165415532167L;

	/** 相关单据号ID（如：销售订单、退换货订单） **/
	@TableField("REF_SLIP_ID")
	private java.lang.Long refSlipId;
	/** 相关单据号CODE（如：销售订单、退换货订单） **/
	@TableField("REF_SLIP_CODE")
	private java.lang.String refSlipCode;
	/** 单据类型（2：销售单，3：退换货申请，6：换货出库） **/
	@TableField("DATA_TYPE")
	private java.lang.Integer dataType;
	/** 0：新建、5：处理失败、10：处理成功 **/
	@TableField("PROCESS_STATUS")
	private java.lang.Integer processStatus;
	/** 错误消息 **/
	@TableField("ERROR_MSG")
	private java.lang.String errorMsg;
	/** 放入时间 **/
	@TableField("INPUT_TIME")
	private java.util.Date inputTime;
	/** 数据更新时间 **/
	@TableField("UPDATE_TIME")
	private java.util.Date updateTime;
    //店铺ID
	@TableField("SHOP_ID")
    private Long shopId;

}