package io.vitess.service.common;

import io.vitess.model.base.PackageSkuNum;
import io.vitess.model.mq.CompanyShop;
import io.vitess.service.BaseManager;
import io.vitess.service.BusinessException;

/**
 * 
 * 
 * @className com.jumbo.manager.baseinfo.PackageSkuNumManager
 * @author hailiang.jiang
 * @date 2014年12月3日 下午3:37:33
 */
public interface PackageSkuNumManager extends BaseManager {

	/**
	 * 根据shop, extCode查询PackageSkuNum
	 *
	 * @methodName com.jumbo.manager.baseinfo.PackageSkuNumManager.findPackageSkuNum
	 * @author hailiang.jiang
	 * @date 2014年12月3日 下午3:42:01
	 * @param shop
	 * @param extCode
	 * @return
	 * @throws BusinessException
	 */
	PackageSkuNum findPackageSkuNum(CompanyShop shop, String extCode) throws BusinessException;
	
}
