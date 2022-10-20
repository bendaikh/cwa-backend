package com.cym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cym.dao.TeacherDao;
import com.cym.entity.Teacher;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;
    public List<Teacher> allTeachers(){
        return teacherDao.findAll();
    }
}
