package io.vitess.service.common;

import io.vitess.enums.SpecialSkuType;
import io.vitess.model.base.SpecialSku;
import io.vitess.service.BaseManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @classname com.jumbo.manager.baseinfo.SpecialSkuManager
 * @description TODO
 * @author hailiang.jiang
 * @date 2015年2月4日 上午11:10:30
 * @version: v1.0.0
 * @see
 */
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public interface SpecialSkuManager extends BaseManager {
	
	/**
	 * 
	 * @methodName com.jumbo.manager.baseinfo.SpecialSkuManager.findSpecialSku
	 * @author hailiang.jiang
	 * @date 2015年2月4日 下午3:12:31
	 * @version: v1.0.0
	 */
	SpecialSku findSpecialSku(Long shopId, String extCode, SpecialSkuType skuType);

}
