package io.vitess.dao.mq;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.mq.MqDeliveryInfoLog;

import java.util.List;

/**
 * <p>
  * mq订单配送信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqDeliveryInfoLogDao extends BaseMapper<MqDeliveryInfoLog> {

    List<MqDeliveryInfoLog> getMqDeliveryInfoLogsBySoLogId(Long soLogId, Long shopId);


}