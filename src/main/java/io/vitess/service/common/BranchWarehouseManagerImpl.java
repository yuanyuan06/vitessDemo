package io.vitess.service.common;

import io.vitess.dao.base.BranchWarehouseDao;
import io.vitess.enums.BranchWarehouseStatus;
import io.vitess.enums.BranchWarehouseWmsType;
import io.vitess.service.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author haiwen.chen
 *
 */
@Transactional
@Service("branchWarehouseManager")
public class BranchWarehouseManagerImpl extends BaseManagerImpl implements BranchWarehouseManager {

    private static final long serialVersionUID = 4358939031678951928L;
    
    @Autowired
    private BranchWarehouseDao branchWarehouseDao;
    
    @Override
    public List<BranchWarehouse> findBranchWarehouseList(Long shopId, BranchWarehouseWmsType wmsType, BranchWarehouseStatus status) {
    	return this.branchWarehouseDao.findBranchWarehouseListByShopAndBranchWarehouseWmsType(shopId, wmsType, status);
    }

	@Override
	public List<BranchWarehouse> findBranchWarehouseList(Long shopId, Set<String> soLineWhCodeList, BranchWarehouseStatus enable) {
		return this.branchWarehouseDao.findBranchWarehouseListByShopAndWhCodeList(shopId, soLineWhCodeList, enable.getValue());
	}

}
