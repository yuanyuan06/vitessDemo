package container;

import io.vitess.dao.so.TbTradeDao;
import io.vitess.model.mq.TbTrade;
import io.vitess.service.common.CompanyShopManager;
import io.vitess.service.mq.TbTradeParsePorxyManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author YSH4807
 * @date 2018/4/23 15:59
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:application.xml"})
public class TestMybatis {

    @Autowired
    private TbTradeDao tbTrade;
    @Autowired
    private TbTradeParsePorxyManager tbTradeParsePorxyManager;
    @Autowired
    private CompanyShopManager shopManager;

    @Test
    public void testInsertTrade(){
        TbTrade trade = new TbTrade();
        trade.setId(1L);
        trade.setContent("");
        trade.setFullInfoGetTime(new Date());
        tbTrade.insert(trade);
    }

    @Test
    public void testMqParse(){
        tbTradeParsePorxyManager.tbTradeParse();
    }

    @Test
    public void tt(){
        shopManager.findShopInfoByShopId(1L);
    }
}
