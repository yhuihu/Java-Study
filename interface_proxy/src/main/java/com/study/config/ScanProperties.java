package com.study.config;

import lombok.Data;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 20:02
 */
@Data
public class ScanProperties {

    /**
     * 包扫描路径
     */
    private String scanPath;

    /**
     * 扫描的接口实现类后缀
     */
    private String proxyImpl;


}
