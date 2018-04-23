package io.vitess.dao.base;


import io.vitess.command.SmsDataParamCommand;
import io.vitess.common.BaseDao;
import io.vitess.model.base.SmsDataParam;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * @author hailiang.jiang
 * @date 2015年09月25日 下午13:32:18
 */
public interface SmsDataParamDao extends BaseDao<SmsDataParam> {

    List<Long> findSmsDataParamIdList(int queryNum, RowMapper<Long> rowMapper);
	
    List<SmsDataParamCommand> findSmsDataParamCommandList(List<Long> pkList);

	void udpateTaskProcessStatus(List<Long> pkList,  int processStatus,  String processResult);
    
}