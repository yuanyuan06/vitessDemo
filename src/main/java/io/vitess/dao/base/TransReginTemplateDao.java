package io.vitess.dao.base;


import io.vitess.command.TransReginTemplateCommand;
import io.vitess.common.BaseDao;
import io.vitess.model.base.TransReginTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface TransReginTemplateDao extends BaseDao<TransReginTemplate> {
	
    /**
     * 查找所有
     * 
     * @return
     */

    TransReginTemplate findByCode( String code);
	
	List<TransReginTemplateCommand> findTransRegionTemplateListByShop(Long shopId, RowMapper<TransReginTemplateCommand> rowMapper);
	
} 
