package io.vitess.service.mq;

import io.vitess.dao.so.TbTradeDao;
import io.vitess.model.mq.TbTrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Service("tbTradeParsePorxyManager")
public class TbTradeParsePorxyManagerImpl implements TbTradeParsePorxyManager, InitializingBean {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TbTradeDao tbTradeDao;
	@Autowired
	private TbTradeParseManager tbTradeParseManager;

    /**
     * 线程数
     */
    @Value("${so.thread.count}")
    private int ThreadCount;
    /**
     * 分组大小
     */
    private int maxDeal = 100;

    private ExecutorService exec;

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Scheduled(fixedRate = 1000*50)
	@Override
	public void tbTradeParse() {

        log.warn("thread {}, tbTradeParse start time {}", Thread.currentThread().getName(), new Date());
        try {
            startTime.set(System.currentTimeMillis());
            List<Long> list = tbTradeDao.findTbTradeNotSyncTradeId();
            if (CollectionUtils.isEmpty(list)) {
                return;
            }

            int times = (list.size() - 1) / maxDeal + 1;
            CountDownLatch countDownLatch = new CountDownLatch(times);
            for (int i = 0; i < times; i++) {
                if (i == times - 1) {
                    exec.execute(new ProcessRunnable(list.subList(i * maxDeal, list.size()), countDownLatch));
                } else {
                    exec.execute(new ProcessRunnable(list.subList(i * maxDeal, (i + 1) * maxDeal), countDownLatch));
                }
            }
            countDownLatch.await();
        } catch (Exception e) {
            log.error(Thread.currentThread().getName() + ":Interrupted");
        }finally {
            Long interval = System.currentTimeMillis() - startTime.get();
            startTime.remove();
            log.warn("thread {}, one order parse task interval: {} s", Thread.currentThread().getName(), interval/1000);
            log.warn("thread {}, tbTradeParse end time {}", Thread.currentThread().getName(), new Date());
        }

	}

    @Override
    public void afterPropertiesSet() throws Exception {
        exec = new ThreadPoolExecutor(ThreadCount, ThreadCount,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    private class ProcessRunnable implements Runnable {
        private List<Long> list;
        private CountDownLatch countDownLatch;
        
        public ProcessRunnable(List<Long> list, CountDownLatch countDownLatch) {
            this.list = list;
            this.countDownLatch = countDownLatch;
        }
        
        @Override
        public void run() {
        	Long tId= Thread.currentThread().getId(),
        			ct = System.currentTimeMillis(),
        			cct = ct;
            try {
                List<TbTrade> tradeList = tbTradeDao.findTbTradeNotSyncByTradeId(list);
            	for(TbTrade bean : tradeList){
            		tbTradeParseManager.tbTradeParse(bean);
            		Long tmpt = System.currentTimeMillis();
                    log.warn("[Thread-{}]Parse One Trade within {}ms", tId, tmpt-cct);
                    cct = tmpt;
            	}
            } finally {
                countDownLatch.countDown();
                log.warn("[Thread-{}]Parse Trade End within {}ms", tId, System.currentTimeMillis()-ct);
            }
        }
    }
	
}
