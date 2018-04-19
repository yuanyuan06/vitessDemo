package io.vitess.service.mq;

import io.vitess.model.mq.TbTrade;

public interface TbTradeParseManager {
	
	void tbTradeParse(TbTrade bean);
	
}
