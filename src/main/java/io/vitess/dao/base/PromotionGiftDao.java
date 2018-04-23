package io.vitess.dao.base;


import io.vitess.command.PromotionGiftCommand;
import io.vitess.common.BaseDao;
import io.vitess.model.base.PromotionGift;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PromotionGiftDao extends BaseDao<PromotionGift> {

    List<PromotionGiftCommand> findGiftByRuleID( long RuleID);

    List<PromotionGift> findByProductRuleId( Long ruleId,  Long shopId);
    
    void deletePromotionGiftByRuleId( Long ruleId);
}
