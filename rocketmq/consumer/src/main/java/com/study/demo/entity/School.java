package com.study.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tiger
 * @date 2019-10-15
 * @see com.study.demo.entity
 **/
@Data
public class School implements Serializable {
    private String id;

    private String cityName;

    private String name;

    private String description;
}
