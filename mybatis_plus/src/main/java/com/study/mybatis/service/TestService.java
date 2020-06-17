package com.study.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mybatis.common.service.impl.BaseServiceImpl;
import com.study.mybatis.entity.School;
import com.study.mybatis.mapper.SchoolMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tiger
 * @date 2020-06-16
 * @see com.study.mybatis.service
 **/
@Service
public class TestService extends BaseServiceImpl<School> {
    @Resource
    SchoolMapper schoolMapper;

}
