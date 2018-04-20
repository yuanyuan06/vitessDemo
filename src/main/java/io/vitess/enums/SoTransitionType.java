package io.vitess.enums;

public enum SoTransitionType {
	SO_SYS_AUTO, // 自动处理
	CONFIRMED_TO_ERP, // 同步至ERP
	Y, N, // 是，否
	CS_CONFIRMED, // 客服确认
	CS_TO_WH, // 客服提交至过仓队列
	APPO_SUBMIT_TO_WH, // 预约指定日期过仓
	ERP_TO_WH, // ERP过仓
	WH_CONFIRMED, // 出库指令生成 	1004
	DIRECT_WH_CONFIRMED, // 直接到库房准备中
	TO_WH_FAILED, // 过仓失败（指令同步至WMS失败）
	OUTBOUND, // 出库	1006
	TRANS_UN_REACHABLE, // 物流不可达
	FINISHED, // 完成
	CANCEL_FAILED, // 取消失败
	CANCEL, // 取消
	REVOKE, // 作废
	CLOSED, // 交易关闭
	CANCEL_TO_CONFIRM, // 取消待确认
	CS_SUBMIT_FINISH, // 客服提交待完成确认
	CONFIRM_TO_WH, // 已同步至仓库
	TO_WH_FAILED_STA,// 过仓失败（出库单生成失败）
	SO_AUTO_FINISHED,//电子券订单自动完成
	ORDER_TO_WMS,//订单过仓到wms
	DIRECT_SUSPEND,//直接到挂起节点
	DIRECT_MANUAL_WH,//直接到手动过仓
	DIRECT_WAIT_ERP_TO_WH,//直接到待ERP过仓至WMS（非直连）
	DIRECT_WAIT_CMD_TO_WH,//直接到待同步过仓指令至ERP（直连）
	DIRECT_FINISH,//直接到完成（电子券订单）
	DIRECT_APPOINTMENT, //预约过仓订单直接到预约过仓节点
	APPOINTMENT_HANG , //预约节点到挂起节点
}
