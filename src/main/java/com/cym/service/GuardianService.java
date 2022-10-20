package com.cym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cym.dao.GuardianDao;
import com.cym.entity.Guardian;

@Service
public class GuardianService {
    @Autowired
    private GuardianDao guardianDao;
    
    public List<Guardian> allGuardians(){
        return guardianDao.findAll();
    }
}
