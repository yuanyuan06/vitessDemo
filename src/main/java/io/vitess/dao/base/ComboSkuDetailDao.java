package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.ComboSkuDetail;

import java.util.List;

public interface ComboSkuDetailDao extends BaseDao<ComboSkuDetail> {


    List<ComboSkuDetail> findDetailByComBoId(Long comBoSkuId);

}
