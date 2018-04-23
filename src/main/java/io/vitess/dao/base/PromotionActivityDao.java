package io.vitess.dao.base;


import io.vitess.command.PromotionActivityCommand2;
import io.vitess.common.BaseDao;
import io.vitess.model.base.PromotionActivity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface PromotionActivityDao extends BaseDao<PromotionActivity> {

    PromotionActivity findPromotionActivityByName(String name, Long shopId);

    PromotionActivity findPromotionActivityById(Long id);

    PromotionActivity findPromotionByIdShopId( Long id,  Long shopId);

    PromotionActivity findPromotionActivityByNameExitId( String name,  Long id,  Long shopId);

    /**
     * 根据店铺id和时间范围查找促销活动
     * @param shopId
     * @param date
     * @return
     */
    List<PromotionActivityCommand2> findAllPromotionActivityForShopAndTime(Long shopId, Date date);
    
    List<PromotionActivityCommand2> findAllPromotionActivity();
}
