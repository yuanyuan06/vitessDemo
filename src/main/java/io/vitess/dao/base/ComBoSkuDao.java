package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.ComboSku;

public interface ComBoSkuDao extends BaseDao<ComBoSkuDao> {


	ComboSku findByCodeAndShopId(String code,  Long shopId);


}
