package com.study.demo.service;

import com.study.demo.entity.School;

/**
 * @author Tiger
 * @date 2019-10-16
 * @see com.study.demo.service
 **/
public interface SchoolService {
    /**
     * 保存学校信息到数据库并发送到MQ
     * @param school 实体类
     * @return void
     **/
    void saveSchool(School school);
}
