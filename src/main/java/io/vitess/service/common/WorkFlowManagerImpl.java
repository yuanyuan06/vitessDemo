package io.vitess.service.common;

import io.vitess.common.WorkFlow;
import io.vitess.dao.base.WorkFlowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("workFlowManager")
public class WorkFlowManagerImpl implements WorkFlowManager {

	@Autowired
	protected WorkFlowDao workFlowDao;
	
	@Override
	public WorkFlow findWorkFlowByCode(String workFlowCode) {
		return workFlowDao.findByCode(workFlowCode);
	}

}
