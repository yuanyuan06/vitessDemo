package io.vitess.service.common;

import io.vitess.common.ErrorCode;
import io.vitess.dao.so.ShopWhDao;
import io.vitess.model.mq.ShopWh;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 实现类：店铺仓库关系维护管理
 * 
 * @className com.jumbo.manager.ShopWhManagerImpl
 * @author hailiang.jiang
 * @date 2014年10月16日 上午11:33:41
 */
@Service("shopWhManager")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class ShopWhManagerImpl extends BaseManagerImpl implements ShopWhManager {

	private static final long serialVersionUID = -3476412711146840137L;
	
	@Autowired
	private ShopWhDao shopWhDao;
	
	@Override
	public ShopWh getDefaultWarehouse(Long shopId) throws BusinessException {
		ShopWh shopWh = shopWhDao.findDefaultByShopId(shopId, true);
        if (shopWh == null) {
            throw new BusinessException(ErrorCode.BASEINFO_ERROR_WAREHOUSE_NOT_EXIST);
        }
        return shopWh;
	}
	
	@Override
	public ShopWh findPlatformDefaultWh(Long shopId) throws BusinessException {
		return shopWhDao.findPlatformDefaultWhByShopId(shopId, true);
	}
	
	@Override
	public List<ShopWh> findShopWhList(Long shopId) throws BusinessException {
		return shopWhDao.findShopWhList(shopId);
	}
}
