package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.ComboSkuDetail;

import java.util.List;

public interface ComboSkuDetailDao extends BaseMapper<ComboSkuDetail> {


    List<ComboSkuDetail> findDetailByComBoId(Long comBoSkuId);

    List<ComboSkuDetail> findComBoSkuDetailByCodeAndShopId(String comboCode, Long shopId);

    void deleteForComBoSkuId(Long comBoSkuId);
    
}
