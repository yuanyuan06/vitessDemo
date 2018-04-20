package io.vitess.service.common;

import io.vitess.dao.base.SpecialSkuDao;
import io.vitess.enums.SpecialSkuType;
import io.vitess.model.base.SpecialSku;
import io.vitess.service.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @classname com.jumbo.manager.baseinfo.SpecialSkuManagerImpl
 * @author hailiang.jiang
 * @date 2015年2月4日 上午11:11:18
 * @version: v1.0.0
 * @see
 */
@Service("specialSkuManager")
public class SpecialSkuManagerImpl extends BaseManagerImpl implements SpecialSkuManager {

	private static final long serialVersionUID = -8798998694239091629L;
	
	@Autowired
	private SpecialSkuDao specialSkuDao;

	@Override
	public SpecialSku findSpecialSku(Long shopId, String extCode, SpecialSkuType skuType) {
		return specialSkuDao.findSpecialSku(shopId, extCode, skuType);
	}

}
