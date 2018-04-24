package io.vitess.service.common;

import io.vitess.command.SalesOrderLineCommand;
import io.vitess.model.base.ShopSkuWhRef;
import io.vitess.model.base.CompanyShop;
import io.vitess.service.BaseManager;
import io.vitess.service.BusinessException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 店铺商品仓库编码关系维护
 * 
 * @className com.jumbo.manager.baseinfo.ShopSkuWhRefManager
 * @author hailiang.jiang
 * @date 2014年10月9日 下午8:57:58
 */
public interface ShopSkuWhRefManager extends BaseManager {
    public static final String EXCEL_SHOP_SKU_WH_REF_RESULT_LIST = "excel/tpl_shop_sku_wh_ref_result_list.xls";
    public static final String EXCEL_SHOP_SKU_WH_REF_TEMPLATE = "excel/tpl_export_shopskuwhref_by_shop.xls";

    
    /**
     * 导出模板数据
     * @param ouId
     * @return
     */
    Map<String, Object> findShopSkuWhRefByShopId(Long ouId);
    
	/**
	 *
	 * @methodName com.jumbo.manager.baseinfo.ShopSkuWhRefManager.findByShopAndSku
	 * @author hailiang.jiang
	 * @date 2014年10月10日 下午2:53:57
	 * @param shop
	 * @param productCode
	 * @param paymentTime
	 * @return
	 */
	ShopSkuWhRef findByShopAndSku(CompanyShop shop, String productCode, Date paymentTime) throws BusinessException;
	
	/**
	 * 按商品所属仓库分类
	 *
	 * @methodName com.jumbo.manager.baseinfo.ShopSkuWhRefManager.matchWarehouse
	 * @author hailiang.jiang
	 * @date 2014年10月16日 下午12:56:37
	 * @param shop
	 * @param salesOrderLineCommandList
	 * @return
	 * @throws BusinessException
	 */
	Map<String, List<SalesOrderLineCommand>> matchWarehouse(CompanyShop shop, List<SalesOrderLineCommand> salesOrderLineCommandList) throws BusinessException;
	
}
