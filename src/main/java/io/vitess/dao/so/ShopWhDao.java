package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.common.ShopWhCommand;
import io.vitess.model.base.ShopWh;
import org.apache.ibatis.annotations.Param;

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

    List<ShopWhCommand> findByShopIdAndProvince(@Param("shopId") Long shopId, @Param("province") String province);


    ShopWh findShopWhByShopIdAndWhCode(@Param("shopId") Long shopId, @Param("whCode") String whCode);


    ShopWh findDefaultByShopId(@Param("shopId") Long shopId, @Param("isDefault") boolean isDefault);

    ShopWh findPlatformDefaultWhByShopId(@Param("shopId") Long shopId, @Param("isDefault") boolean isDefault);

    List<ShopWh> findShopWhList(@Param("shopId") Long shopId);
    
}