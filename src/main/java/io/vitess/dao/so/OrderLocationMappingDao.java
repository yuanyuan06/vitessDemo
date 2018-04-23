package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.service.so.OrderLocationMapping;

public interface OrderLocationMappingDao extends BaseMapper<OrderLocationMapping> {
	
	OrderLocationMapping findByBusinessType(Long shopId,  String skuCode,  Integer businessType);

}
