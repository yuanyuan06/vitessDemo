package io.vitess.dao.base;

import io.vitess.common.BaseDao;
import io.vitess.model.base.WorkFlow;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface WorkFlowDao extends BaseDao<WorkFlow> {

    WorkFlow findByCode(@Param("workFlowCode") String workFlowCode);

    List<WorkFlow> findAllAvailableFlow();
}
