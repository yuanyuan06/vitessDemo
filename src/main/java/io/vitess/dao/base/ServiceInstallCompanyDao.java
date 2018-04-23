package io.vitess.dao.base;


import io.vitess.common.BaseDao;
import io.vitess.model.so.ServiceInstallCompany;

import java.util.List;

public interface ServiceInstallCompanyDao extends BaseDao<ServiceInstallCompany> {
    
	public List<ServiceInstallCompany> findServiceInstallCompanyList( Long shopId,  String province,  String city,  String district);
	
	void deleteInstallCompByShopId( Long shopId);

	public String findInstallCompanyName( Long shopId);

}
