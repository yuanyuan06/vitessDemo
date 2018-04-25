package io.vitess.service.common;

import io.vitess.model.base.WorkFlow;

public interface WorkFlowManager {
	
	WorkFlow findWorkFlowByCode(String workFlowCode);

}
