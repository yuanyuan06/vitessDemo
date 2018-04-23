package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.Transportator;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * <p>
  * 物流商主数据 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface TransportatorDao extends BaseMapper<Transportator> {

    List<Transportator> findAll();

    List<Transportator> findTransIsSupportCodList();

    List<Transportator> findTransAllDistinctByName( RowMapper<Transportator> rowMapper);

    String findNameByExpCode( String expCode);

    Transportator findByCode( String expCode);

    Transportator findByName( String name);
}