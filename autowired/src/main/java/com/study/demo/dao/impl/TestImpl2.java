package com.study.demo.dao.impl;

import com.study.demo.dao.Test;
import org.springframework.stereotype.Service;

/**
 * @author Tiger
 * @date 2020-10-18
 * @see com.study.demo.dao.impl
 **/
@Service
public class TestImpl2 implements Test {
    @Override
    public void sout() {
        System.out.println("hello world");
    }
}
