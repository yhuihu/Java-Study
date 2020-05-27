package com.study.demo.dao;

import com.study.demo.entity.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Tiger
 * @date 2019-10-14
 * @see com.study.demo.dao
 **/
@Repository
public interface SchoolRepository1 extends MongoRepository<School, Long> {
    /**
     * 测试一下
     *
     * @param id 学校编号
     * @return com.study.demo.entity.School
     **/
    @Query("{ 'id' : ?0}")
    School queryById(Long id);
}
