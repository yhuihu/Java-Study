package com.study.demo.service;

import com.study.demo.entity.School;

import java.util.List;

/**
 * @author Tiger
 * @date 2019-10-14
 * @see com.study.demo.service
 **/
public interface SchoolService {
    /**
     * 保存school信息
     * @param school 实体类
     * @return com.study.demo.entity.School
     **/
    School save(School school);

    /**
     * 查找学校
     * @param id int
     * @return com.study.demo.entity.School
     **/
    School findById(Long id);

    /**
     * 查找所有学校
     * @return java.util.List<com.study.demo.entity.School>
     **/
    List<School> findAllSchool();

    /**
     * 修改
     * @param school 实体类
     * @return com.study.demo.entity.School
     **/
    School modifySchool(School school);

    /**
     * 删除
     * @param id 编号
     * @return void
     **/
    void deleteSchool(Long id);

    /**
     * 手写
     * @param id int
     * @return com.study.demo.entity.School
     **/
    School queryFindOne(Long id);

}
