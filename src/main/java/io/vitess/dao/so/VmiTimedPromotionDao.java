package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.so.VmiTimedPromotion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
  * vmi促销主数据 Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface VmiTimedPromotionDao extends BaseMapper<VmiTimedPromotion> {


    List<VmiTimedPromotion> findVmiPromotionList( int status,  String proType,  Long shopId);

    List<VmiTimedPromotion> findVmiPromotionList2( int status,  String proType,  Long shopId,  Date payTime,  BigDecimal totalActual);

    Integer updateGiftQuota( Long id,  Long shopId);
}