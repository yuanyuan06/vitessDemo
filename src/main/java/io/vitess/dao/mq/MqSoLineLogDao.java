package io.vitess.dao.mq;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.mq.MqSoLineLog;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * <p>
  * mq订单行 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoLineLogDao extends BaseMapper<MqSoLineLog> {


    List<MqSoLineLog> getMqMqSoLineLogsBySoLogId(Long soLogId, Long shopId);
//
    List<Long> findBySoLogIdAndExtentionCode(Long soLogId, Long shopId, String extentionCode);

    void updateExtentionCodeBySoLogId(Long soLogId, Long shopId, String extentionCode,  String newExtentionCode);

    void updateExtentionCodeBySoLogLineId(Long lineId,String newExtentionCode,Long shopId);

}