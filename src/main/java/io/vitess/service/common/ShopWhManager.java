package io.vitess.service.common;

import io.vitess.model.base.ShopWh;
import io.vitess.service.BaseManager;
import io.vitess.service.BusinessException;

import java.util.List;

/**
 * 接口：店铺仓库关系维护管理
 * 
 * @className com.jumbo.manager.baseinfo.ShopWhManager
 * @author hailiang.jiang
 * @date 2014年10月16日 上午11:31:55
 */
public interface ShopWhManager extends BaseManager {

	/**
	 * 获取店铺默认仓库
	 *
	 * @methodName com.jumbo.manager.baseinfo.ShopWhManager.getDefaultWarehouse
	 * @author hailiang.jiang
	 * @date 2014年10月16日 下午12:28:13
	 * @param shopId
	 * @return
	 * @throws BusinessException
	 */
	public ShopWh getDefaultWarehouse(Long shopId) throws BusinessException;
	
	/**
	 * 获取店铺默认平台发货仓
	 * @param shopId
	 * @return
	 * @throws BusinessException
	 */
	public ShopWh findPlatformDefaultWh(Long shopId) throws BusinessException;
	
	/**
	 * 获取店铺关联的仓库
	 *
	 * @methodName com.jumbo.manager.baseinfo.ShopWhManager.findShopWhList
	 * @author hailiang.jiang
	 * @date 2015年1月12日 上午11:02:17
	 * @param shopId
	 * @return
	 * @throws BusinessException
	 */
	public List<ShopWh> findShopWhList(Long shopId) throws BusinessException;
}
