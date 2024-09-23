package com.study.demo.dao;

import com.study.config.ProxyComponent;
import com.study.config.ProxyMethod;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 19:45
 */
@ProxyComponent
public interface DemoDao {

    @ProxyMethod(impl = "MySql,Db2,Oracle.syn")
    void sayHello();

}
