package io.vitess.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态
 * 
 * @author sjk
 * 
 */
public enum SalesOrderStatus {
	/** 作废 */
	DISABLED(0, "作废"),
	/** 新建 */
	CREATED(2, "新建"),
	/** 已提交 */
	SUBMITED(1, "已提交"),
	/** 库房准备中 */
	WH_READY(4, "库房准备中"),
	/** 在途 */
	IN_TRANSIT(6, "在途"),
	/** 已同步过仓指令至ERP */
	TO_WH(7, "已同步过仓指令至ERP"),
	/** 已取消 */
	CANCELED(10, "已取消"),
	/** 关闭 */
	CLOSED(12, "关闭"),
	/** 挂起待确认 */
	CONFIRM_UP(16, "挂起待确认"),
	/** 完成 */
	FINISHED(15, "完成");

	private int value;

	private String name;

	private SalesOrderStatus(int value, String name){
		this.value = value;
		this.name = name;
	}

	public int getValue(){
		return value;
	}

	public String getName(){
		return name;
	}
	
	/**
	 * 所有无效的订单状态
	 * @author hailiang.jiang
	 * @date 2015年9月8日 上午11:23:21
	 * @return
	 */
	public static List<Integer> getInvalidSalesOrderStatus() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(DISABLED.getValue());
		list.add(CANCELED.getValue());
//		list.add(CLOSED.getValue()); //只要发过货的订单都为有效的订单
		return list;
	}
	
	/**
	 * 结算单据类型
	 * 1：有效的销售
	 * 2: 关闭（取消）的销售
	 * 结算单据同步时该字段非空
	 * @author hailiang.jiang
	 * @date 2015年9月8日 上午11:22:28
	 * @param orderStatus
	 * @return
	 */
	public static int toPacsStatementType(int orderStatus) {
		return getInvalidSalesOrderStatus().contains(orderStatus) ? 2 : 1;
	}

	public static SalesOrderStatus valueOf(int value){
		switch (value) {
		case 0:
			return DISABLED;
		case 2:
			return CREATED;
		case 1:
			return SUBMITED;
		case 4:
			return WH_READY;
		case 6:
			return IN_TRANSIT;
		case 10:
			return CANCELED;
		case 12:
			return CLOSED;
		case 15:
			return FINISHED;
		case 16:
			return CONFIRM_UP;
		case 7:
			return TO_WH;
		default:
			throw new IllegalArgumentException();
		}
	}

}
