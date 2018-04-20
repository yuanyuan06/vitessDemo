package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.mq.CompanyShop;
import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 店铺 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface CompanyShopDao extends BaseMapper<CompanyShop> {

    List<CompanyShop> findShopListGeneralOrder();

    CompanyShop getByOuId(Long ouid);
}