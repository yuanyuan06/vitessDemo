package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.InventoryStatus;

import java.util.List;

public interface InventoryStatusDao extends BaseDao<InventoryStatus> {
	
    InventoryStatus findByName(String name);
    InventoryStatus findByCode(String code);
    List<InventoryStatus> findAllAvailableInvStatus();
	
}
