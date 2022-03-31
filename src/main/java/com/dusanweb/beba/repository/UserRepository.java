package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.User;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends UserBaseRepository <User>{
    //public User findByEmail(String email);
}
