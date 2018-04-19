package io.vitess.exception;


public class TaskNotExistsException extends WorkTaskException {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6345380582640029502L;

    public TaskNotExistsException(String workFlowCode) {
        super(workFlowCode);
    }

    public TaskNotExistsException(String workFlowCode, String taskNo) {
        super(workFlowCode, taskNo);
    }
}
