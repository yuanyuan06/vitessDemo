package io.vitess.service.common;

import io.vitess.model.mq.CompanyShop;

/**
 * 店铺信息
 * @author fanht
 *
 */
public interface CompanyShopManager {
    
    /**
     * shopId查询店铺（使用缓存）
     * @param shopId
     * @return
     */
    CompanyShop findShopInfoByShopId(Long shopId);
}
