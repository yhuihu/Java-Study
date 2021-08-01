package com.example.easy.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author yhhu
 * @date 2021/6/7
 * @description
 */
@Data
public class BookExcel {
    @ExcelProperty(value = {"字段中文"}, index = 0)
    private String colName;
    @ExcelProperty(value = {"字段英文"}, index = 1)
    private String colEnName;
}
