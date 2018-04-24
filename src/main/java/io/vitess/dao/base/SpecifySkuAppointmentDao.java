package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.base.SkuAppointment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author YSH4807
 * @date 2018/4/19 21:15
 */
public interface SpecifySkuAppointmentDao extends BaseDao<SkuAppointment> {

    SkuAppointment queryAppointmentSku(@Param("skuId") Long skuId, @Param("shopId") Long shopId, @Param("payTime") Date payTime);

}
