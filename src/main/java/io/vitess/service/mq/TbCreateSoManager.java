package io.vitess.service.mq;

import com.taobao.api.domain.DealerOrder;
import com.taobao.api.domain.PurchaseOrder;
import io.vitess.exception.SoGetTradeException;
import io.vitess.model.mq.MqSoLog;

public interface TbCreateSoManager {
    MqSoLog convertTradeToMqSo(com.taobao.api.domain.Trade tbTrade, Long shopId) throws SoGetTradeException;
}
