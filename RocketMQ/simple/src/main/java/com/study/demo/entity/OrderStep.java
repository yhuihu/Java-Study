package com.study.demo.entity;

import lombok.Data;

/**
 * @author Tiger
 * @date 2019-12-24
 * @see com.study.demo.entity
 **/
@Data
public class OrderStep {
    private long orderId;
    private String desc;

    @Override
    public String toString() {
        return "OrderStep{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }
}
