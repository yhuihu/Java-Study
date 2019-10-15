package com.study.demo.controller;

import com.study.demo.entity.School;
import com.study.demo.service.SchoolService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tiger
 * @date 2019-10-14
 * @see com.study.demo.controller
 **/
@RestController
@RequestMapping("school")
public class SchoolController {

    @Resource
    SchoolService schoolService;

    @PostMapping()
    public School saveSchool(@RequestBody School school) {
        return schoolService.save(school);
    }

    @GetMapping("{id}")
    public School getSchool(@PathVariable Long id) {
        return schoolService.findById(id);
    }

    @GetMapping()
    public List<School> getAllSchool() {
        return schoolService.findAllSchool();
    }

    @PutMapping()
    public School updateSchool(@RequestBody School school) {
        return schoolService.modifySchool(school);
    }

    @DeleteMapping("{id}")
    public void deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
    }

    @GetMapping("Query/{id}")
    public School getOne(@PathVariable Long id) {
        return schoolService.queryFindOne(id);
    }
}
