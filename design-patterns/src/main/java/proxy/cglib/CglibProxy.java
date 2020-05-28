package proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Tiger
 * @date 2020-03-10
 * @see proxy.cglib
 **/
public class CglibProxy implements MethodInterceptor {

    private Object object;

    public Object getInstance(Object o){
        this.object=o;
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("买房前准备");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("买房后装修");
        return result;
    }
}
