package com.study.demo.controller;

import com.study.demo.dao.UserRepository;
import com.study.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Tiger
 * @date 2020-08-13
 * @see com.study.demo.controller
 **/
@RestController
public class TestController {
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @GetMapping()
    public ResponseEntity<HashMap<String, Object>> getData() {
        HashMap<String, Object> map = new HashMap<>();
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<User> all = userRepository.findAll(pageable);
        map.put("user", all.getContent());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> postData(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteData(@PathVariable(name = "id") String id) {
        userRepository.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("id")
    public ResponseEntity<Void> updateData(@PathVariable(name = "id") String id, User user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
