package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.common.WorkFlow;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface WorkFlowDao extends BaseDao<WorkFlow> {

    WorkFlow findByCode(String workFlowCode);

}
