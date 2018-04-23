package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.InventoryStatus;

import java.util.List;

public interface InventoryStatusDao extends BaseMapper<InventoryStatus> {
	
    InventoryStatus findByName(String name);
    InventoryStatus findByCode(String code);
    List<InventoryStatus> findAllAvailableInvStatus();
	
}
