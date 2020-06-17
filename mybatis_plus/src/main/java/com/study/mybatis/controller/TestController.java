package com.study.mybatis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.mybatis.entity.School;
import com.study.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tiger
 * @date 2020-06-16
 * @see com.study.mybatis.controller
 **/
@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/{id}")
    public School getSchool(@PathVariable String id) {
        return testService.selectById(id);
    }

    @GetMapping("/{page}/{size}")
    public IPage<School> getSchoolByPage(@PathVariable Integer page, @PathVariable Integer size) {
        return testService.selectByPage(page, size);
    }
}
