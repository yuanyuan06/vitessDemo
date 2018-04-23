package io.vitess.service.so;

import io.vitess.model.mq.MqSoServiceLineLog;
import io.vitess.service.BaseManager;

import java.util.List;

public interface MqSoServiceLineLogManager extends BaseManager {

	public List<MqSoServiceLineLog> findMqSoServiceLineLogList(Long soLogId, Long shopId);
	
}
