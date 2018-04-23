package io.vitess.dao.mq;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.mq.MqSoLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * mq订单头 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoLogDao extends BaseMapper<MqSoLog> {

    MqSoLog findMqSoLogByIdShopId(@Param("id") Long id, @Param("shopId") Long shopId);

    List<Long> findMqSoForCreateSo(int platformType, Long shopId, int status, Date createTime);

    void updateStatusAndErrorMsgById(@Param("id") Long id, @Param("shopId") Long shopId, @Param("createTime") Date createTime, @Param("status") int status, @Param("errorMsg") String errorMsg, @Param("errorCount") Integer errorCount);

}