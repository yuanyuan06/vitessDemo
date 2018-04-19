package io.vitess.exception;


public class WrongCurrentTaskNodeException extends WorkTaskException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8027770480892198445L;

    private Integer fromNode;
    private Integer currentFromNode;

    public WrongCurrentTaskNodeException(String workFlowCode, String taskNo, Integer fromNode, Integer currentFromNode) {
        super(workFlowCode, taskNo);
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
}
