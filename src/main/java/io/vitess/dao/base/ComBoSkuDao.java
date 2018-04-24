package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.ComboSku;
import org.apache.ibatis.annotations.Param;

public interface ComBoSkuDao extends BaseDao<ComBoSkuDao> {


	ComboSku findByCodeAndShopId(@Param("code") String code, @Param("shopId") Long shopId);


}
