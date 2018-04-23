package io.vitess.dao.mq;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.mq.MqSoServiceLineLog;

import java.util.List;

/**
 * <p>
  * mq订单服务信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoServiceLineLogDao extends BaseMapper<MqSoServiceLineLog> {


    public List<MqSoServiceLineLog> findMqSoServiceLineLogList(Long soLogId, Long shopId);

}