package io.vitess.dao.base;


import io.vitess.command.ShopSkuWhRefCommand;
import io.vitess.common.BaseDao;
import io.vitess.model.base.ShopSkuWhRef;

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
	ShopSkuWhRef findByShopAndSku( Long shopId,  String extCode,  Date createTime);

	ShopSkuWhRef findShopSkuWhRefByShopIdAndExtCode( Long shopId,  String extCode);
	
    void updateActiveOff( Long shopId,  String extCode,  Date updateTime,  String updateUserNo);
	
	void deleteShopSkuWhRefByShopId( Long shopId);
	
    List<ShopSkuWhRefCommand> findShopSkuWhRefByShopId(Long shopId);
	
}
