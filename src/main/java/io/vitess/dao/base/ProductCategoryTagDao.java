package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.ProductCategoryTag;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface ProductCategoryTagDao extends BaseMapper<ProductCategoryTag> {
    
    ProductCategoryTag getPromotionActivityByName(String name,  Long shopId);

    ProductCategoryTag getPromotionActivityByNameExtId(String name,  Long id,  Long shopId);

    List<ProductCategoryTag> findCategoryTagByProduct( Long productId,  Long shopId, RowMapper<ProductCategoryTag> rowMapper);

    ProductCategoryTag getProductCategoryTagByCode( String code,  Long shopId);

    List<String> findCategoryCodeByProduct( Long productId,  Long shopId, RowMapper<String> rowMapper);

}
