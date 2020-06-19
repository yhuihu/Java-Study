package com.study.mybatis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mybatis.entity.School;
import com.study.mybatis.entity.Student;
import com.study.mybatis.service.StudentService;
import com.study.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiger
 * @date 2020-06-16
 * @see com.study.mybatis.controller
 **/
@RestController
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public School getSchool(@PathVariable String id) {
        return testService.getById(id);
    }

    @GetMapping("/{page}/{size}")
    public IPage<School> getSchoolByPage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<School> iPage = new Page<>(page, size);
        return testService.page(iPage);
    }

    @PostMapping("/more")
    public void addStudent() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setName(String.valueOf(i));
            student.setSchoolId("001404b7a1e94394bab5cc49b5e0ba7d");
            studentService.save(student);
            list.add(student);
        }
        studentService.saveOrUpdateBatch(list);
    }

    @PostMapping("/transactional")
    public void transactional() throws Exception {
        testService.transactionalDefaultSchool();
    }
}
