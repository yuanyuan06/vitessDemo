package io.vitess.exception;


public class WorkFlowNodeNotExistsException extends WorkFlowException {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1990936408185247513L;

    public WorkFlowNodeNotExistsException(String workFlowNodeNo) {
        super(workFlowNodeNo);
    }
}
