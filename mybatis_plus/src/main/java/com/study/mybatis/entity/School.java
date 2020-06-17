package com.study.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "school")
public class School {
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @TableField(value = "cityName")
    private String cityname;

    @TableField(value = "name")
    private String name;

    @TableField(value = "description")
    private String description;

    public static final String COL_ID = "id";

    public static final String COL_CITYNAME = "cityName";

    public static final String COL_NAME = "name";

    public static final String COL_DESCRIPTION = "description";
}