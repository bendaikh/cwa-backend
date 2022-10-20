package com.cym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cym.dao.StudentDao;
import com.cym.entity.Student;

@Service
public class StudentService {
    
    @Autowired
    private StudentDao studentDao;

    public List<Student> allStudents(){
        return studentDao.findAll();
    }
}
