package io.vitess.dao.base;


import io.vitess.command.ShopSkuWhRefCommand;
import io.vitess.common.BaseDao;
import io.vitess.model.base.ShopSkuWhRef;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ShopSkuWhRefDao extends BaseDao<ShopSkuWhRef> {

	/**
	 * 
	 * @param shopId
	 * @param extCode
	 * @param createTime
	 * @return
	 */
	ShopSkuWhRef findByShopAndSku(@Param("shopId") Long shopId, @Param("extCode") String extCode, @Param("createTime") Date createTime);

	ShopSkuWhRef findShopSkuWhRefByShopIdAndExtCode(@Param("shopId") Long shopId, @Param("extCode") String extCode);
	
    void updateActiveOff(@Param("shopId") Long shopId, @Param("extCode") String extCode, @Param("updateTime") Date updateTime, @Param("updateUserNo") String updateUserNo);
	
	void deleteShopSkuWhRefByShopId(@Param("shopId") Long shopId);
	
    List<ShopSkuWhRefCommand> findShopSkuWhRefByShopId(@Param("shopId") Long shopId);
	
}
