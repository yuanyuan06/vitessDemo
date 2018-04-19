package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.mq.TbTrade;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
  * 平台交易数据 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface TbTradeDao extends BaseMapper<TbTrade> {

    List<TbTrade> findTbTradeNotSync();

    void updateTbTrade(Long id,Integer syncStatus);
}