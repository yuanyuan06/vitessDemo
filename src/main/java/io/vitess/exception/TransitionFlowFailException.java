package io.vitess.exception;


public class TransitionFlowFailException extends WorkFlowException {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1852089191444299544L;

    private Integer nodeNo;
    private String transitionCode;

    public TransitionFlowFailException(String workFlowCode, Integer nodeNo, String transitionCode) {
        super(workFlowCode);
        this.nodeNo = nodeNo;
        this.transitionCode = transitionCode;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getTransitionCode() {
        return transitionCode;
    }

    public void setTransitionCode(String transitionCode) {
        this.transitionCode = transitionCode;
    }
}
