package io.vitess.dao.mq;


import io.vitess.common.BaseDao;
import io.vitess.model.mq.MqDeliveryInfoLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * mq订单配送信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqDeliveryInfoLogDao extends BaseDao<MqDeliveryInfoLog> {

    List<MqDeliveryInfoLog> getMqDeliveryInfoLogsBySoLogId(@Param("soLog") Long soLogId, @Param("shopId") Long shopId);


}