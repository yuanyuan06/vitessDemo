package io.vitess.dao.mq;

import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface MqSoPackingInfoLogDao extends BaseMapper<MqSoPackingInfoLog> {


    List<MqSoPackingInfoLog> findBySoLineLogIdAndLevel(Long soLineLogId, MqSoPackingInfoLevel level, Long shopId);

}