package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.SkuWarehouseRel;

import java.util.List;

public interface SoSkuWhCodeRelDao extends BaseMapper<SkuWarehouseRel> {
	
    List<SkuWarehouseRel> querySkuWarehouseRelByParam(Long shopId, String skuCodeStr);

}
