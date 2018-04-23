package io.vitess.dao.so;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.so.PlatformPromotion;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hh
 * @since 2018-04-19
 */
public interface PlatformPromotionDao extends BaseMapper<PlatformPromotion> {

    List<PlatformPromotion> findSoPlatformPromotionBySoId(Long soId,  Long shopId);

    List<PlatformPromotion> findBySoLineId(Long soId, Long slId,  Long shopId);

    List<PlatformPromotion> findBySoLineIdAndPlatformLineId(Long soId, Long platformLineId,  Long shopId);


}