package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.model.mq.TbTrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 平台交易数据 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface TbTradeDao extends BaseDao<TbTrade> {

    List<TbTrade> findTbTradeNotSync();

    void updateTbTrade(@Param("id") Long id, @Param("syncStatus") Integer syncStatus, @Param("shopId") Long shopId);

}