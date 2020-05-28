package single.lazy;

/**
 * @author Tiger
 * @date 2020-03-08
 * @see single.lazy
 **/
public class InnerLazySingleton {
    private InnerLazySingleton() {
    }

    private volatile static InnerLazySingleton single = null;

    private static class LazyHolder {
        private static final InnerLazySingleton INSTANCE = new InnerLazySingleton();
    }

    public static InnerLazySingleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
