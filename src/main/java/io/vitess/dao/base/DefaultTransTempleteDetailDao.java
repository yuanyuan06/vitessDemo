package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.common.DefaultTransTempleteDetailCommand;
import io.vitess.model.base.DefaultTransTempleteDetail;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * <p>
  * 物流推荐明细表 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface DefaultTransTempleteDetailDao extends BaseMapper<DefaultTransTempleteDetail> {

    List<DefaultTransTempleteDetailCommand> findDTTDByTempId(Long tempId);

    void deleteDTTD(Long tempId);
}