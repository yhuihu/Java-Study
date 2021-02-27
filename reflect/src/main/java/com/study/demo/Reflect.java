package com.study.demo;

import com.study.demo.entity.School;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yhhu
 * @date 2020/12/4
 * @description
 */
public class Reflect {
    /**
     * 通过反射创建对象
     *
     * @return {@link School}
     * @throws ClassNotFoundException e
     * @throws InstantiationException e
     * @throws IllegalAccessException e
     */
    public static School createNewInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> newClass = Class.forName("com.study.demo.entity.School");
        Object o = newClass.newInstance();
        School school = (School) o;
        school.setSchoolName("hello");
        school.setProvidence("gd");
        return school;
    }

    /**
     * 获取单个构造函数
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static void getConstructor() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> newClass = Class.forName("com.study.demo.entity.School");
//        获取单个的"公有的"构造方法
        Constructor<?> constructor = newClass.getConstructor();
//        获取单个”私有的“构造方法
        Constructor<?> declaredConstructor = newClass.getDeclaredConstructor(String.class, String.class);
        Object o = constructor.newInstance();
//        暴力访问，忽略修饰符
        declaredConstructor.setAccessible(true);
        Object o1 = declaredConstructor.newInstance("hello", "hello");
    }


    public static void getConstructors() throws ClassNotFoundException {
        Class<?> newClass = Class.forName("com.study.demo.entity.School");
        System.out.println("---------------获取所有公有构造函数-----------------");
        Constructor<?>[] constructors = newClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("---------------获取所有构造函数-----------------");
        Constructor<?>[] declaredConstructors = newClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
    }

    public static void getField() throws ClassNotFoundException {
        Class<?> newClass = Class.forName("com.study.demo.entity.School");
        System.out.println("-------------------获取所有公有字段");
        Field[] fields = newClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("-------------------获取所有字段");
        Field[] declaredFields = newClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }

    public static void getMethod() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> newClass = Class.forName("com.study.demo.entity.School");
        Method[] methods = newClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        School o = (School)newClass.newInstance();
        Method setName = newClass.getMethod("setSchoolName", String.class);
//     * <p>If the underlying method is static, then the specified {@code obj}
//     * argument is ignored. It may be null.
        setName.invoke(o, "Hello");
        System.out.println(o);
    }
}
