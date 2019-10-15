package com.study.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author Tiger
 * @date 2019-10-14
 * @see com.study.demo.entity
 **/
@Data
public class School {
    @Id
    private Long id;

    private String cityName;

    private String name;

    private String description;
}
