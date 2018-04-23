package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.so.ServiceInstallCompany;

import java.util.List;

public interface ServiceInstallCompanyDao extends BaseMapper<ServiceInstallCompany> {
    
	public List<ServiceInstallCompany> findServiceInstallCompanyList( Long shopId,  String province,  String city,  String district);
	
	void deleteInstallCompByShopId( Long shopId);

	public String findInstallCompanyName( Long shopId);

}
