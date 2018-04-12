package SpringContainer.mybatis.demo.mq;

import io.vitess.dao.*;
import io.vitess.model.MqSoPackingInfoLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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


    @Test
    public void tetd(){
        MqSoPackingInfoLog mqSoPackingInfoLog = new MqSoPackingInfoLog();
            mqSoPackingInfoLog.setId(1L);
            mqSoPackingInfoLog.setMemo("fd");
            mqSoPackingInfoLog.setSoLineLogId(1L);
            mqSoPackingInfoLog.setSoLogId(1L);
            mqSoPackingInfoLog.setShopId(1L);
        mqSoPackingInfoLogDao.insert(mqSoPackingInfoLog);
    }

}

