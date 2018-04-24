package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SalesOrderLineCommand;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.so.SalesOrder;
import io.vitess.service.BaseManager;

import java.util.List;
import java.util.Map;

/**
 * @author hailiang.jiang
 * @date 2014年7月21日
 * @description SalesOrderSplitManager
 */
public interface SalesOrderSplitManager extends BaseManager {
	
	public static final String[] SPLIT_IGNORE_SO_PROPERTIES = {};
	public static final String[] SPLIT_IGNORE_SO_DELIVERY_INFO_PROPERTIES = {"salesOrder"};
	public static final String[] SPLIT_IGNORE_SO_MEMBER_INFO_PROPERTIES = {"salesOrder"};
	public static final String[] SPLIT_IGNORE_SO_LINE_PROPERTIES = {"sku", "proLog"};
	public static final String[] SPLIT_IGNORE_SO_LINE_PACKAGE_PROPERTIES = {"salesOrder", "salesOrderLine"};
	
	public static final String[] IGNORE_SO_PROPERTIES = {};
	public static final String[] IGNORE_SO_DELIVERY_INFO_PROPERTIES = {};
	public static final String[] IGNORE_SO_MEMBER_INFO_PROPERTIES = {};
	public static final String[] IGNORE_SO_LINE_PROPERTIES = {};
	public static final String[] IGNORE_SO_LINE_PACKAGE_PROPERTIES = {};



	/**
	 * 拆分订单：根据商品的默认仓
	 *
	 * @methodName com.jumbo.manager.sales.SalesOrderSplitManager.splitTradeByShopSkuWh
	 * @author hailiang.jiang
	 * @date 2014年10月16日 下午1:33:43
	 * @param srcSoCmd
	 * @param whSoMap
	 * @return
	 */
	public List<SalesOrderCommand> splitTradeByShopSkuWh(SalesOrderCommand srcSoCmd, Map<String, List<SalesOrderLineCommand>> whSoMap);

	/**
	 * 拆分订单：按商品数量拆
	 *
	 * @methodName com.jumbo.manager.sales.SalesOrderSplitManager.splitTradeBySkuNum
	 * @author hailiang.jiang
	 * @date 2014年12月3日 下午2:55:37
	 * @param shop
	 * @param srcSoCmd
	 * @return
	 */
	public List<SalesOrderCommand> splitTradeBySkuNum(CompanyShop shop, SalesOrderCommand srcSoCmd);

	/**
	 * 拆分订单：O2O订单，按商品服务类型[有售后、无售后]拆分
	 *
	 * @methodName com.jumbo.manager.sales.SalesOrderSplitManager.splitTradeByO2oSalesServiceType
	 * @author hailiang.jiang
	 * @date 2015年2月3日 下午8:54:04
	 * @version: v1.0.0
	 */
	public List<SalesOrderCommand> splitTradeByO2oSalesServiceType(CompanyShop shop, SalesOrderCommand srcSoCmd);



	/**
	 * 为List中填充短信参数信息
	 * @author hailiang.jiang
	 * @date 2015年10月17日 上午9:17:47
	 * @param persistSo
	 * @param platformOrderCodeNs
	 * @param soLineSkuList
	 */
	public void fillSpiltSmsParam(SalesOrder persistSo, List<String> platformOrderCodeNs, List<String> soLineSkuList, Long shopId);

	/**
	 * 指定sku分仓
	 * @param shop
	 * @param srcSoCmd
	 */
	List<SalesOrderCommand> splitOrderDesignatedWhBySku(CompanyShop shop, SalesOrderCommand srcSoCmd);
}