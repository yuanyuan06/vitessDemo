package io.vitess.service.so;

import io.vitess.command.SoSuspend;
import io.vitess.enums.SoSpecialType;
import io.vitess.exception.WorkFlowException;
import io.vitess.model.base.WorkTask;
import io.vitess.model.mq.CompanyShop;
import io.vitess.model.so.OrderMember;
import io.vitess.model.so.SalesOrder;
import io.vitess.model.so.SalesOrderLine;
import io.vitess.model.so.SoDeliveryInfo;

import java.util.List;

/**
 * 订单核心判断逻辑
 * @author fanht
 *
 */
public interface SoManager {
	
	/**
	 * 订单是否自动过仓
	 * @param shop 店铺信息
	 * @param transExpCode 物流服务商
	 * @return
	 */
	Boolean getOrderAutoToWh(CompanyShop shop, String transExpCode, SoSpecialType soSpecialType);

	/**
	 * 订单挂起判断
	 * @param task
	 * @param nodeNo
	 * @return
	 * @throws WorkFlowException
	 */
	Boolean needSuspend(WorkTask task, Integer nodeNo) throws WorkFlowException;

	/**
	 * 订单挂起判断(订单信息外部提供)
	 * @param task
	 * @param nodeNo
	 * @return
	 * @throws WorkFlowException
	 */
	Boolean suspendCheck(WorkTask task, Integer nodeNo, SalesOrder so, List<SalesOrderLine> slList, SoDeliveryInfo sdi, OrderMember om)throws WorkFlowException;

	/**
	 * 新的挂起判断
	 * @param shop
	 * @param so
	 * @param slList
	 * @param sdi
	 * @param om
	 * @return
	 * @throws WorkFlowException
	 */
	SoSuspend suspendCheck(CompanyShop shop, SalesOrder so, List<SalesOrderLine> slList, SoDeliveryInfo sdi, OrderMember om);

	/**
	 * 平台订单号查询订单（给pac调用）
	 * @param platformOrderCode
	 * @param shopId
	 * @return
	 */
	int  checkHandCreateOrder(String platformOrderCode, Long shopId) ;

}
