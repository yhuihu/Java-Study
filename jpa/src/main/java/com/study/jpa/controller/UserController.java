package com.study.jpa.controller;

import com.study.jpa.dao.UserRepository;
import com.study.jpa.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable(name = "id")String id){
        User one = userRepository.findById(Long.valueOf(id)).get();
        return ResponseEntity.status(HttpStatus.OK).body(one);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id")String id){
        userRepository.delete(new User(id));
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
