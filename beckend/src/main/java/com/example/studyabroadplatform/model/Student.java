package com.example.studyabroadplatform.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    @Column(name = "student_id", nullable = false)
    private Integer studentId; // 学生ID

    @Column(nullable = false, length = 50)
    private String name; // 姓名

    @Enumerated(EnumType.STRING)
    private Gender gender; // 性别

    @Column(name = "birth_date")
    private Date birthDate; // 出生日期

    @Column(name = "contact_phone", length = 20)
    private String contactPhone; // 联系电话

    @Column(name = "contact_email", length = 100)
    private String contactEmail; // 联系邮箱

    @Column
    private String address; // 地址

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // 性别枚举
    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}