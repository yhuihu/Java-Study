package com.study.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.study.demo.entity.School;
import com.study.demo.service.ProviderService;
import com.study.demo.service.SchoolService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Tiger
 * @date 2019-10-16
 * @see com.study.demo.service.impl
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SchoolServiceImpl implements SchoolService {
    @Resource
    ProviderService providerService;
    @Resource
    JdbcTemplate jdbcTemplate;
    @Override
    public void saveSchool(School school) {
        for (int i = 0; i < 1000; i++) {
            JSONObject jsonObject = new JSONObject();
            school.setId(UUID.randomUUID().toString().replace("-",""));
            jsonObject.put("school",school);
            providerService.send(jsonObject.toJSONString());
        }
//        JSONObject jsonObject = new JSONObject();
//        school.setId(UUID.randomUUID().toString().replace("-",""));
//        jsonObject.put("school",school);
//        providerService.send(jsonObject.toJSONString());
//        jdbcTemplate.update("insert into school(school_name,city_name,description) values(?,?,?);",school.getName(),school.getCityName(),school.getDescription());
    }
}
