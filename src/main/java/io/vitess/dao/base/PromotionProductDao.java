package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.PromotionProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PromotionProductDao extends BaseDao<PromotionProduct> {

    List<PromotionProduct> findByProductRuleId( Long ruleId,  Long shopId);
    
}
