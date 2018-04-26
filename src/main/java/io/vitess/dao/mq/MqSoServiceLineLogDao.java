package io.vitess.dao.mq;


import io.vitess.common.BaseDao;
import io.vitess.model.mq.MqSoServiceLineLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * mq订单服务信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoServiceLineLogDao extends BaseDao<MqSoServiceLineLog> {


    public List<MqSoServiceLineLog> findMqSoServiceLineLogList(@Param("soLogId") Long soLogId, @Param("shopId") Long shopId);

}