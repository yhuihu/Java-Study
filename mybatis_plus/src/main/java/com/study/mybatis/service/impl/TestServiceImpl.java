package com.study.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.mybatis.entity.School;
import com.study.mybatis.mapper.SchoolMapper;
import com.study.mybatis.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author Tiger
 * @date 2020-06-18
 * @see com.study.mybatis.service.impl
 **/
@Service
public class TestServiceImpl extends ServiceImpl<SchoolMapper, School> implements TestService {
}
