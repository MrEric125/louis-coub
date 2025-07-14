package com.louis.longagocode.concurrent.executor;

import com.concurrent.executor.DefaultMKThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author louis
 * <p>
 * Date: 2019/12/20
 * Description:
 */
@Slf4j
public class ThreadUtils {


    private static final int POOL_SIZE = 100; //线程池线程最大数
    private static ExecutorService pool;
    static {
        DefaultMKThreadFactory factor = new DefaultMKThreadFactory("intOplogQueue");
        //无容量的阻塞队列 SynchronousQueue
        pool = new ThreadPoolExecutor(0, POOL_SIZE, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), factor);
    }


    public void execute(Runnable worker, int concurrentSize) {
        log.debug("execute start");
        if(concurrentSize<=0){
            return ;
        }
        for (int i = 0; i < concurrentSize; i++) {
            pool.execute(worker);
        }

        log.debug("execute end");
    }
    public void shutdown() {
        pool.shutdown();

    }
}
