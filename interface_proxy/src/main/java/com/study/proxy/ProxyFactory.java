package com.study.proxy;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 22:49
 */
public class ProxyFactory<T> implements FactoryBean<T> {

    private Class<T> beanClass;

    public ProxyFactory(Class<T> aclass) {
        this.beanClass = aclass;
    }

    @Override
    public T getObject() throws Exception {
        // 反射调用proxyHandle的invoke时，可以从method对象获取到beanClass信息
        return (T) Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(),
                new Class[]{beanClass}, new ProxyHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return beanClass;
    }
}
