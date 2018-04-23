package io.vitess.dao.so;


import io.vitess.common.BaseDao;
import io.vitess.service.so.OrderLocationMapping;

public interface OrderLocationMappingDao extends BaseDao<OrderLocationMapping> {
	
	OrderLocationMapping findByBusinessType(Long shopId,  String skuCode,  Integer businessType);

}
