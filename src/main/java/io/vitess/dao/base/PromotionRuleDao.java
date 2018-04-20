package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.command.PromotionRuleCommand;
import io.vitess.model.base.PromotionRule;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PromotionRuleDao extends BaseMapper<PromotionRule> {
	
    PromotionRule getPromotionRuleLevelIsExits(int ruleLevel,  Long promotionId);

    List<PromotionRuleCommand> getPromotionRulByProId(Long id);

    Integer updateUsedGiftTimesByRuleIdAndTimes( Long ruleId,  int times,  Long shopId);

    Integer findPromotionRulById( Long id,  Long shopId);

    List<PromotionRule> findAllRuleList();

    List<PromotionRule> findRuleList( Long promotionId,  Long shopId);

}
