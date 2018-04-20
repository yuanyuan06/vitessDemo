/**
 * 
 */
package io.vitess.service.so;

import io.vitess.command.SalesOrderCommand;
import io.vitess.command.SalesOrderLineCommand;
import io.vitess.model.mq.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import io.vitess.model.so.PlatformPromotion;
import io.vitess.model.so.SalesOrder;
import io.vitess.model.so.SalesOrderLine;
import io.vitess.service.BaseManager;

import java.util.List;

/**
 * @author bacui.lu
 * 2016年2月18日
 */
public interface PlatformPromotionManager extends BaseManager {

	/**组装促销信息
	 * @author bacui.lu
	 * @date 2016年2月18日
	 * @param shop
	 * @param soLog
	 * @param soCmd
	 */
	void constructSoPromotionInfo(CompanyShop shop, MqSoLog soLog, SalesOrderCommand soCmd);
	
	/**构建cmd的促销数据
	 * @author bacui.lu
	 * @date 2016年3月7日
	 * @param linePlatformPromotions
	 * @param solCmd
	 * @param sl
	 * @param newSo
	 */
	void genratorPlatformLinePromotion(List<PlatformPromotion> linePlatformPromotions,
											  SalesOrderLineCommand solCmd, SalesOrderLine sl, SalesOrder newSo);

	/**保存促销数据到业务表
	 * @author bacui.lu
	 * @date 2016年3月7日
	 * @param s	
	 * @param soCmd
	 * @param linePlatformPromotions 
	 */
	void savePlatformPromotions(SalesOrder s, SalesOrderCommand soCmd, List<PlatformPromotion> linePlatformPromotions);

	

}
