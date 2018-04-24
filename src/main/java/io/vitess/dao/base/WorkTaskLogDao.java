package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.WorkTaskLog;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface WorkTaskLogDao extends BaseDao<WorkTaskLog> {

    void addTaskLog(Long taskId, Long userId, String transitionCode, Integer fromNodeNo, Integer toNodeNo, Date enterTIme, Date transactionTime, String memo,  Long shopId);

}
