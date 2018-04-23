package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.ShoOutLetInfo;

public interface ShoOutLetInfoDao extends BaseDao<ShoOutLetInfo> {

	ShoOutLetInfo queryStore(String storeId, Long shopId);

	ShoOutLetInfo queryStoreCode(String storeId, Long shopId);

	ShoOutLetInfo queryByStoreCode(String storeCode, Long shopId);
	/**
	 * 默认一个店铺只有一个ec仓
	 */
	ShoOutLetInfo queryEcWareHouse(Long shopId);
}
