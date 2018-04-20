package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.TransReginTemplateDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface TransReginTemplateDetailDao extends BaseMapper<TransReginTemplateDetail> {
	
    void deleteRTRD(Long tempId);

    List<TransReginTemplateDetail> findRTRDListByDId(Long tempId);
} 
