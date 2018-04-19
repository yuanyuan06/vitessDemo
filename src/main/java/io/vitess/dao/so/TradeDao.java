package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.so.Trade;

/**
 * <p>
  * 交易头 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface TradeDao extends BaseMapper<Trade> {

    Trade findByPlatformOrderCode(String platformOrderCode,Long shopId);
}