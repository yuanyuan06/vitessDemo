package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.model.base.CompanyShop;
import io.vitess.service.BaseManager;

import java.util.List;

/**
 * by店铺配置拆单
 * @author zhiyong.shi
 *
 */
public interface SalesOrderStoreSplitManager extends BaseManager {

	static final String DEFAULT_WH =  "DefaultWarehouse";
	/**
	 * 全渠道店铺按照门店CODE拆单
	 * @author zhiyong.shi
	 *
	 */
	public List<SalesOrderCommand> splitOrderByStore(CompanyShop shop, SalesOrderCommand srcSoCmd);
	
	/**
	 * 
	 * @Description: by配送方式拆单
	 * @author zhiyong.shi
	 * 2017年6月19日
	 */
	public List<SalesOrderCommand> splitOrderByShippingMethods(CompanyShop shop, SalesOrderCommand srcSoCmd);
}