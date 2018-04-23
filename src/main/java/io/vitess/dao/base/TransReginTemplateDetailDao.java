package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.TransReginTemplateDetail;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface TransReginTemplateDetailDao extends BaseDao<TransReginTemplateDetail> {
	
}
