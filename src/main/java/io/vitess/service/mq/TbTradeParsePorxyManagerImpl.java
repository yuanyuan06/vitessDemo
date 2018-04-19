package io.vitess.service.mq;

import io.vitess.dao.so.TbTradeDao;
import io.vitess.model.mq.TbTrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Service("tbTradeParsePorxyManager")
public class TbTradeParsePorxyManagerImpl implements TbTradeParsePorxyManager {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TbTradeDao tbTradeDao;
	@Autowired
	private TbTradeParseManager tbTradeParseManager;

    /**
     * 线程数
     */
    private int ThreadCount = 20;
    /**
     * 分组大小
     */
    private int maxDeal = 50;
    /**
     * 线程池
     */
//    private ExecutorService exec = Executors.newFixedThreadPool(ThreadCount);
    private ExecutorService exec = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

	@Override
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
