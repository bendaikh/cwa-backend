package com.cym.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Teacher extends User {
    
    private String address;
    private String phoneNumber;
    private String email;
    private String className;
    private String agreedHourlyRate;

    
}
