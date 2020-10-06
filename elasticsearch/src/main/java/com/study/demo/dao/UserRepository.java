package com.study.demo.dao;

import com.study.demo.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tiger
 * @date 2020-10-01
 * @see com.study.demo.dao
 **/
@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

}
