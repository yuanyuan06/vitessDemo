package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.model.so.SalesOrder;

import java.util.List;

/**
 * <p>
  * 订单头 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface SalesOrderDao extends BaseDao<SalesOrder> {

    SalesOrder findSoByCodeShopId(String code,  Long shopId);

    void updateSuspendReasonType(Long soId,  Long shopId,  Integer suspendReasonType);

    List<Integer> queryCheckHandCreateOrder(String platformOrderCode, Long shopId);
}