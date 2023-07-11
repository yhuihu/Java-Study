package com.study.config;

import lombok.Data;

import java.util.List;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description 扫描配置信息
 * @date 2023/6/4 20:02
 */
@Data
public class ScanProperties {

    /**
     * 一组配置的位移标志
     */
    private String primaryKey;

    /**
     * 包扫描路径
     */
    private String scanPath;

    /**
     * 扫描的接口实现类后缀
     */
    private String proxyImpl;

    /**
     * 类名以什么作为结尾
     */
    private String endWith;

    private List<String> proxyClass;

}
