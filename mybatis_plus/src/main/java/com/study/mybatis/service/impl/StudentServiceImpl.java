package com.study.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mybatis.entity.Student;
import com.study.mybatis.mapper.StudentMapper;
import com.study.mybatis.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;

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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transactionalDefaultStudent(String schoolId) throws Exception {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            Random random = new Random();
            int i1 = random.nextInt();
            student.setId(Long.valueOf(i + i1 + schoolId));
            student.setName(String.valueOf(i));
            student.setSchoolId(schoolId);
            studentMapper.insert(student);
        }
    }

    @Override
    public void transactionalSupportStudent() throws Exception {

    }
}
