package io.vitess.dao.so;


import io.vitess.command.SoServiceLineCommand;
import io.vitess.common.BaseDao;
import io.vitess.model.so.SoServiceLine;

import java.util.List;

/**
 * <p>
  * 订单服务行 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface SoServiceLineDao extends BaseDao<SoServiceLine> {


    public List<SoServiceLine> findSoServiceLineList(Long salesOrderId);

    public List<SoServiceLine> findSoServiceLineByShopId(Long salesOrderId, Long shopId);

    public List<SoServiceLineCommand> findSoServiceLineListBySoAndServiceLine(Long salesOrderId,
                                                                                List<Long> soServiceLineIdList,
                                                                                    Long shopId);
}