package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.common.ShopWhCommand;
import io.vitess.model.base.ShopWh;

import java.util.List;

/**
 * <p>
  * 店铺仓库表 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface ShopWhDao extends BaseDao<ShopWh> {

    List<ShopWhCommand> findByShopIdAndProvince( Long shopId, String province);


    ShopWh findShopWhByShopIdAndWhCode( Long shopId, String whCode);


    ShopWh findDefaultByShopId(Long shopId,  boolean isDefault);

    ShopWh findPlatformDefaultWhByShopId(Long shopId,  boolean isDefault);

    List<ShopWh> findShopWhList(Long shopId);
    
}