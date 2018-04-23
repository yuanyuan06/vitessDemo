package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.command.SoServiceLineCommand;
import io.vitess.model.so.SoServiceLine;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * <p>
  * 订单服务行 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface SoServiceLineDao extends BaseMapper<SoServiceLine> {


    public List<SoServiceLine> findSoServiceLineList(Long salesOrderId);

    public List<SoServiceLine> findSoServiceLineByShopId(Long salesOrderId, Long shopId);

    public List<SoServiceLineCommand> findSoServiceLineListBySoAndServiceLine(Long salesOrderId,
                                                                                List<Long> soServiceLineIdList,
                                                                                    Long shopId);
}