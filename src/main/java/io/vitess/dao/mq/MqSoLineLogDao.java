package io.vitess.dao.mq;


import io.vitess.common.BaseDao;
import io.vitess.model.mq.MqSoLineLog;

import java.util.List;

/**
 * <p>
  * mq订单行 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoLineLogDao extends BaseDao<MqSoLineLog> {


    List<MqSoLineLog> getMqMqSoLineLogsBySoLogId(Long soLogId, Long shopId);
}