package com.example.easy.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yhhu
 * @date 2021/6/7
 * @description
 */
@Data
public class TableExcel implements Serializable {
    @ExcelProperty(value = {"字段名"}, index = 6)
    private String colName;
    @ExcelProperty(value = {"字段中文名"}, index = 7)
    private String colEnName;
    @ExcelProperty(value = {"字段长度"}, index = 8)
    private String typeAndLength;
}
