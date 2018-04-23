package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.enums.SpecialSkuType;
import io.vitess.model.base.SpecialSku;

/**
 * 特殊商品-dao
 * 
 * @classname com.jumbo.dao.baseinfo.SpecialSkuDao
 * @description TODO
 * @author hailiang.jiang
 * @date 2015年2月4日 上午11:07:49
 * @version: v1.0.0
 * @see
 */
public interface SpecialSkuDao extends BaseDao<SpecialSku> {

	SpecialSku findSpecialSku(Long shopId, String extCode, SpecialSkuType skuType);

	void deleteSpecialSkuByShopId(Long shopId);
	
}
