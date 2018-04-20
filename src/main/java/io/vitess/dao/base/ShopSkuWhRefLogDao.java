package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.ShopSkuWhRefLog;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ShopSkuWhRefLogDao extends BaseMapper<ShopSkuWhRefLog> {
	

}
