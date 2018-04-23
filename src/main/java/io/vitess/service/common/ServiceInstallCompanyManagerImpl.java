package io.vitess.service.common;

import io.vitess.common.ErrorCode;
import io.vitess.dao.base.ServiceInstallCompanyDao;
import io.vitess.model.so.ServiceInstallCompany;
import io.vitess.service.BaseManagerImpl;
import io.vitess.service.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @classname com.jumbo.manager.baseinfo.ServiceInstallCompanyManagerImpl
 * @author hailiang.jiang
 * @date 2015年3月6日 下午2:10:30
 * @version: v1.0.0
 * @see
 */
@Transactional
@Service("serviceInstallCompanyManager")
public class ServiceInstallCompanyManagerImpl extends BaseManagerImpl implements ServiceInstallCompanyManager {

    private static final long serialVersionUID = 4358939031678951928L;
    
    @Autowired
    private ServiceInstallCompanyDao serviceInstallCompanyDao;
    
	@Override
	public List<ServiceInstallCompany> findInstallCompany(Long shopId, String province, String city, String distinct) {
		return serviceInstallCompanyDao.findServiceInstallCompanyList(shopId, province, city, distinct);
	}
	
	/**
	 * 
	 * @Description:  修改获取安装公司名称逻辑
	 * @author zhiyong.shi
	 * 2017年1月3日
	 */
    @Override
    public ServiceInstallCompany findServiceInstallCompany(Long shopId, String province, String city, String distinct) throws BusinessException {
    	
    	String companyName = serviceInstallCompanyDao.findInstallCompanyName(shopId);
    	if("".equals(companyName) || companyName == null){
    		throw  new BusinessException(ErrorCode.CUSTOMIZATION_TIP, new Object[] {String.format("该订单需要上门安装，但店铺下查不到安装公司信息")});
    	}
    	ServiceInstallCompany sic = new ServiceInstallCompany();
    	sic.setCompanyName(companyName);
    	sic.setCompanyCode(companyName);
		return sic; 
	}

	/**根据SHOPID删除安装公司数据**/
	@Override
	public void deleteInstallCompByShopId(Long shopId) {
		serviceInstallCompanyDao.deleteInstallCompByShopId(shopId);
	}

}
