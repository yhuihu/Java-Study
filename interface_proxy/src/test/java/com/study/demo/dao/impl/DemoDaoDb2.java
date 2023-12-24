package com.study.demo.dao.impl;

import com.study.demo.dao.DemoDao;
import org.springframework.stereotype.Repository;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 20:19
 */
@Repository
public class DemoDaoDb2 implements DemoDao {
    @Override
    public void sayHello() {
        System.out.println("run db2 dao");
    }
}
