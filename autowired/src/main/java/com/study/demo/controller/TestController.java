package com.study.demo.controller;

import com.study.demo.dao.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tiger
 * @date 2020-10-18
 * @see com.study.demo.controller
 **/
@RestController
public class TestController {
    @Autowired
    Test test;

    public ResponseEntity test() {
        test.sout();
        return new ResponseEntity(HttpStatus.OK);
    }
}
