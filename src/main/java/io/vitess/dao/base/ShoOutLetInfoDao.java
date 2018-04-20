package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.command.ShoOutLetInfo;

public interface ShoOutLetInfoDao extends BaseMapper<ShoOutLetInfo> {

	ShoOutLetInfo queryStore(String storeId, Long shopId);

	ShoOutLetInfo queryStoreCode(String storeId, Long shopId);

	ShoOutLetInfo queryByStoreCode(String storeCode, Long shopId);
	/**
	 * 默认一个店铺只有一个ec仓
	 */
	ShoOutLetInfo queryEcWareHouse(Long shopId);
}
