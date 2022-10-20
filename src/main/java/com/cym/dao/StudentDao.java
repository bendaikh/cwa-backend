package com.cym.dao;

import org.springframework.stereotype.Repository;

import com.cym.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentDao extends JpaRepository<Student,Long> {
    
}
