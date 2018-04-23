package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.model.so.Trade;

/**
 * <p>
  * 交易头 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface TradeDao extends BaseDao<Trade> {

    Trade findByPlatformOrderCode(String platformOrderCode,Long shopId);
}