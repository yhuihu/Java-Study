package single.lazy;

/**
 * @author Tiger
 * @date 2020-03-08
 * @see single.lazy
 **/
public class SynchronizedLazySingleton {
    private SynchronizedLazySingleton() {
    }

    // 这里注意要用volatile，避免jvm重排序导致18行没执行就执行19行
    private volatile static SynchronizedLazySingleton single = null;

    public static SynchronizedLazySingleton getInstance() {
        if (single == null) {
            synchronized (SynchronizedLazySingleton.class) {
                if (single == null) {
                    single = new SynchronizedLazySingleton();
                }
            }
        }
        return single;
    }
}
