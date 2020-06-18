package com.study.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mybatis.entity.Student;
import com.study.mybatis.mapper.StudentMapper;
import com.study.mybatis.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author Tiger
 * @date 2020-06-18
 * @see com.study.mybatis.service.impl
 **/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
