package com.study.demo.controller;

import com.study.demo.entity.School;
import com.study.demo.service.SchoolService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tiger
 * @date 2019-10-15
 * @see com.study.demo.controller
 **/
@RestController
public class MqController {
    @Resource
    SchoolService schoolService;
    @RequestMapping("save")
    public String send(@RequestBody School school){
        schoolService.saveSchool(school);
        return "success";
    }
}
