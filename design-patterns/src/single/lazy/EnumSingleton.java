package single.lazy;

/**
 * @author Tiger
 * @date 2020-03-08
 * @see single.lazy
 **/
public class EnumSingleton {
    private EnumSingleton() {
    }

    // 定义一个静态枚举类
    static enum SingletonEnum {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private EnumSingleton enumSingleton;

        //私有化枚举的构造函数
        private SingletonEnum() {
            enumSingleton = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return enumSingleton;
        }
    }

    // 对外暴露一个获取User对象的静态方法
    public static EnumSingleton getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }
}
