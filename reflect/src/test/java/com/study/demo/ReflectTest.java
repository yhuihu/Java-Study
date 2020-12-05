package com.study.demo;

import com.study.demo.entity.School;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yhhu
 * @date 2020/12/5
 * @description
 */
public class ReflectTest {
    @Test
    public void classTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//        直接new一个对象获取class
        School school = new School();
        Class<? extends School> aClass = school.getClass();
//        通过类直接获取class
        Class<School> bClass = School.class;
        if (aClass == bClass) {
            System.out.println("aClass=bClass");
        }
//        利用反射创建对象获取class
        School newInstance = Reflect.createNewInstance();
        Class<? extends School> aClass1 = newInstance.getClass();
        if (aClass1 == bClass) {
            System.out.println("a=b=c");
        }
    }

    @Test
    public void constructorTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflect.getConstructor();
    }

    @Test
    public void constructorsTest() throws ClassNotFoundException {
        Reflect.getConstructors();
    }

    @Test
    public void filedTest() throws ClassNotFoundException {
        Reflect.getField();
    }

    @Test
    public void reflectT() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        Class<? extends List> aClass = list.getClass();
        Method add = aClass.getMethod("add", Object.class);
        add.invoke(list, 789);
        for (Object s : list) {
//            class java.lang.String
//            class java.lang.String
//            class java.lang.Integer
            System.out.println(s.getClass());
        }
    }

    @Test
    public void reflectMethod() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Reflect.getMethod();
    }
}
