package org.upteam.controller.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.upteam.controller.threadPool.communication.Notify;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class CustomThreadPool {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomThreadPool.class);
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 核心线程数
     */
    private volatile int miniSize;
    /**
     * 最大线程数
     */
    private volatile int maxSize;
    /**
     * 存放线程的阻塞队列（当核心线程数满了时，就会开始放进队列，队列满了再去接着开线程，直到活跃线程数等于最大线程数）
     */
    private BlockingQueue<Runnable> workQueue;
    /**
     *
     */
    private long keepAliveTime;
    private TimeUnit unit;

    /**
     *
     */
    private volatile Set<Worker> workers;

    private AtomicBoolean isShutDown = new AtomicBoolean(false);

    private AtomicInteger totalTask = new AtomicInteger();

    private Object shutDownNotify = new Object();

    private Notify notify;

    /**
     *
     * @param miniSize
     * @param maxSize
     * @param workQueue
     * @param keepAliveTime
     * @param unit
     * @param notify
     */
    public CustomThreadPool(int miniSize, int maxSize, BlockingQueue<Runnable> workQueue, long keepAliveTime, TimeUnit unit, Notify notify) {
        this.miniSize = miniSize;
        this.maxSize = maxSize;
        this.workQueue = workQueue;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.notify = notify;

        workers = new ConcurrentHashSet<>();
    }

    public void execute(Runnable runnable){
        if(runnable == null){
            throw new NullPointerException("runnable nullPointerException");
        }
        if (isShutDown.get()) {
            LOGGER.info("线程池已经关闭，不能再提交任务！");
            return;
        }
        //提交的线程 计数
        totalTask.incrementAndGet();

        //小于最小线程数时新建线程
        if (workers.size() < miniSize) {
            addWorker(runnable);
            return;
        }

        boolean offer = workQueue.offer(runnable);
        //写入队列失败
        if (!offer) {

            //创建新的线程执行
            if (workers.size() < maxSize) {
                addWorker(runnable);
                return;
            } else {
                LOGGER.error("超过最大线程数");
                try {
                    //会阻塞
                    workQueue.put(runnable);
                } catch (InterruptedException e) {

                }
            }

        }
    }


    /**
     * 添加任务，需要加锁
     *
     * @param runnable 任务
     */
    private void addWorker(Runnable runnable) {
        Worker worker = new Worker(runnable, true);
        worker.startTask();
        workers.add(worker);
    }


    private final class Worker extends Thread {
        private Runnable task;

        private Thread thread;
        /**
         * true --> 创建新的线程执行
         * false --> 从队列里获取线程执行
         */
        private boolean isNewTask;

        public Worker(Runnable task, boolean isNewTask) {
            this.task = task;
            this.isNewTask = isNewTask;
            thread = this;
        }

        public void startTask() {
            thread.start();
        }

        public void close() {
            thread.interrupt();
        }

        @Override
        public void run() {

            Runnable task = null;

            if (isNewTask) {
                task = this.task;
            }

            try {
                while ((task != null || (task = getTask()) != null)) {
                    try {
                        //执行任务
                        task.run();
                    } finally {
                        //任务执行完毕
                        task = null;
                        int number = totalTask.decrementAndGet();
                        //LOGGER.info("number={}",number);
                        if (number == 0) {
                            synchronized (shutDownNotify) {
                                shutDownNotify.notify();
                            }
                        }
                    }
                }

            } finally {
                //释放线程
                boolean remove = workers.remove(this);
                //LOGGER.info("remove={},size={}", remove, workers.size());
                tryClose(true);
            }
        }
    }

    /**
     * 从队列中获取任务
     *
     * @return
     */
    private Runnable getTask() {
        //关闭标识及任务是否全部完成
        if (isShutDown.get() && totalTask.get() == 0) {
            return null;
        }
        //while (true) {
        //
        //    if (workers.size() > miniSize) {
        //        boolean value = number.compareAndSet(number.get(), number.get() - 1);
        //        if (value) {
        //            return null;
        //        } else {
        //            continue;
        //        }
        //    }

        lock.lock();

        try {
            Runnable task = null;
            if (workers.size() > miniSize) {
                //大于核心线程数时需要用保活时间获取任务
                task = workQueue.poll(keepAliveTime, unit);
            } else {
                task = workQueue.take();
            }

            if (task != null) {
                return task;
            }
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }

        return null;
        //}
    }
    /**
     * 关闭线程池
     *
     * @param isTry true 尝试关闭      --> 会等待所有任务执行完毕
     *              false 立即关闭线程池--> 任务有丢失的可能
     */
    private void tryClose(boolean isTry) {
        if (!isTry) {
            closeAllTask();
        } else {
            if (isShutDown.get() && totalTask.get() == 0) {
                closeAllTask();
            }
        }

    }

    /**
     * 关闭所有任务
     */
    private void closeAllTask() {
        for (Worker worker : workers) {
            //LOGGER.info("开始关闭");
            worker.close();
        }
    }
    private final class ConcurrentHashSet<T> extends AbstractSet<T>{

        private ConcurrentHashMap<T, Object> map = new ConcurrentHashMap<>();
        private final Object PRESENT = new Object();

        private AtomicInteger count = new AtomicInteger();

        @Override
        public boolean add(T t) {
            count.incrementAndGet();
            return map.put(t, PRESENT) == null;
        }
        @Override
        public Iterator<T> iterator() {
            return map.keySet().iterator();
        }

        @Override
        public boolean remove(Object o) {
            count.decrementAndGet();
            return map.remove(o) == PRESENT;
        }

        @Override
        public int size() {
            return count.get();
        }
    }

    /**
     * 获取工作线程数量
     *
     * @return
     */
    public int getWorkerCount() {
        return workers.size();
    }

    /**
     * 任务执行完毕后关闭线程池
     */
    public void shutdown() {
        isShutDown.set(true);
        tryClose(true);
        //中断所有线程
        //synchronized (shutDownNotify){
        //    while (totalTask.get() > 0){
        //        try {
        //            shutDownNotify.wait();
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //}
    }
}
