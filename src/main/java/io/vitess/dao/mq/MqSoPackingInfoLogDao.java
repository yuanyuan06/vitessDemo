package io.vitess.dao.mq;


import io.vitess.common.BaseDao;
import io.vitess.enums.MqSoPackingInfoLevel;
import io.vitess.model.mq.MqSoPackingInfoLog;

import java.util.List;

/**
 * <p>
  * mq订单包装信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoPackingInfoLogDao extends BaseDao<MqSoPackingInfoLog> {


    List<MqSoPackingInfoLog> findBySoLineLogIdAndLevel(Long soLineLogId, MqSoPackingInfoLevel level, Long shopId);

}