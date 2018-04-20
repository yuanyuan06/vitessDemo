package io.vitess.service.common;

import io.vitess.enums.BranchWarehouseStatus;
import io.vitess.enums.BranchWarehouseWmsType;
import io.vitess.service.BaseManager;

import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * @className com.jumbo.manager.baseinfo.BranchWarehouseManager
 * @author hailiang.jiang
 * @date 2014年10月21日 下午7:40:04
 */
public interface BranchWarehouseManager extends BaseManager {
	
	public static final String MIX_WAREHOUSE_CODE = "MIX";
	public static final String IC_WAREHOUSE_CODE = "IC";
	
	public List<BranchWarehouse> findBranchWarehouseList(Long shopId, BranchWarehouseWmsType wmsType, BranchWarehouseStatus status);

	/**
	 * 查询所有不在仓库list的仓库信息
	 * @methodName com.jumbo.manager.baseinfo.BranchWarehouseManager.findBranchWarehouseList
	 * @author hailiang.jiang
	 * @date 2015年2月11日 上午11:38:40
	 * @version: v1.0.0
	 */
	public List<BranchWarehouse> findBranchWarehouseList(Long shopId, Set<String> soLineWhCodeList, BranchWarehouseStatus enable);
	
}
