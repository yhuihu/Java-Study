package com.study.config;

import lombok.Getter;
import lombok.Setter;

import java.beans.Transient;
import java.util.Map;

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
    @Setter
    private String className;

    /**
     * 实现类后缀
     */
    @Setter
    @Getter
    private String defaultImpl;

    private Class<?> classInfo;

    /**
     * 方法级别控制
     */
    @Setter
    @Getter
    private Map<String, String> methodsImpl;

    public String getClassName() {
        return className;
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
