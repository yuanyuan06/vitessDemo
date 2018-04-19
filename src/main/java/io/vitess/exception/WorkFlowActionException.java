package io.vitess.exception;

public class WorkFlowActionException extends RuntimeException {
    /**
	 * 
	 */
    private static final long serialVersionUID = 4487255897872418561L;
    protected String workFlowCode;

    public WorkFlowActionException(String workFlowCode) {
        this.workFlowCode = workFlowCode;
    }

    public String getWorkFlowCode() {
        return workFlowCode;
    }



}
