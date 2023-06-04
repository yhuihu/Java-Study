package com.study.dao.impl;

import com.study.dao.DemoDao;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 20:19
 */
public class DemoDaoMySql implements DemoDao {
    @Override
    public void sayHello() {
        System.out.println("run mysql dao");
    }
}
