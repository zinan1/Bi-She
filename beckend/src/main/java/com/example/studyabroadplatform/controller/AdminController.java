package com.example.studyabroadplatform.controller;

import com.example.studyabroadplatform.model.Student;
import com.example.studyabroadplatform.model.School;
import com.example.studyabroadplatform.service.StudentService;
import com.example.studyabroadplatform.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private StudentService studentService;

    @Autowired
    public SchoolService schoolService;

    // 学生管理
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        return ResponseEntity.ok(studentService.save(student));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 校方管理
    @GetMapping("/schools")
    public List<School> getAllSchools() {
        return schoolService.findAll();
    }

    @GetMapping("/schools/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        School school = schoolService.findById(id);
        return school != null ? ResponseEntity.ok(school) : ResponseEntity.notFound().build();
    }

    @PostMapping("/schools")
    public School createSchool(@RequestBody School school) {
        return schoolService.save(school);
    }

    @PutMapping("/schools/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School school) {
        school.setId(id);
        return ResponseEntity.ok(schoolService.save(school));
    }

    @DeleteMapping("/schools/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}