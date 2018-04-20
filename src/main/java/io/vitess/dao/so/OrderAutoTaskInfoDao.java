package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.command.BaseSplitData;
import io.vitess.model.so.OrderAutoTaskInfo;

import java.util.List;

public interface OrderAutoTaskInfoDao extends BaseMapper<OrderAutoTaskInfo> {
	
    List<Long> findWaitToWhList( int queryCount,  Boolean isDirectWms);

    List<BaseSplitData> findOrderToWh( String shops,  int queryCount,  int systemNum);

    
    List<BaseSplitData> findRouteSalesOrder(String shops, int queryCount);
    
	void updateRouteProcessStatus( List<BaseSplitData> pkList,  int processStatus,  String errorMsg);
    
}
