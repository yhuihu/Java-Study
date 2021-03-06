package single.lazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Tiger
 * @date 2020-03-08
 * @see single.lazy
 **/
public class EnumSingleton {

    enum SerEnumSingleton {
        INSTANCE;
        private String content;

        public String getContent() {
            return content;
        }

        /**
         * 内容测试
         * @param content content
         */
        public void setContent(String content) {
            this.content = content;
        }

        SerEnumSingleton() { }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SerEnumSingleton serEnumSingleton = SerEnumSingleton.INSTANCE;
        serEnumSingleton.setContent("singleton1");
        // 这段代码是执行不了的，因为反射的时候会判断类是不是枚举类型，如果是枚举类型，则会抛出异常
        Class<SerEnumSingleton> serEnumSingletonClass = SerEnumSingleton.class;
        Constructor<SerEnumSingleton> constructor=serEnumSingletonClass.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);
        SerEnumSingleton serEnumSingleton1=constructor.newInstance("test",123);
        System.out.println(serEnumSingleton==serEnumSingleton1);
    }
}
