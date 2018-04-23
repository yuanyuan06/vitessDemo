package io.vitess.service.common;

import io.vitess.dao.base.SkuDao;
import io.vitess.dao.base.SoSkuWhCodeRelDao;
import io.vitess.model.base.Sku;
import io.vitess.model.base.SkuWarehouseRel;
import io.vitess.service.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service("skuManager")
public class SkuManagerImpl extends BaseManagerImpl implements SkuManager {
    private static final long serialVersionUID = -1504861017273304643L;

    @Autowired
    private SkuDao skuDao;
    
    @Autowired
    private SoSkuWhCodeRelDao soSkuWhCodeRelDao;


    @Override
    public Sku findByCode(String code) {
        return this.skuDao.findByCode(code);
    }

    @Override
    public Sku findSkuByExtCodeBrtCode(String extCode, String brtCode, String whCustomerCode) {
    	return skuDao.findSkuByExtCodeBrtCode(extCode,brtCode,whCustomerCode);
    }

	@Override
	public List<SkuWarehouseRel> findSkuWarehouseRel(Long shopId, String skuCodeStr) {
		return soSkuWhCodeRelDao.querySkuWarehouseRelByParam(shopId, skuCodeStr);
	}

	@Override
	public String findSkuByExtCode(String skuCode, String businessRegionType, String whCustomerCode) {
		Set<String> set = new HashSet<>(1);
		set.add(skuCode);
		List<String> extCodeList =  skuDao.findByCodes(set, businessRegionType, whCustomerCode);
		if (extCodeList.size() > 0) {
			return extCodeList.get(0);
		}
		return null;
	}
    
}
