package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.command.PromotionGiftCommand;
import io.vitess.model.base.PromotionGift;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PromotionGiftDao extends BaseMapper<PromotionGift> {

    List<PromotionGiftCommand> findGiftByRuleID( long RuleID);

    List<PromotionGift> findByProductRuleId( Long ruleId,  Long shopId);
    
    void deletePromotionGiftByRuleId( Long ruleId);
}
