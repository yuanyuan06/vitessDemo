package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SoServiceLineCommand;
import io.vitess.model.mq.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import io.vitess.model.so.SoServiceLine;
import io.vitess.service.BaseManager;

import java.util.List;

public interface SoServiceLineManager extends BaseManager {
	
	public SoServiceLine getByPrimaryKey(Long pkId);
	
	public List<SoServiceLine> findSoServiceLineList(Long salesOrderId);
	
	public List<SoServiceLineCommand> findSoServiceLineListBySoAndServiceLine(Long salesOrderId, List<Long> soServiceLineIdList, Long shopId);
	
	public void applySoServiceInfo(CompanyShop shop, MqSoLog soLog, SalesOrderCommand soCmd);

	public void countSoServiceLineMoney(SalesOrderCommand newSalesOrder, List<SoServiceLineCommand> soServiceLineCmdList);

}
