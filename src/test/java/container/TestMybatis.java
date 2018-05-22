package container;

import com.alibaba.fastjson.JSON;
import io.vitess.dao.base.CompanyShopDao;
import io.vitess.dao.so.TbTradeDao;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import io.vitess.model.mq.TbTrade;
import io.vitess.service.common.CompanyShopManager;
import io.vitess.service.mq.TbTradeParsePorxyManager;
import io.vitess.service.so.PlatformSoManagerProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author YSH4807
 * @date 2018/4/23 15:59
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:application.xml"})
public class TestMybatis {

    @Autowired
    private TbTradeDao tbTradeDao;
    @Autowired
    private TbTradeParsePorxyManager tbTradeParsePorxyManager;
    @Autowired
    private CompanyShopManager shopManager;
    @Autowired
    private CompanyShopDao companyShopDao;
    @Autowired
    private PlatformSoManagerProxy platformSoManagerProxy;

    @Test
    public void testInsertTrade(){
        TbTrade trade = new TbTrade();
        trade.setId(1L);
        trade.setContent("");
        trade.setFullInfoGetTime(new Date());
        tbTradeDao.insert(trade);
    }

    @Test
    public void tt(){
        shopManager.findShopInfoByShopId(1L);
    }



    @Test
    public void testShop(){
        CompanyShop byId = companyShopDao.findById(6111L);
        System.out.println(byId);
    }

    @Test
    public void getTrade(){
        List<TbTrade> tbTradeNotSync = tbTradeDao.findTbTradeNotSync();
        System.out.println(JSON.toJSONString(tbTradeNotSync));
    }

    @Test
    public void te(){
        MqSoLog log = new MqSoLog();
//        log.getIsLgtype();
//        log.getIsLgType();
    }

    @Test
    public void testMqParse(){
        tbTradeParsePorxyManager.tbTradeParse();
    }

    @Test
    public void testCreate(){
        while (true){
            platformSoManagerProxy.createTaobaoSo();
        }
    }
}
