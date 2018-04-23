package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.SkuWarehouseRel;

import java.util.List;

public interface SoSkuWhCodeRelDao extends BaseDao<SkuWarehouseRel> {
	
    List<SkuWarehouseRel> querySkuWarehouseRelByParam(Long shopId, String skuCodeStr);

}
