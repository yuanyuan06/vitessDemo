package io.vitess.dao.base;


import io.vitess.command.PromotionRuleCommand;
import io.vitess.common.BaseDao;
import io.vitess.model.base.PromotionRule;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PromotionRuleDao extends BaseDao<PromotionRule> {
	
    PromotionRule getPromotionRuleLevelIsExits(int ruleLevel,  Long promotionId);

    List<PromotionRuleCommand> getPromotionRulByProId(Long id);

    Integer updateUsedGiftTimesByRuleIdAndTimes( Long ruleId,  int times,  Long shopId);

    Integer findPromotionRulById( Long id,  Long shopId);

    List<PromotionRule> findAllRuleList();

    List<PromotionRule> findRuleList( Long promotionId,  Long shopId);

}
