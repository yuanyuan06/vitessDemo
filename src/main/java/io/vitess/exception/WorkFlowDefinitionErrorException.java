package io.vitess.exception;


public class WorkFlowDefinitionErrorException extends WorkFlowException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6088802620757421817L;

    public WorkFlowDefinitionErrorException(String workFlowCode) {
        super(workFlowCode);
    }
}
