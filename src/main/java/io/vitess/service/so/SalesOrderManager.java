package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SalesOrderLineCommand;
import io.vitess.model.base.User;
import io.vitess.enums.SalesMode;
import io.vitess.enums.SalesOrderType;
import io.vitess.model.base.SkuAppointment;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.so.SalesOrder;
import io.vitess.service.BaseManager;

import java.util.List;
import java.util.Map;

public interface SalesOrderManager extends BaseManager {

	SalesOrder generateSalesOrderByCommand(SalesOrder newSo, SalesOrderCommand soCommand, User creator, SalesOrderType orderType, Integer isNeededAssign, Map<String, String> sourceMap, SkuAppointment skuAppointment);

	/**
	 * 自动拆单
	 * */
	List<SalesOrderCommand> splitTrade(SalesOrderCommand originalSoCmd);
	
	/**
	 * 根据商品所属发货仓库-自动拆单
	 *
	 * @methodName com.jumbo.manager.sales.SalesOrderManager.splitTradeByShopSkuWh
	 * @author hailiang.jiang
	 * @date 2014年10月9日 下午7:48:51
	 * @param soCmd
	 * @param shop
	 * @return
	 */
	List<SalesOrderCommand> splitTradeByShopSkuWh(SalesOrderCommand soCmd, CompanyShop shop);

	/**
	 * 销售模式交集
	 * */
	SalesMode retainSalesModeByShopAndProduct(String salesModesStr1, String salesModesStr2);

	/**
	 * 应用平摊让利与积分
	 * */
	void assignPromotionfee(SalesOrderCommand soCmd);

	
	/**
	 * O2O-验证分仓
	 * @methodName com.jumbo.manager.sales.SalesOrderManager.validateBranchWarehouseCode
	 * @description
	 * @author hailiang.jiang
	 * @date 2015年2月3日 下午7:37:32
	 * @version: v1.0.0
	 */
	String validateBranchWarehouseCode(CompanyShop shop, SalesOrderCommand soCmd, List<SalesOrderLineCommand> soLineCommandList);

}