package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.PromotionGiftSku;

import java.util.List;

public interface PromotionGiftSkuDao extends BaseDao<PromotionGiftSku> {

    List<PromotionGiftSku> findBypromotionGiftId( Long promotionGiftId, Long shopId);
    
    Integer updateUsedGiftNum(Long id,int times, Long shopId);
	
}
