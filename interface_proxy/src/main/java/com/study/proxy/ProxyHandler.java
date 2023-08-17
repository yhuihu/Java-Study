package com.study.proxy;

import com.study.config.ScanProperty;
import com.study.util.SpringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
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
        String className = method.getDeclaringClass().getSimpleName();
        char firstChar = className.charAt(0);
        // 获取bean名称
        String beanName = String.valueOf(firstChar).toLowerCase(Locale.getDefault()) + className.substring(1);
        // 获取需要的实现类
        ScanProperty scanProperty = ProxyFactory.scanPropertiesMap.get(beanName);

        // 第一个方法的执行结果作为返回
        String classImpl = scanProperty.getClassImpl();
        String[] impls = classImpl.split(",");
        if (impls.length == 0) {
            throw new RuntimeException("className " + className + " have not impl methods");
        }
        Object bean = SpringUtils.applicationContext.getBean(beanName + impls[0]);
        Object returnResult = invokeBeanMethod(bean, method, args);

        if (impls.length > 1) {
            for (int i = 1; i < impls.length; i++) {
                String impl = impls[i];
                // 携带.syn 证明该方法需要同步执行，否则为异步执行
                if (impl.contains(".syn")) {
                    bean = SpringUtils.applicationContext.getBean(beanName + impls[0]);
                    Object bakResult = invokeBeanMethod(bean, method, args);
                } else {

                }
            }
        }

        return returnResult;
    }

    private Object invokeBeanMethod(Object bean, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(bean, args);
    }

    private Object invokeBeanMethodAsync(Object bean, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(bean, args);
    }

}
