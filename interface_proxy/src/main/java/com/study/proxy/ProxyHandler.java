package com.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description JDK代理方式进行交易转发
 * @date 2023/6/4 22:41
 */
public class ProxyHandler implements InvocationHandler {

    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        String className = method.getDeclaringClass().getName();
        char firstChar = className.charAt(0);
        // 获取bean名称
        String beanName = String.valueOf(firstChar).toLowerCase(Locale.getDefault()) + className.substring(1);


    }

}
