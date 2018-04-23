package io.vitess.dao.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.vitess.model.base.PackageSkuNum;

public interface PackageSkuNumDao extends BaseMapper<PackageSkuNum> {

	/**
	 * 根据extCode查询PackageSkuNum
	 *
	 * @methodName com.jumbo.dao.baseinfo.PackageSkuNumDao.findPackageSkuNum
	 * @author hailiang.jiang
	 * @date 2014年12月3日 下午3:45:04
	 * @param shopId
	 * @param extCode
	 * @return
	 */
	PackageSkuNum findPackageSkuNum(Long shopId, String extCode);
	
}
