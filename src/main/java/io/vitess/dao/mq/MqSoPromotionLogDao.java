package io.vitess.dao.mq;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.mq.MqSoPromotionLog;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * mq订单促销信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoPromotionLogDao extends BaseMapper<MqSoPromotionLog> {

    List<MqSoPromotionLog> findBySoLogIdAndType(Long soLogId, Integer scopeType, Long shopId);

    List<MqSoPromotionLog> queryPlatformPromotionsByParam(Map<String, Object> map, RowMapper<MqSoPromotionLog> rowMapper);

}