package com.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description JDK代理方式进行交易转发
 * @date 2023/6/4 22:41
 */
public class ProxyHandler implements InvocationHandler {

    private Object proxy;

    public ProxyHandler(final Object o) {
        this.proxy = o;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        System.out.println("买房准备");
        Object o = method.invoke(proxy, args);
        System.out.println("买完了");
        return null;
    }
}
