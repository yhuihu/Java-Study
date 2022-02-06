package com.study.jpa.controller;

import com.study.jpa.dao.OrderRepository;
import com.study.jpa.entity.Order;
import com.study.jpa.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    OrderRepository orderRepository;

    @PostMapping()
    public ResponseEntity addOrder(@RequestBody Order order) {
        order.setUser(new User("1"));
        orderRepository.save(order);
        return ResponseEntity.ok("success");
    }
}
