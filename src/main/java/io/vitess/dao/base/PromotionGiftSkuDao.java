package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface PromotionGiftSkuDao extends BaseMapper<PromotionGiftSku> {

    List<PromotionGiftSku> findBypromotionGiftId( Long promotionGiftId, Long shopId);
    
    Integer updateUsedGiftNum(Long id,int times, Long shopId);
	
}
