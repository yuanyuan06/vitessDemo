package io.vitess.exception;

public class ForkTransitionNotExistsException extends WorkFlowActionException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3266006199098193350L;

    private String taskNo;
    private Integer fromNode;
    private Integer currentFromNode;

    public ForkTransitionNotExistsException(String workFlowCode) {
        super(workFlowCode);
    }

    public ForkTransitionNotExistsException(String workFlowCode, String taskNo, Integer fromNode, Integer currentFromNode) {
        super(workFlowCode);
        this.taskNo = taskNo;
        this.fromNode = fromNode;
        this.currentFromNode = currentFromNode;
    }

    public Integer getFromNode() {
        return fromNode;
    }

    public void setFromNode(Integer fromNode) {
        this.fromNode = fromNode;
    }

    public Integer getCurrentFromNode() {
        return currentFromNode;
    }

    public void setCurrentFromNode(Integer currentFromNode) {
        this.currentFromNode = currentFromNode;
    }


    public String getTaskNo() {
        return taskNo;
    }


    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
}
