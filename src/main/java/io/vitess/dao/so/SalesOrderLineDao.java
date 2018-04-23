package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.model.so.SalesOrderLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface SalesOrderLineDao extends BaseDao<SalesOrderLine> {

    SalesOrderLine getSalesOrderLineByIdShopId(@Param("id") Long id, @Param("shopId") Long shopId);
    List<SalesOrderLine> querySalesOrderLineBySoIdShopId( Long soId,  Long shopId);

}