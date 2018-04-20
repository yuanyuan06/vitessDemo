package io.vitess.command;

import java.io.Serializable;

/**
 * 返回订单挂起判断结果
 * @author fanht
 *
 */
public class SoSuspend implements Serializable {
	private static final long serialVersionUID = 4391473216097900578L;
	
	//挂起原因
	private Integer suspendReasonType;
	
	//是否挂起
	private Boolean isSuspend;

	public Integer getSuspendReasonType() {
		return suspendReasonType;
	}

	public void setSuspendReasonType(Integer suspendReasonType) {
		this.suspendReasonType = suspendReasonType;
	}

	public Boolean getIsSuspend() {
		return isSuspend;
	}

	public void setIsSuspend(Boolean isSuspend) {
		this.isSuspend = isSuspend;
	}

}
