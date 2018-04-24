package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.Sku;
import org.apache.ibatis.annotations.Param;

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
public interface SkuDao extends BaseDao<Sku> {


    Sku findByProductId(Long productId);


    Sku findSkuByExtensionCode1( String extensionCode1);

    Sku findSkuByExtCodeBrtCode(@Param("extCode") String extCode, @Param("brtCode") String brtCode, @Param("whCustomerCode") String whCustomerCode);

    List<String> findByCodes(Set<String> newExtentionCodes, String businessRegionType, String whCustomerCode);

    Sku findByCode(String code);
}