package com.study.mybatis.common.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mybatis.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author Tiger
 * @date 2020-06-17
 * @see com.study.mybatis.common.service.impl
 **/
public class BaseServiceImpl<T> implements BaseService<T> {

    @Resource
    BaseMapper<T> baseMapper;

    @Override
    public T selectById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public IPage<T> selectByPage(int page, int size) {
        IPage<T> userPage = new Page<>(page, size);
        IPage<T> schoolIPage = baseMapper.selectPage(userPage, null);
        return schoolIPage;
    }
}
