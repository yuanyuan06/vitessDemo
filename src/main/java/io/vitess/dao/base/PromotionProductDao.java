package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.command.PromotionProductCommand;
import io.vitess.model.base.PromotionProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PromotionProductDao extends BaseMapper<PromotionProduct> {

    List<PromotionProduct> findByProductRuleId( Long ruleId,  Long shopId);
    
}
