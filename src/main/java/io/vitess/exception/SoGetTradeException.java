package io.vitess.exception;

public class SoGetTradeException extends Exception {
	private static final long serialVersionUID = -1759326578022267662L;
	
	protected String platformCode;
	
	public SoGetTradeException(String platformCode){
		this.platformCode = platformCode;
	}

	public String getPlatformCode() {
		return platformCode;
	}
}