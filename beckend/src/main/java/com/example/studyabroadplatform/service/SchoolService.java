package com.example.studyabroadplatform.service;

import com.example.studyabroadplatform.model.School;
import com.example.studyabroadplatform.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService extends BaseUserService {
    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public School findById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public School save(School school) {
        return schoolRepository.save(school);
    }

    public void deleteById(Long id) {
        schoolRepository.deleteById(id);
    }
}