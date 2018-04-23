package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.common.DefaultTransTempleteDetailCommand;
import io.vitess.model.base.DefaultTransTempleteDetail;

import java.util.List;

/**
 * <p>
  * 物流推荐明细表 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface DefaultTransTempleteDetailDao extends BaseDao<DefaultTransTempleteDetail> {

    List<DefaultTransTempleteDetailCommand> findDTTDByTempId(Long tempId);

    void deleteDTTD(Long tempId);
}