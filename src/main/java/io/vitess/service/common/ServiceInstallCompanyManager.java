package io.vitess.service.common;

import io.vitess.model.so.ServiceInstallCompany;
import io.vitess.service.BaseManager;
import io.vitess.service.BusinessException;

import java.util.List;

public interface ServiceInstallCompanyManager extends BaseManager {
	
	public List<ServiceInstallCompany> findInstallCompany(Long shopId, String province, String city, String distinct);
	
	public ServiceInstallCompany findServiceInstallCompany(Long shopId, String province, String city, String distinct) throws BusinessException;

	/**根据SHOPID删除安装公司数据**/
	public void deleteInstallCompByShopId(Long shopId);
}
