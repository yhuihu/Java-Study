package com.study.config;

import java.beans.Transient;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description 扫描配置信息
 * @date 2023/6/4 20:02
 */
public class ScanProperty {

    /**
     * 类名
     */
    private String className;

    /**
     * 实现类后缀
     */
    private String classImpl;

    private Class<?> classInfo;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassImpl() {
        return classImpl;
    }

    public void setClassImpl(String classImpl) {
        this.classImpl = classImpl;
    }

    @Transient
    public Class<?> getClassInfo() {
        return classInfo;
    }

    @Transient
    public void setClassInfo(Class<?> classInfo) {
        this.classInfo = classInfo;
    }
}
