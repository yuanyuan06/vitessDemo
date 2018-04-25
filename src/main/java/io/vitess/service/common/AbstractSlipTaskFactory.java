package io.vitess.service.common;

import io.vitess.dao.base.WorkTaskDao;
import io.vitess.dao.base.WorkTaskLogDao;
import io.vitess.enums.WorkTaskStatus;
import io.vitess.exception.SlipTypeNotMatchException;
import io.vitess.exception.WorkFlowDefinitionErrorException;
import io.vitess.exception.WorkFlowException;
import io.vitess.exception.WorkFlowNotExistsException;
import io.vitess.model.base.User;
import io.vitess.model.base.WorkFlow;
import io.vitess.model.base.WorkFlowNode;
import io.vitess.model.base.WorkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractSlipTaskFactory<S> implements TaskFactory<S>{

	protected static final Logger logger = LoggerFactory.getLogger(AbstractSlipTaskFactory.class);
	
	@Autowired
	protected WorkFlowManager workFlowManager;


	@Autowired
	protected WorkTaskDao workTaskDao;


	@Autowired
	protected WorkTaskLogDao workTaskLogDao;

	protected static final String Y = "Y";

	protected static final String N = "N";

	public abstract User getCreatorOfSlip(S slip);

	public abstract String getSlipCode(S slip);

	public abstract Long getSlipId(S slip);

	public abstract void afterTaskCreation(WorkTask task, WorkFlowNode firstNode, User creator);

	@Override
	public WorkTask createTask(String workFlowCode, S slip, Long shopId) throws WorkFlowException{
		WorkFlow workFlow = workFlowManager.findWorkFlowByCode(workFlowCode);
		if (workFlow == null){
			throw new WorkFlowNotExistsException(workFlowCode);
		}
		if (workFlow.getFlowNodes().size() == 0){
			throw new WorkFlowDefinitionErrorException(workFlowCode);
		}
		WorkFlowNode firstNode = workFlow.getFlowNodes().iterator().next();
		// check slipType
		if (getSlipType() != workFlow.getSlipType()){

			throw new SlipTypeNotMatchException(workFlowCode, workFlow.getSlipType(), getSlipType());
		}

		User creator = getCreatorOfSlip(slip);
		WorkTask task = new WorkTask();
		task.setCurrentNodeNo(firstNode.getNodeNo());
		task.setStartTime(new Date());
		task.setEnterTime(new Date());
		task.setRefSlipType(getSlipType().getValue());
		task.setRefSlipCode(getSlipCode(slip));
		task.setTaskNo(UUID.randomUUID().toString());
		task.setWorkFlow(workFlow);
		task.setStatus(WorkTaskStatus.RUNNING.getValue());
		task.setCreateUser(creator);
		task.setRefSlipId(getSlipId(slip));
		//新增shopId
		task.setRefSlipShopId(shopId);
		workTaskDao.insert(task);
		workTaskLogDao.addTaskLog(task.getId(), creator == null ? null : creator.getId(), null, null, firstNode.getNodeNo(), new Date(), new Date(), null,shopId);
		afterTaskCreation(task, firstNode, creator);
		return task;
	}
    
}
