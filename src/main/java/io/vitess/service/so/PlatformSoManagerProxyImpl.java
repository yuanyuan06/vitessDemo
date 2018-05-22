package io.vitess.service.so;

import com.alibaba.fastjson.JSON;
import io.vitess.constants.PlatformSouceContant;
import io.vitess.dao.base.CompanyShopDao;
import io.vitess.dao.mq.MqSoLogDao;
import io.vitess.enums.MqSoLogStatus;
import io.vitess.enums.PlatformType;
import io.vitess.enums.SalesOrderType;
import io.vitess.model.base.CompanyShop;
import io.vitess.model.mq.MqSoLog;
import io.vitess.service.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private int maxDeal = 50;

//    private ExecutorService exec;
    private ExecutorService exec;

    private ThreadLocal<Long> startTime = new ThreadLocal<>();
    Map<String, String> map;
    /**
     * 淘宝创单
     */
    @Scheduled(fixedDelay = 1000)
    @Override
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 记录错误次数,不允许超过3次
     * @param mqSoLogId
     */
    private void mqCreateSoErrorCount(Long mqSoLogId, Long shopId, String msg) {
        MqSoLog mqSoLog = mqSoLogDao.findMqSoLogByIdShopId(mqSoLogId, shopId);
        Integer initCount = new Integer(1);
        Integer errorCount = mqSoLog.getErrorCount() == null ? initCount : initCount + mqSoLog.getErrorCount();
        int orderStatus = mqSoLog.getStatus();
        if(errorCount >= 3){
        	orderStatus = MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue();
        	msg = mqSoLog.getErrorMsg() + "(创单错误次数超过3次)";
        }
        
        mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, orderStatus, msg, errorCount);
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
                for (int j = 0; j < mqSoLogIds.size(); j++) {
                    Long mqSoLogId = mqSoLogIds.get(j);
                    try {
                        platformSoManager.createTbSoFromMqSoLog(mqSoLogId, SalesOrderType.PLATFORM_ONLINE_TB, map,shopId);
                    } catch(BusinessException be) {
                        mqSoLogDao.updateStatusAndErrorMsgById(mqSoLogId, shopId, null, MqSoLogStatus.MQ_SO_STATUS_ERROR.getValue(), JSON.toJSONString(be.getArgs()), null);
                        logger.error("---------createTaobaoSo Error, mqSoLogId:" + mqSoLogId + "------------", be);
                    }catch (Exception e) {
                        mqCreateSoErrorCount(mqSoLogId, shopId, e.getMessage());
                        logger.error("---------createTaobaoSo Error, mqSoLogId:" + mqSoLogId + "------------",e);
                    }
                }
            }finally {
                countDownLatch.countDown();
            }

        }
    }
}


