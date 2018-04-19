package io.vitess.exception;

import io.vitess.common.User;

public class InvalidWorkTaskActorException extends WorkFlowException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 266215034960530202L;

    private int flowNodeNo;
    private User user;

    public InvalidWorkTaskActorException(String workFlowCode) {
        super(workFlowCode);
    }

    public InvalidWorkTaskActorException(String workFlowCode, int flowNodeNo, User user) {
        super(workFlowCode);
        this.flowNodeNo = flowNodeNo;
        this.user = user;
    }

    public int getFlowNodeNo() {
        return flowNodeNo;
    }

    public void setFlowNodeNo(int flowNodeNo) {
        this.flowNodeNo = flowNodeNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
