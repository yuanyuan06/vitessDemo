package io.vitess.dao.so;

import io.vitess.common.BaseDao;
import io.vitess.model.so.SoInvFlow;
import org.apache.ibatis.annotations.Param;

/**
 * @author YSH4807
 * @date 2018/4/26 20:48
 */
public interface SoInvFlowDao extends BaseDao<SoInvFlow> {

//    SoInvFlow getInvBySku(@Param("sku") String sku);

    void updateSkuInv(@Param("sku") String sku, @Param("qty") int qty);
}
