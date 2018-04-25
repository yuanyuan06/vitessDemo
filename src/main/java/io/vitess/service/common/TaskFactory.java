package io.vitess.service.common;

import io.vitess.enums.SlipType;
import io.vitess.exception.WorkFlowException;
import io.vitess.model.base.WorkTask;

public interface TaskFactory<S> {

	/**
	 * 创建工作流
	 * @param workFlowCode
	 * @param slip
	 * @param shopId
	 * @return
	 * @throws WorkFlowException
	 */
	WorkTask createTask(String workFlowCode, S slip, Long shopId) throws WorkFlowException;

	SlipType getSlipType();
}
