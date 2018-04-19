package io.vitess.exception;

public class WorkTaskException extends WorkFlowException {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4255285574882571090L;

    protected String taskNo;

    public WorkTaskException(String workFlowCode) {
        super(workFlowCode);
    }

    public WorkTaskException(String workFlowCode, String taskNo) {
        super(workFlowCode);
        this.taskNo = taskNo;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
}
