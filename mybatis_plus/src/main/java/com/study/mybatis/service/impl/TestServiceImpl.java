package com.study.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mybatis.entity.School;
import com.study.mybatis.mapper.SchoolMapper;
import com.study.mybatis.service.StudentService;
import com.study.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Tiger
 * @date 2020-06-18
 * @see com.study.mybatis.service.impl
 **/
@Service
public class TestServiceImpl extends ServiceImpl<SchoolMapper, School> implements TestService {
    @Resource
    SchoolMapper schoolMapper;
    @Autowired
    StudentService studentService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionalDefaultSchool() throws Exception {
        for (int i = 0; i < 1000; i++) {
            School school = new School();
            school.setCityname("sz");
            school.setDescription("wuhu");
            String s = String.valueOf(i);
            school.setId(s);
            school.setName(String.valueOf(i));
            schoolMapper.insert(school);
            studentService.transactionalDefaultStudent(s);
            if (i == 95) {
                throw new Exception();
            }
        }
    }

    @Override
    public void transactionalSupportSchool() throws Exception {

    }
}
