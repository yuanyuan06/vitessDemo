package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.CompanyShop;

import java.util.List;

/**
 * <p>
  * 店铺 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface CompanyShopDao extends BaseDao<CompanyShop> {

    List<CompanyShop> findShopListGeneralOrder();

    CompanyShop getByOuId(Long ouid);
}