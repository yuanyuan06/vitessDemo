package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.common.WorkFlow;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface WorkFlowDao extends BaseMapper<WorkFlow> {

    WorkFlow findByCode(String workFlowCode);

}
