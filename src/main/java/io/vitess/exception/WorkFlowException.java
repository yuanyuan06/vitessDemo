package io.vitess.exception;

public class WorkFlowException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3998031292831096192L;
    protected String workFlowCode;

    public WorkFlowException(String workFlowCode) {
        this.workFlowCode = workFlowCode;
    }

    public String getWorkFlowCode() {
        return workFlowCode;
    }
}
