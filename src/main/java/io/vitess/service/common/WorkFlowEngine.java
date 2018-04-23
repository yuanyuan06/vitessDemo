package io.vitess.service.common;

import io.vitess.common.User;
import io.vitess.exception.WorkFlowException;
import io.vitess.model.base.WorkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface WorkFlowEngine {
	static final Logger logger = LoggerFactory.getLogger(WorkFlowEngine.class);

	static final String APPROVE = "APPROVE";

	static final String REJECT = "REJECT";

	static final String DISCARD = "DISCARD";

	static final String SUBMIT = "SUBMIT";

	static final String RESUBMIT = "RESUBMIT";

	static final String TRANSITION_HANG = "HANG";

}
