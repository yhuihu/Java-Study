package proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see proxy.jdkproxy
 **/
public class ProxyMethod implements InvocationHandler {
    private Object object;
    public ProxyMethod(final Object o){
        this.object=o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房准备");
        Object o=method.invoke(object,args);
        System.out.println("买完了");
        return null;
    }
}
