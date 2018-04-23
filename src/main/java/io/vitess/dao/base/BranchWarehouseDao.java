package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.enums.BranchWarehouseStatus;
import io.vitess.enums.BranchWarehouseWmsType;
import io.vitess.service.common.BranchWarehouse;

import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * @className com.jumbo.dao.baseinfo.BranchWarehouseDao
 * @author hailiang.jiang
 * @date 2014年10月21日 下午7:59:26
 */
public interface BranchWarehouseDao extends BaseDao<BranchWarehouse> {
	
    public List<BranchWarehouse> findBranchWarehouseListByShopAndBranchWarehouseWmsType(Long shopId, BranchWarehouseWmsType type, BranchWarehouseStatus status);

	public List<BranchWarehouse> findBranchWarehouseListByShopAndWhCodeList( Long shopId,  Set<String> soLineWhCodeList,  int warehouseStatus);
    
}
