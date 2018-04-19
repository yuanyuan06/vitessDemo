package io.vitess.dao.mq;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.mq.MqPlatformMemberLog;

/**
 * <p>
  * mq订单会员信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqPlatformMemberLogDao extends BaseMapper<MqPlatformMemberLog> {

    MqPlatformMemberLog findMqPlatformMemberLog(Long id, Long shopId);
}