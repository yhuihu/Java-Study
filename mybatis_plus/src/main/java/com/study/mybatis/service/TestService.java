package com.study.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.mybatis.entity.School;

/**
 * @author Tiger
 * @date 2020-06-18
 * @see com.study.mybatis.service
 **/
public interface TestService extends IService<School> {
    /**
     * 支持当前事务,如果有就加入当前事务中;如果当前方法没有事务,就新建一个事务;
     * @throws Exception
     */
    public void transactionalDefaultSchool() throws Exception;

    /**
     *支持当前事务,如果有就加入当前事务中;如果当前方法没有事务,就以非事务的方式执行;
     * @throws Exception
     */
    public void transactionalSupportSchool() throws Exception;
}
