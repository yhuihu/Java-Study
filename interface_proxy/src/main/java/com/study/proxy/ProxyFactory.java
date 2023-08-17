package com.study.proxy;

import com.study.config.ScanProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/8/17 22:44
 */
public class ProxyFactory {

    /**
     * key: 类名
     * value: {@link ScanProperty}
     */
    public static Map<String, ScanProperty> scanPropertiesMap = new HashMap<>();

}
