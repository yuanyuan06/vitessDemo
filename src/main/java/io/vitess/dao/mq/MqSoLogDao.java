package io.vitess.dao.mq;


import io.vitess.common.BaseDao;
import io.vitess.model.mq.MqSoLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
  * mq订单头 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface MqSoLogDao extends BaseDao<MqSoLog> {

    MqSoLog findMqSoLogByIdShopId(@Param("id") Long id, @Param("shopId") Long shopId);

    List<Long> findMqSoForCreateSo(@Param("platformType") int platformType, @Param("shopId") Long shopId, @Param("status") int status, @Param("createTime") Date createTime);

    void updateStatusAndErrorMsgById(@Param("id") Long id, @Param("shopId") Long shopId, @Param("createTime") Date createTime, @Param("status") int status, @Param("errorMsg") String errorMsg, @Param("errorCount") Integer errorCount);

}