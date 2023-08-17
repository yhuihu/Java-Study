package com.study.dao.impl;

import com.study.dao.DemoDao;
import org.springframework.stereotype.Repository;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 20:19
 */
@Repository
public class DemoDaoOracle implements DemoDao {
    @Override
    public void sayHello() {
        System.out.println("run oracle dao");
    }
}
