package com.example.studyabroadplatform.model;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends User {
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "contact_phone")
    private String contactPhone;
    
    @Column(name = "contact_email")
    private String contactEmail;
    
    // 保留原有department字段
    @Column
    private String department;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}