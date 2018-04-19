package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.SkuAppointment;

import java.util.Date;

/**
 * @author YSH4807
 * @date 2018/4/19 21:15
 */
public interface SpecifySkuAppointmentDao extends BaseMapper<SkuAppointment> {

    SkuAppointment queryAppointmentSku(Long skuId, Long shopId, Date payTime);

}
