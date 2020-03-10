package single.lazy;

/**
 * @author Tiger
 * @date 2020-03-08
 * @see single.lazy
 **/
public class DCLSingleton {
    private DCLSingleton() {
    }

    private volatile static DCLSingleton single = null;

    public static DCLSingleton getInstance() {
        if (single == null) {
            synchronized (DCLSingleton.class) {
                if (single == null) {
                    single = new DCLSingleton();
                }
            }
        }
        return single;
    }
}
