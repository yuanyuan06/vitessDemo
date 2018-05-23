package io.vitess.service.so;

import io.vitess.enums.SalesOrderType;
import io.vitess.model.mq.MqSoLog;

import java.util.Map;

public interface PlatformSoManager {
	//预售订单定金已付
	static final String STEP_TRADE_STATUS_FRONT_PAID = "FRONT_PAID_FINAL_NOPAID";
	/**
	 * 
	 * 创建淘宝订单
	 * 
	 * @param mqSoLog
	 * @param orderType
	 * @param sourceMap
	 */
	void createTbSoFromMqSoLog(MqSoLog mqSoLog, SalesOrderType orderType, Map<String, String> sourceMap, Long shopId);
	
}
