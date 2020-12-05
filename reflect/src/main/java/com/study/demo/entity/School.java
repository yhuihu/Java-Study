package com.study.demo.entity;

import lombok.Data;

/**
 * @author yhhu
 * @date 2020/12/4
 * @description
 */
@Data
public class School {
    private String schoolName;
    private String providence;
    private Integer studentCount;
    public String publicString;

    public School() {
        System.out.println("公有无参构造函数");
    }

    public School(String schoolName) {
        System.out.println("单参数构造");
    }

    protected School(int count) {
        System.out.println("保护构造函数");
    }

    private School(String schoolName, String providence) {
        System.out.println("私有构造");
    }
}
