package com.study.mybatis.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author Tiger
 * @date 2020-06-17
 * @see com.study.mybatis.common.service
 **/
public interface BaseService<T> {
    T selectById(String id);

    IPage<T> selectByPage(int page, int size);
}
