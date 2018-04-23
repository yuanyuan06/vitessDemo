package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.model.so.SoAppointmentToWhTask;
import io.vitess.service.BusinessException;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface SoAppointmentToWhTaskDao extends BaseDao<SoAppointmentToWhTask> {

	public List<Long> findSoAppointmentToWhTaskList(int prcesssStatus, RowMapper<Long> rowMapper);
	
	public SoAppointmentToWhTask findSoAppointmentToWhTask(Long soId,  Long shopId) throws BusinessException;
}
