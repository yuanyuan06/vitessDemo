package io.vitess.exception;


public class WorkFlowNotExistsException extends WorkFlowException {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1990936408185247513L;

    public WorkFlowNotExistsException(String workFlowCode) {
        super(workFlowCode);
    }
}
