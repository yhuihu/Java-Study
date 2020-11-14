package xyz.study.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * @author yhhu
 * @date 2020/11/1
 * @description
 */
@Data
public class FundData {
    private List<String> datas;
    private Integer allRecords;
    private Integer pageIndex;
    private Integer pageNum;
    private Integer allPages;
}
