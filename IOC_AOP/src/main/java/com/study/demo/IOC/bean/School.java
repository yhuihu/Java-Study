package com.study.demo.IOC.bean;

/**
 * @author Tiger
 * @date 2019-12-18
 * @see com.study.demo.IOC.bean
 **/
public class School {
    private String name;
    private String location;

    @Override
    public String toString() {
        return "----------------------schoolName:"+name+"------------location:"+location;
    }
}
