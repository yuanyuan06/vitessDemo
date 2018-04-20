package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.ComboSku;

public interface ComBoSkuDao extends BaseMapper<ComBoSkuDao> {


	ComboSku findByCodeAndShopId(String code,  Long shopId);


}
