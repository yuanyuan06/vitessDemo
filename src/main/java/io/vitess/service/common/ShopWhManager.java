package io.vitess.service.common;

import io.vitess.model.mq.ShopWh;
import io.vitess.model.so.SalesOrder;
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
	 * 根据店铺，仓库编码获取店铺绑定仓库信息
	 * @param shopId
	 * @param whCode
	 * @return
	 * @throws BusinessException
	 */
	public ShopWh getWarehouse(Long shopId, String whCode) throws BusinessException;
	
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

	/**
	 * 判断订单当前所选仓库判断店铺绑定仓库信息是否存在
	 * @param whCode
	 * @param so
	 */
	public void checkExist(String whCode, SalesOrder so)throws BusinessException;
	
	
}
