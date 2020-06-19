package com.study.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mybatis.entity.Student;
import com.study.mybatis.mapper.StudentMapper;
import com.study.mybatis.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Tiger
 * @date 2020-06-18
 * @see com.study.mybatis.service.impl
 **/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    StudentMapper studentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transactionalServiceStudent(String schoolId) throws Exception {
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setId(Long.valueOf(i + schoolId));
            student.setName(String.valueOf(i));
            student.setSchoolId(schoolId);
            studentMapper.insert(student);
        }
    }
}
