package com.cym.dao;

import org.springframework.stereotype.Repository;

import com.cym.entity.Languages;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LanguagesDao extends JpaRepository<Languages, Long>{
    Languages findByLangName(String langName);
}
