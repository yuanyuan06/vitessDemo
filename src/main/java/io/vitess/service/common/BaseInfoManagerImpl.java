package io.vitess.service.common;

import io.vitess.dao.base.*;
import io.vitess.model.base.DefaultTransTemplete;
import io.vitess.model.base.District;
import io.vitess.model.base.InventoryStatus;
import io.vitess.model.base.Transportator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("baseInfoManager")
public class BaseInfoManagerImpl implements BaseInfoManager {
    private static final long serialVersionUID = -7479360319162471088L;
    
    protected static final Logger log = LoggerFactory.getLogger(BaseInfoManagerImpl.class);
    
    @Autowired
    private TransportatorDao transportatorDao;
    
    @Autowired
    private DistrictDao districtDao;
    
    @Autowired
    private InventoryStatusDao inventoryStatusDao;
    
    @Autowired
    private WorkTaskLogDao workTaskLogDao;
    
    @Autowired
    private DefaultTransTempleteDao defaultTransTempleteDao;
    

    // 店铺下物流服务商查询(非COD)
    @Override
    public List<Transportator> findTransIsSupportCodList() {
        return transportatorDao.findTransIsSupportCodList();
    }
    
    //查询所有可用的物流商
    @Override
    public List<Transportator> findTransportatorList() {
        return transportatorDao.findAll();
    }
    
    //@Cacheable(value="findTransportatorByCode",key="#expCode")
	@Override
	public Transportator findTransportatorByCode(String expCode) {
		return transportatorDao.findByCode(expCode);
	}
    
    // 查询出所有省/市
    @Override
    public List<District> findProvince() {
        return districtDao.findProvince();
    }
    
    @Override
    public List<InventoryStatus> findAllAvailableInvStatus() {
        return inventoryStatusDao.findAllAvailableInvStatus();
    }
    
    // 查询出所有国家
    @Override
    public List<District> findCountry() {
        return districtDao.findCountry();
    }
    
    // 根据国家查询出所有省
    @Override
    public List<District> findProvinceByCountry(String country) {
         String countrya=country.trim();
         return districtDao.findProvinceByCountry(countrya);
    }
    
    // 根据省查询出所有市
    @Override
    public List<District> findCityByProvince(String province) {
        return districtDao.findCity(province);
    }

    // 根据市查询出所有地区
    @Override
    public List<District> findDistrictByCity(String province, String city) {
        return districtDao.findDistrict(province, city );
    }

    @Override
    public List<DefaultTransTemplete> findAllDTT() {
          return defaultTransTempleteDao.findAllDTT();
    }

}
