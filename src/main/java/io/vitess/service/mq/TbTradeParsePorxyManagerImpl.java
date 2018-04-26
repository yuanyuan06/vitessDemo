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
import org.springframework.transaction.annotation.Transactional;

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
    private int maxDeal = 50;

    private ExecutorService exec;

    @Scheduled(fixedRate = 1000*5)
	@Override
    @Transactional(rollbackFor = Exception.class)
	public void tbTradeParse() {

		List<TbTrade> list = tbTradeDao.findTbTradeNotSync();
		if (list == null || list.size() <= 0) {
            return;
        }
		
		int times = (list.size() + maxDeal - 1) / maxDeal;
        CountDownLatch countDownLatch = new CountDownLatch(times);
        
        try {
            for (int i = 0; i < times; i++) {
                if (i == times - 1) {
                    exec.execute(new ProcessRunnable(list.subList(i * maxDeal, list.size()), countDownLatch));
                } else {
                    exec.execute(new ProcessRunnable(list.subList(i * maxDeal, (i + 1) * maxDeal), countDownLatch));
                }
            }
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error(Thread.currentThread().getName() + ":Interrupted");
        }

	}

    @Override
    public void afterPropertiesSet() throws Exception {
        exec = new ThreadPoolExecutor(ThreadCount, ThreadCount,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    private class ProcessRunnable implements Runnable {
        private List<TbTrade> list;
        private CountDownLatch countDownLatch;
        
        public ProcessRunnable(List<TbTrade> list, CountDownLatch countDownLatch) {
            this.list = list;
            this.countDownLatch = countDownLatch;
        }
        
        @Override
        public void run() {
        	Long tId= Thread.currentThread().getId(),
        			ct = System.currentTimeMillis(),
        			cct = ct;
            try {
            	for(TbTrade bean : list){
            		tbTradeParseManager.tbTradeParse(bean);
            		Long tmpt = System.currentTimeMillis();
                    log.info("[Thread-{}]Parse One Trade within {}ms", tId, tmpt-cct);
                    cct = tmpt;
            	}
            } finally {
                countDownLatch.countDown();
                log.info("[Thread-{}]Parse Trade End within {}ms", tId, System.currentTimeMillis()-ct);
            }
        }
    }
	
}
