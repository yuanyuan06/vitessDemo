package io.vitess.service.common;

import io.vitess.model.base.DefaultTransTemplete;
import io.vitess.model.base.District;
import io.vitess.model.base.InventoryStatus;
import io.vitess.model.base.Transportator;
import io.vitess.service.BaseManager;

import java.util.List;

public interface BaseInfoManager extends BaseManager {
    
    /**
     * 查找店铺下物流服务商(非COD)
     * 
     * @return
     */
    List<Transportator> findTransIsSupportCodList();
    
    /**
     * 查询所有可用的物流商
     * @return
     */
    List<Transportator> findTransportatorList();
    
    /**
     * 查询物流商
     * @param expCode
     * @return
     */
    Transportator findTransportatorByCode(String expCode);
    
    /**
     * 查询所有国家
     * 
     * @return
     */
    List<District> findCountry();
    
    /**
     * 根据国家查询所有省
     * 
     * @return
     */
    List<District> findProvinceByCountry(String country);
    
    /**
     * 根据省查询所有城市
     * 
     * @return
     */
    List<District> findCityByProvince(String province);

    /**
     * 根据城市查询所有地区
     * 
     * @return
     */
    List<District> findDistrictByCity(String province, String city);
    
    /**
     * 查询所有省/市
     * 
     * @return
     */
    List<District> findProvince();
    
    /**
     * 查找所有有效的库存状态
     * @return
     */
    List<InventoryStatus> findAllAvailableInvStatus();
    

    List<DefaultTransTemplete> findAllDTT();
    
}
