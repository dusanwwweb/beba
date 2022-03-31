package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Parent;

import javax.transaction.Transactional;

@Transactional
public interface ParentRepository extends UserBaseRepository<Parent>{
    public Parent findByEmail(String email);
}
