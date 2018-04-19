package io.vitess.command;

import io.vitess.model.so.SoServiceLine;

public class SoServiceLineCommand extends SoServiceLine {

	private static final long serialVersionUID = 4060027484316301235L;

	private Integer serviceTypeInt;

	public Integer getServiceTypeInt() {
		return serviceTypeInt;
	}

	public void setServiceTypeInt(Integer serviceTypeInt) {
		this.serviceTypeInt = serviceTypeInt;
	}

}
