package io.vitess.service;

import io.vitess.model.mq.MqSoLog;

/**
 * @author YSH4807
 * @date 2018/4/11 11:01
 */
public interface TbCreateSoService {


    MqSoLog convertTradeToMqSo(com.taobao.api.domain.Trade tbTrade, Long shopId) throws Exception;


}
