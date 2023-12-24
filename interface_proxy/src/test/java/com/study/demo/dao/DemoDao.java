package com.study.demo.dao;

import com.study.config.ProxyConfig;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 19:45
 */
@ProxyConfig(impl = "MySql,Db2,Oracle.syn")
public interface DemoDao {
    void sayHello();
}
