package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.WorkTaskLog;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface WorkTaskLogDao extends BaseMapper<WorkTaskLog> {

    void addTaskLog(Long taskId, Long userId, String transitionCode, Integer fromNodeNo, Integer toNodeNo, Date enterTIme, Date transactionTime, String memo,  Long shopId);

    List<WorkTaskLog> findTaskLogs( Long taskId,  Long shopId);

    Date getLastTransactionTime( Long taskId,  Long shopId);

}
