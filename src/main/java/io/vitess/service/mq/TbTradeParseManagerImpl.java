package io.vitess.service.mq;

import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.response.TradeFullinfoGetResponse;
import io.vitess.constants.PlatformConstants;
import io.vitess.constants.TopRdsConstants;
import io.vitess.dao.so.PlatformSoLogDao;
import io.vitess.dao.so.TbTradeDao;
import io.vitess.exception.SoGetTradeException;
import io.vitess.model.mq.MqSoLog;
import io.vitess.model.base.PlatformSoLog;
import io.vitess.model.mq.TbTrade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tbTradeParseManager")
public class TbTradeParseManagerImpl implements TbTradeParseManager {
	private static Log log = LogFactory.getLog(TbTradeParseManagerImpl.class);
	
    @Autowired
    private TbCreateSoManager createTaoBaoSoManager;
    @Autowired
    private PlatformSoLogDao platformSoLogDao;
	@Autowired
	private TbTradeDao tbTradeDao;
	
	@Override
	public void tbTradeParse(TbTrade bean) {
		boolean result = saveHubTrade(bean.getOmsShopId(),bean.getContent());
		Integer syncStatus = TopRdsConstants.PROCESS_STATUS_NO;
		if(result){
			syncStatus = TopRdsConstants.PROCESS_STATUS_COMPLET;
		}
		tbTradeDao.updateTbTrade(bean.getId(), syncStatus);

	}
	
	private boolean saveHubTrade(Long shopId, String message){
		boolean result = false;
        try {
            TradeFullinfoGetResponse taobaoTrade = TaobaoUtils.parseResponse(message, TradeFullinfoGetResponse.class);
            com.taobao.api.domain.Trade trade = taobaoTrade.getTrade();
            saveTrade(shopId, trade, message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("trade json: " + message + ":" + e.getMessage());
        }
        return result;
    }
	
    private void saveTrade(Long shopId, com.taobao.api.domain.Trade trade, String message) throws SoGetTradeException {
        if (trade.getPayment() == null || "".equals(trade.getPayment())) {
            return;
        }
        // gnc健安喜宝尊专卖店淘宝的店铺 的淘宝类型订单暂时不处理 先处理它的分销平台数据
        if (PlatformConstants.SHOP_TOP_GNC.equals(trade.getSellerNick().trim()) && PlatformConstants.SO_TYPE_TB_FIXED.equals(trade.getType())) {
            return;
        }
        if (trade.getOrders() == null || trade.getOrders().size() == 0) {
            boolean flag = trade.getOrders() == null;
            log.info("getTradesFromTB trade.getOrders is null , trade.getOrders() == null :" + flag + "trade.getOrders().size() : " + (trade.getOrders() == null ? 0 : trade.getOrders().size()));
            return;
        }
        try {

            MqSoLog soLog = (MqSoLog) createTaoBaoSoManager.convertTradeToMqSo(trade, shopId);
            // 生成trade源数据日志
            PlatformSoLog psl = new PlatformSoLog();
            psl.setShopId(shopId);
            psl.setSourceMsg(message);
            psl.setCode(trade.getTid().toString());
            if (soLog != null) {
                psl.setMqSoLogId(soLog.getId());
            }
            platformSoLogDao.insert(psl);
        } catch (Exception ie) {
            ie.printStackTrace();
            log.error(ie.getMessage());
            log.error("获取数据错误：SESSION ERROR");
            throw new SoGetTradeException(PlatformConstants.TAOBAO_PLATFORM);
        }
    }
}
