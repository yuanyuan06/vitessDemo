package io.vitess.service.common;

import io.vitess.dao.base.CompanyShopDao;
import io.vitess.model.base.CompanyShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("companyShopManager")
public class CompanyShopManagerImpl implements CompanyShopManager {

    @Autowired
    private CompanyShopDao companyShopDao;

    @Transactional(readOnly = true)
    @Override
    public CompanyShop getByOuId(Long ouid) {
        return companyShopDao.getByOuId(ouid);
    }

    @Cacheable(value="findShopInfoByShopId",key="#shopId")
	@Override
	public CompanyShop findShopInfoByShopId(Long shopId) {
		return companyShopDao.findById(shopId);
	}
}
