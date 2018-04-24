package io.vitess.dao.mq;


import io.vitess.common.BaseDao;
import io.vitess.model.mq.MqSoPromotionLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * mq订单促销信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoPromotionLogDao extends BaseDao<MqSoPromotionLog> {

    List<MqSoPromotionLog> findBySoLogIdAndType(@Param("soLog") Long soLogId, @Param("scopeType") Integer scopeType, @Param("shopId") Long shopId);
}