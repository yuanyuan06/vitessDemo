package SpringContainer.mybatis.demo.mq;

import com.alibaba.fastjson.JSON;
import com.sun.javafx.runtime.SystemProperties;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.response.TradeFullinfoGetResponse;
import io.vitess.service.TbCreateSoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Properties;

/**
 * @author YSH4807
 * @date 2018/4/16 10:04
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:application.xml"})
public class ParseMq {

    @Autowired
    private TbCreateSoService tbCreateSoService;

    private static String trade;
    private static String shopId;

    static {
        ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        Resource resource = resourceLoader.getResource("classpath:param.properties");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            trade = properties.getProperty("trade");
            shopId = properties.getProperty("shop_id");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void er() throws Exception {

        TradeFullinfoGetResponse taobaoTrade = TaobaoUtils.parseResponse(trade, TradeFullinfoGetResponse.class);
        com.taobao.api.domain.Trade tradeP = taobaoTrade.getTrade();
        for (String o : shopId.split(",")) {
            tbCreateSoService.convertTradeToMqSo(tradeP, Long.parseLong(o));
        }
    }
}
