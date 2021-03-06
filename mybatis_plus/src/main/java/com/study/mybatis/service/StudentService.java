package com.study.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mybatis.entity.Student;

/**
 * @author Tiger
 * @date 2020-06-18
 * @see com.study.mybatis.service
 **/
public interface StudentService extends IService<Student> {
    public void transactionalDefaultStudent(String schoolId) throws Exception;

    public void transactionalSupportStudent() throws Exception;
}
