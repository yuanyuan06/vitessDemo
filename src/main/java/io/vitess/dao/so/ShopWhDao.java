package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.common.ShopWhCommand;
import io.vitess.model.mq.ShopWh;

import java.util.List;

/**
 * <p>
  * 店铺仓库表 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface ShopWhDao extends BaseMapper<ShopWh> {

    List<ShopWhCommand> findByShopIdAndProvince( Long shopId, String province);


    ShopWh findShopWhByShopIdAndWhCode( Long shopId, String whCode);


}