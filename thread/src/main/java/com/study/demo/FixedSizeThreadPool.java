package com.study.demo;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Tiger
 * @date 2019-09-25
 * @see com.study.demo
 **/
public class FixedSizeThreadPool {
    private BlockingQueue<Runnable> blockingQueue;
    private LinkedBlockingQueue<Thread> workers;

    /**
     * @param pollSize 线程池数量
     * @param taskSize 任务数量
     * @return
     **/
    public FixedSizeThreadPool(int pollSize, int taskSize) {
        if (pollSize <= 0 || taskSize <= 0) {
            throw new IllegalArgumentException("非法参数");
        }
        this.blockingQueue = new LinkedBlockingQueue<>(taskSize);
        this.workers = new LinkedBlockingQueue<>(pollSize);

        for (int i = 0; i < pollSize; i++) {
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    public Boolean submit(Runnable task) {
        if (isWorking) {
            return this.blockingQueue.offer(task);
        } else {
            return false;
        }
    }

    private volatile boolean isWorking = true;

    public void shutdown() {
        this.isWorking = false;
        for (Thread thread : workers) {
            if(thread.getState().equals(Thread.State.BLOCKED)){
                thread.interrupt();
            }
        }
    }

    public static class Worker extends Thread {
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while (this.pool.isWorking || this.pool.blockingQueue.size() > 0) {
                Runnable task = null;
                try {
                    //阻塞方法获取任务
                    if (this.pool.isWorking) {
                        task = this.pool.blockingQueue.take();
                    } else {
                        task = this.pool.blockingQueue.poll();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    task.run();
                    System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
                }
            }
        }
    }

    public static void main(String[] args) {
        FixedSizeThreadPool fixedSizeThreadPool = new FixedSizeThreadPool(3, 6);
        for (int i = 0; i < 6; i++) {
            fixedSizeThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("放入一个线程");
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedSizeThreadPool.shutdown();
    }
}
