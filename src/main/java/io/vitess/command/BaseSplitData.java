package io.vitess.command;

import io.vitess.common.SuperEntity;

/**
 * @Description:  水平表分库参数Model
 * @author zhiyong.shi
 * 2017年2月8日
 */
public class BaseSplitData extends SuperEntity{

	
	private static final long serialVersionUID = 1858200321633733839L;
	/**
	 * 店铺Id
	 */
	private Long shopId;
	/**
	 * 
	 * @Description: 系统标示
	 * @author zhiyong.shi
	 * 2017年4月26日
	 */
	private int  systemNum;
	/**
	 * 
	 * @Description: 订单ID （退货或者销售）
	 * @author zhiyong.shi
	 * 2017年6月5日
	 */
	private Long soId;
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public int getSystemNum() {
		return systemNum;
	}

	public void setSystemNum(int systemNum) {
		this.systemNum = systemNum;
	}

	public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}
	
	
}
