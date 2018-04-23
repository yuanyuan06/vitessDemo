package io.vitess.dao.base;

import io.vitess.common.BaseDao;
import io.vitess.model.base.DefaultTransTemplete;

import java.util.List;

/**
 * <p>
  * 物流推荐主表 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface DefaultTransTempleteDao extends BaseDao<DefaultTransTemplete> {


    DefaultTransTemplete findByCode(String code);

    List<DefaultTransTemplete> findAllDTT();
}