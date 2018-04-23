package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.DefaultTransTemplete;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * <p>
  * 物流推荐主表 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface DefaultTransTempleteDao extends BaseMapper<DefaultTransTemplete> {


    DefaultTransTemplete findByCode(String code);

    List<DefaultTransTemplete> findAllDTT();
}