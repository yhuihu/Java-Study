package com.study.demo.IOC.bean;

/**
 * @author Tiger
 * @date 2019-12-18
 * @see com.study.demo.IOC.bean
 **/
public class Student {
    private String name;
    private School school;

    @Override
    public String toString() {
        return "name:" + name + "------------------school" + school;
    }
}
