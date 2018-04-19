package io.vitess.exception;

import io.vitess.enums.SlipType;

public class SlipTypeNotMatchException extends WorkFlowException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5628130252399589934L;
    private SlipType workFlowSlipType;
    private SlipType slipType;

    public SlipTypeNotMatchException(String workFlowCode) {
        super(workFlowCode);
    }

    public SlipTypeNotMatchException(String workFlowCode, SlipType workFlowSlipType, SlipType slipType) {
        super(workFlowCode);
        this.workFlowSlipType = workFlowSlipType;
        this.slipType = slipType;
    }

    public SlipType getWorkFlowSlipType() {
        return workFlowSlipType;
    }

    public void setWorkFlowSlipType(SlipType workFlowSlipType) {
        this.workFlowSlipType = workFlowSlipType;
    }

    public SlipType getSlipType() {
        return slipType;
    }

    public void setSlipType(SlipType slipType) {
        this.slipType = slipType;
    }
}
