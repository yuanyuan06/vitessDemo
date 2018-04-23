package io.vitess.service.common;

import io.vitess.common.WorkFlow;

public interface WorkFlowManager {
	
	WorkFlow findWorkFlowByCode(String workFlowCode);

}
