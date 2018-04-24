package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.model.so.Trade;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  * 交易头 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface TradeDao extends BaseDao<Trade> {

    Trade findByPlatformOrderCode(@Param("platformOrderCode") String platformOrderCode, @Param("shopId") Long shopId);
}