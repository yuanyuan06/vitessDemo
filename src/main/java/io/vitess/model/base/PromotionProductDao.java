package io.vitess.model.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.command.PromotionProductCommand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PromotionProductDao extends BaseMapper<PromotionProduct> {

    List<PromotionProductCommand> findProductByRuleID(long RuleID);

    List<PromotionProduct> findByProductRuleId( Long ruleId,  Long shopId);
    
    void deletePromotionProductByRuleId( Long ruleId);
    
}
