package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.so.SoAppointmentToWhTask;
import io.vitess.service.BusinessException;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;
import java.util.List;

public interface SoAppointmentToWhTaskDao extends BaseMapper<SoAppointmentToWhTask> {

	public List<Long> findSoAppointmentToWhTaskList(int prcesssStatus, RowMapper<Long> rowMapper);
	
	public SoAppointmentToWhTask findSoAppointmentToWhTask(Long soId,  Long shopId) throws BusinessException;
	
	void updateProcessStatus(Long id,
                             Long shopId,
                             int processStatus,
                              String processResult,
                              String updateUserNo,  Date updateTime);
}
