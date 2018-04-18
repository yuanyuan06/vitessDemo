package io.vitess.service;

import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.response.TradeFullinfoGetResponse;
import io.vitess.dao.PlatformSoLogDao;
import io.vitess.constants.PlatformConstants;
import io.vitess.model.mq.MqSoLog;
import io.vitess.model.mq.PlatformSoLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YSH4807
 * @date 2018/4/11 10:44
 */
@Service("mqService")
public class MqServiceImpl implements MqService {

    @Autowired
    private TbCreateSoService tbCreateSoService;

    @Autowired
    private PlatformSoLogDao platformSoLogDao;

    @Override
    public void tbTradeParse() {

    }


    private boolean insertHubTrade(Long shopId, String message){
        boolean result = false;
        try {
            TradeFullinfoGetResponse taobaoTrade = TaobaoUtils.parseResponse(message, TradeFullinfoGetResponse.class);
            com.taobao.api.domain.Trade trade = taobaoTrade.getTrade();

            insertTrade(shopId, trade, message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void insertTrade(Long shopId, com.taobao.api.domain.Trade trade, String message) throws Exception {
        if (trade.getPayment() == null || "".equals(trade.getPayment())) {
            return;
        }
        // gnc健安喜宝尊专卖店淘宝的店铺 的淘宝类型订单暂时不处理 先处理它的分销平台数据
        if (PlatformConstants.SHOP_TOP_GNC.equals(trade.getSellerNick().trim()) && PlatformConstants.SO_TYPE_TB_FIXED.equals(trade.getType())) {
            return;
        }
        if (trade.getOrders() == null || trade.getOrders().size() == 0) {
            boolean flag = trade.getOrders() == null;
            return;
        }
        try {

            MqSoLog soLog = (MqSoLog) tbCreateSoService.convertTradeToMqSo(trade, shopId);
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
            throw new Exception(PlatformConstants.TAOBAO_PLATFORM);
        }
    }
}
