package io.vitess.service.so;

import io.vitess.dao.mq.MqSoServiceLineLogDao;
import io.vitess.model.mq.MqSoServiceLineLog;
import io.vitess.service.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("mqSoServiceLineLogManager")
@Transactional
public class MqSoServiceLineLogManagerImpl extends BaseManagerImpl implements MqSoServiceLineLogManager {

	private static final long serialVersionUID = -4083301062699744686L;
	
	@Autowired
	private MqSoServiceLineLogDao mqSoServiceLineLogDao;

	@Override
	public List<MqSoServiceLineLog> findMqSoServiceLineLogList(Long soLogId, Long shopId) {
		return mqSoServiceLineLogDao.findMqSoServiceLineLogList(soLogId,shopId);
	}

}
