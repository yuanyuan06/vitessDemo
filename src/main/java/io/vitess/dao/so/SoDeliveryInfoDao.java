package io.vitess.dao.so;


import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SalesOrderLineCommand;
import io.vitess.common.BaseDao;
import io.vitess.model.so.SoDeliveryInfo;

import java.util.List;

/**
 * <p>
  * 订单配送信息 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface SoDeliveryInfoDao extends BaseDao<SoDeliveryInfo> {

    //已不推荐使用，请使用带shopId的
    SoDeliveryInfo findBySoId(Long soId);

    SoDeliveryInfo findBySoIdShopId(Long soId, Long shopId);

    List<SalesOrderCommand> loadSoAddressByPoc(List<SalesOrderLineCommand> list, Long shopId);



}