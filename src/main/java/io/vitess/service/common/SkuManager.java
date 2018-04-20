package io.vitess.service.common;

import io.vitess.model.base.Sku;
import io.vitess.model.base.SkuWarehouseRel;
import io.vitess.service.BaseManager;

import java.util.List;

public interface SkuManager extends BaseManager {
    
    Sku findById(Long id);

    Sku findByCode(String code);
    
    Sku findSkuByExtCodeBrtCode(String extCode, String brtCode, String whCustomerCode);

    List<SkuWarehouseRel> findSkuWarehouseRel(Long shopId, String skuCodeStr);

	String findSkuByExtCode(String skuCode, String businessRegionType, String whCustomerCode);

}
