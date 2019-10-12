package study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Tiger
 * @date 2019-09-25
 * @see study.thread
 **/
public class MyFutureTask<T> implements Runnable {
    private static final String ERROR = "EXP";
    private static final String FINISH = "END";
    private static final String NEW = "NEW";
    private Callable<T> callable;
    private volatile String state = NEW;
    private T result;
    private LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    public MyFutureTask(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        //业务逻辑执行
        try {
            result = callable.call();
        } catch (Exception e) {
            state = ERROR;
            e.printStackTrace();
        } finally {
            if (!state.equals(ERROR)) {
                state = FINISH;
            }
        }

        Thread waiter = waiters.poll();
        while (waiter != null) {
            LockSupport.unpark(waiter);
            waiter = waiters.poll();
        }
    }

    public T get() throws Exception {
        //没有执行完毕，需要等待
        if (ERROR.equals(state)) {
            throw new Exception("错误");
        }
        while (!FINISH.equals(state)) {
            waiters.add(Thread.currentThread());
            LockSupport.park(); //进入阻塞状态
        }
        return result;
    }
}
