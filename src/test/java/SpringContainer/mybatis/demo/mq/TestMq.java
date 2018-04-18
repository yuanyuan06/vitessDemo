package SpringContainer.mybatis.demo.mq;

import com.alibaba.fastjson.JSON;
import io.vitess.dao.*;
import io.vitess.model.mq.CompanyShop;
import io.vitess.model.mq.MqSoPackingInfoLog;
import io.vitess.model.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YSH4807
 * @date 2018/4/11 17:37
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:application.xml"})
public class TestMq {

    @Autowired
    private MqSoPackingInfoLogDao mqSoPackingInfoLogDao;
    @Autowired
    private CompanyShopDao companyShopDao;
    @Autowired
    private MqDeliveryInfoLogDao mqDeliveryInfoLogDao;
    @Autowired
    private MqPlatformMemberLogDao mqPlatformMemberLogDao;
    @Autowired
    private MqSoLineLogDao mqSoLineLogDao;
    @Autowired
    private MqSoLogDao mqSoLogDao;
    @Autowired
    private MqSoPromotionLogDao mqSoPromotionLogDao;
    @Autowired
    private MqSoServiceLineLogDao mqSoServiceLineLogDao;
    @Autowired
    private PlatformSoLogDao platformSoLogDao;
    @Autowired
    private TestCaseDao testCaseDao;


    @Test
    public void tetd(){
        MqSoPackingInfoLog mqSoPackingInfoLog = new MqSoPackingInfoLog();
            mqSoPackingInfoLog.setMemo("fd");
            mqSoPackingInfoLog.setSoLineLogId(1L);
            mqSoPackingInfoLog.setSoLogId(1L);
            mqSoPackingInfoLog.setShopId(6931L);
        mqSoPackingInfoLogDao.insert(mqSoPackingInfoLog);
        System.out.println(JSON.toJSONString(mqSoPackingInfoLog));


//        MqSoPackingInfoLog byId = mqSoPackingInfoLogDao.findById(1L);

//        Map<String, Object> map = new HashMap<>();
//        map.put("shopId", 6931);
//        List<MqSoPackingInfoLog> listByQueryMap = mqSoPackingInfoLogDao.findListByQueryMap(map);
//        System.out.println(JSON.toJSONString(listByQueryMap));
    }

    @Test
    public void getShop(){
        CompanyShop byId = companyShopDao.findById(6931L);

        Map<String, Object> map = new HashMap<>();
        map.put("shopId", 6931L);
        List<CompanyShop> listByQueryMap = companyShopDao.findListByQueryMap(map);
        System.out.println(JSON.toJSONString(byId));
    }

    @Test
    public void re(){
        TestCase testCase = new TestCase();
            testCase.setMessage("121");
            testCase.setPage(12L);
            testCase.setTimeCreatedNs(32L);
        testCaseDao.insert(testCase);
        System.out.println(JSON.toJSONString(testCase));
    }

}

