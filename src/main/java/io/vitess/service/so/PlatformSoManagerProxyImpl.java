package io.vitess.service.so;

import io.vitess.constants.PlatformSouceContant;
import io.vitess.dao.base.CompanyShopDao;
import io.vitess.dao.mq.MqSoLogDao;
import io.vitess.enums.PlatformType;
import io.vitess.enums.SalesOrderType;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service("platformSoManagerProxy")
public class PlatformSoManagerProxyImpl implements PlatformSoManagerProxy, InitializingBean {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = 2213885134142701455L;

    @Autowired
    private MqSoLogDao mqSoLogDao;

    @Autowired
    private CompanyShopDao companyShopDao;

    @Autowired
    private PlatformSoManager platformSoManager;
    
    @Value("${so.thread.count}")
    private int ThreadCount;

    /**
     * 分组大小
     */
    private int maxDeal = 100;

    private ExecutorService exec;

    private ThreadLocal<Long> startTime = new ThreadLocal<>();
    Map<String, String> map;
    /**
     * 淘宝创单
     */
//    @Scheduled(fixedDelay = 1000)
    @Override
    public void createTaobaoSo() {
        startTime.set(System.currentTimeMillis());
        List<CompanyShop> shopIdsList = companyShopDao.findShopListGeneralOrder();
        int platformType = PlatformType.TAOBAO_PLATFORM.getValue();
        for (int i = 0; i < shopIdsList.size(); i++) {
            Long shopId = shopIdsList.get(i).getId();
            List<Long> mqSoLogIds = mqSoLogDao.findMqSoForCreateSo(platformType, shopId, 2, getCreateTime());

            int times = (mqSoLogIds.size() + maxDeal - 1) / maxDeal;
            CountDownLatch countDownLatch = new CountDownLatch(times);

            try {
                for (int j = 0; j < times; j++) {
                    if (j == times - 1) {
                        exec.execute(new SoCreator(mqSoLogIds.subList(j * maxDeal, mqSoLogIds.size()), shopId, countDownLatch));
                    } else {
                        exec.execute(new SoCreator(mqSoLogIds.subList(j * maxDeal, (j + 1) * maxDeal), shopId, countDownLatch));
                    }
                }
                countDownLatch.await();
            } catch (InterruptedException e) {
                logger.error(Thread.currentThread().getName() + ":Interrupted");
            }finally {
                Long interval = System.currentTimeMillis() - startTime.get();
                logger.warn("one order create task interval {} s", interval/1000);
            }
        }
    }



    private Date getCreateTime(){

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-7);
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());

        return date;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map = PlatformSouceContant.loadPlatformSouceData();
        exec = new ThreadPoolExecutor(ThreadCount, ThreadCount,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    class SoCreator implements  Runnable{

        private List<Long> mqSoLogIds;
        private Long shopId;
        CountDownLatch countDownLatch;

        public SoCreator(List<Long> mqSoLogIds, Long shopId, CountDownLatch countDownLatch) {
            this.mqSoLogIds = mqSoLogIds;
            this.shopId = shopId;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                List<MqSoLog> mqSoLogs = mqSoLogDao.queryMqByIds(mqSoLogIds);
                for (MqSoLog mqSoLog : mqSoLogs) {
                    platformSoManager.createTbSoFromMqSoLog(mqSoLog, SalesOrderType.PLATFORM_ONLINE_TB, map,shopId);
                }
            }finally {
                countDownLatch.countDown();
            }
        }
    }
}


