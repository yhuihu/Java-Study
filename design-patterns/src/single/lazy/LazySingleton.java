package single.lazy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tiger
 * @date 2020-03-08
 * @see single.lazy
 **/
public class LazySingleton {
    private LazySingleton() {
    }

    private volatile static LazySingleton single = null;

    public static LazySingleton getInstance() {
        if (single == null) {
            single = new LazySingleton();
        }
        return single;
    }

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<100;i++) {
            executorService.execute(LazySingleton::getInstance);
        }
        executorService.shutdown();
    }
}
