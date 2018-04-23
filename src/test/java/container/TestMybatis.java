package container;

import io.vitess.dao.so.TbTradeDao;
import io.vitess.model.mq.TbTrade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author YSH4807
 * @date 2018/4/23 15:59
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:application.xml"})
public class TestMybatis {

    @Autowired
    private TbTradeDao tbTrade;

    @Test
    public void testInsertTrade(){
        TbTrade trade = new TbTrade();
        trade.setId(1L);
        trade.setContent("");
        tbTrade.insert(trade);
    }
}
