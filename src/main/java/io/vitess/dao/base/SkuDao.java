package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.Sku;

import java.util.List;
import java.util.Set;

/**
 * <p>
  * 商品sku表 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface SkuDao extends BaseMapper<Sku> {


    Sku findByProductId(Long productId);


    Sku findSkuByExtensionCode1( String extensionCode1);

    Sku findSkuByExtCodeBrtCode( String extCode, String brtCode, String whCustomerCode);

    List<String> findByCodes(Set<String> newExtentionCodes, String businessRegionType, String whCustomerCode);

    Sku findByCode(String code);
}