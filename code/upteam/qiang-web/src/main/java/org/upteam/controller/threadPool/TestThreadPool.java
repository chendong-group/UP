package org.upteam.controller.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestThreadPool.class);
    public static void main(String[] args) throws Exception{
        BlockingQueue blockingQueue = new ArrayBlockingQueue(4);
        CustomThreadPool pool = new CustomThreadPool(3, 5, blockingQueue, 1, TimeUnit.SECONDS, null);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Worker(i));
        }


        LOGGER.info("=======休眠前线程池活跃线程数={}======",pool.getWorkerCount());

        TimeUnit.SECONDS.sleep(5);
        LOGGER.info("=======休眠后线程池活跃线程数={}======",pool.getWorkerCount());

        for (int i = 0; i < 3; i++) {
            pool.execute(new Worker(i + 100));
        }

        pool.shutdown();
        //pool.shutDownNow();
        //pool.execute(new Worker(100));
        LOGGER.info("++++++++++++++");
    }

    private static class Worker implements Runnable{

        private int state ;

        public Worker(int state) {
            this.state = state;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                LOGGER.info("state={}",state);
            } catch (InterruptedException e) {

            }
        }
    }
}
