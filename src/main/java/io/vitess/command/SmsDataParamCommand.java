package io.vitess.command;

import io.vitess.model.base.SmsDataParam;

public class SmsDataParamCommand extends SmsDataParam {
	private static final long serialVersionUID = 883080698633407188L;

	/** 参数名 **/
	private java.lang.String dtParam;
	/** 参数值 **/
	private java.lang.String dtValue;
	/** 备注描述 */
	private String dtDesc;


	public java.lang.String getDtParam() {
		return dtParam;
	}

	public void setDtParam(java.lang.String dtParam) {
		this.dtParam = dtParam;
	}

	public java.lang.String getDtValue() {
		return dtValue;
	}

	public void setDtValue(java.lang.String dtValue) {
		this.dtValue = dtValue;
	}

	public String getDtDesc() {
		return dtDesc;
	}

	public void setDtDesc(String dtDesc) {
		this.dtDesc = dtDesc;
	}

}
