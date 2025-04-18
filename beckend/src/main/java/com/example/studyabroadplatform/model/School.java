package com.example.studyabroadplatform.model;

import javax.persistence.*;


@Entity
@Table(name = "schools")
public class School  {

    @Id
    private Long id; // 主键

    @Column(name = "school_id", nullable = false)
    private Integer schoolId; //学校ID

    @Column(name = "name")
    private String schoolName;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail ;

    @Column(name = "website")
    private String website;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}