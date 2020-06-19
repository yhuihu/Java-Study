package com.study.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mybatis.entity.School;

/**
 * @author Tiger
 * @date 2020-06-18
 * @see com.study.mybatis.service
 **/
public interface TestService extends IService<School> {
    public void transactionalServiceSchool() throws Exception;
}
