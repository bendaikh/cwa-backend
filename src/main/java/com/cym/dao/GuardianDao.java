package com.cym.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cym.entity.Guardian;

@Repository
public interface GuardianDao extends JpaRepository<Guardian,Long> {

}
