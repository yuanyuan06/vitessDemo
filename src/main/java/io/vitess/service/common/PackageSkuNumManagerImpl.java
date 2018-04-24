package io.vitess.service.common;

import io.vitess.dao.base.PackageSkuNumDao;
import io.vitess.model.base.PackageSkuNum;
import io.vitess.model.base.CompanyShop;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @className com.jumbo.manager.baseinfo.PackageSkuNumManagerImpl
 * @author hailiang.jiang
 * @date 2014年12月3日 下午3:40:42
 */
@Service("packageSkuNumManager")
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class PackageSkuNumManagerImpl extends BaseManagerImpl implements PackageSkuNumManager {

	private static final long serialVersionUID = -6621632621164044555L;
	
	@Autowired
	private PackageSkuNumDao packageSkuNumDao;

	@Override
	public PackageSkuNum findPackageSkuNum(CompanyShop shop, String extCode) throws BusinessException {
		return packageSkuNumDao.findPackageSkuNum(shop.getId(), extCode);
	}
}
