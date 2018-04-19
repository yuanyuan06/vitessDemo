package io.vitess.dao.mq;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.MqSoLogLineExchange;
import io.vitess.model.mq.MqSoLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

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

    List<MqSoLog> findSoLogInfo(int start, int size, Long shopId,  Map<String, Object> condition);

    List<Long> findSoLogId(Long shopId, String code, int status);

    void updateStatusAndErrorMsgById(@Param("id") Long id, @Param("shopId") Long shopId, @Param("createTime") Date createTime, @Param("status") int status, @Param("errorMsg") String errorMsg, @Param("errorCount") Integer errorCount);

    int updateStatusAndErrorMsgByCode(String code, Long shopId, Date createTime, int status, String errorMsg, Integer errorCount);
}