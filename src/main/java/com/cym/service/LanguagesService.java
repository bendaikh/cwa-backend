package com.cym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cym.dao.LanguagesDao;
import com.cym.entity.Languages;

@Service
public class LanguagesService {

    @Autowired
    private LanguagesDao languagesDao;

    public Languages saveLanguage(Languages languages){
        languagesDao.save(languages);
        return languages;
    }

    public List<Languages> allLanguage(){
        return languagesDao.findAll();
    }
    
}
