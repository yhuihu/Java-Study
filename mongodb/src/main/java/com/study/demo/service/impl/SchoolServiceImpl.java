package com.study.demo.service.impl;

import com.study.demo.dao.SchoolRepository;
import com.study.demo.dao.SchoolRepository1;
import com.study.demo.entity.School;
import com.study.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tiger
 * @date 2019-10-14
 * @see com.study.demo.service.impl
 **/
@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolRepository1 schoolRepository1;
    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository,SchoolRepository1 schoolRepository1){
        this.schoolRepository=schoolRepository;
        this.schoolRepository1=schoolRepository1;
    }
    @Override
    public School save(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School findById(Long id) {
        Optional<School> school=schoolRepository.findById(id);
        return school.orElse(null);
    }

    @Override
    public List<School> findAllSchool() {
        Sort sort = Sort.by("id").descending();
        return schoolRepository.findAll(sort);
    }

    @Override
    public School modifySchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public void deleteSchool(Long id) {
        Optional<School> optional=schoolRepository.findById(id);
        optional.ifPresent(schoolRepository::delete);
    }

    @Override
    public School queryFindOne(Long id) {
        return schoolRepository1.queryById(id);
    }
}
