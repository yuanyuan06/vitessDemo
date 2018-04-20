package io.vitess.service.common;

import io.vitess.command.PromotionActivityCommand2;
import io.vitess.command.PromotionGiftCommand;
import io.vitess.command.PromotionSkuCommand;
import io.vitess.service.BaseManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface PromotionCashManager extends BaseManager {

    void reloadAllPromotions();

    Map<Long, PromotionGiftCommand> applyPromotion(PromotionActivityCommand2 promotion, BigDecimal skuTotalFee, Map<Long, PromotionSkuCommand> skuInfoComboMap , Map<Long, PromotionSkuCommand> skuInfoNotComboMap, Map<Long, PromotionGiftCommand> proGiftMap);

    void refreshPromotionMap();
    
    List<PromotionActivityCommand2> queryPromotionCash(Long shopId);
   
    /**
    * 根据时间和店铺查找促销活动
    */
    List<PromotionActivityCommand2> findAllPromotionActivityForShopAndTime(Long shopId, Date date);
}
