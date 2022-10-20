package com.cym.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends User{
    private String studentName;
    private String addressLine1;
    private String addressLine2;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private String guardian;
    // private Date birthdate;
    // private String age;
}
