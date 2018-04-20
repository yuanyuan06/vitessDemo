package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SoServiceLineCommand;
import io.vitess.model.mq.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import io.vitess.model.so.SoServiceLine;
import io.vitess.service.BaseManager;

import java.util.List;

public interface SoServiceLineManager extends BaseManager {
	
	List<SoServiceLine> findSoServiceLineList(Long salesOrderId);

	void applySoServiceInfo(CompanyShop shop, MqSoLog soLog, SalesOrderCommand soCmd);

}
