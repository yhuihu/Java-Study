package com.study.demo.dao;

import com.study.demo.entity.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tiger
 * @date 2019-10-14
 * @see com.study.demo.dao
 **/
@Repository
public interface SchoolRepository extends MongoRepository<School, Long> {

}
