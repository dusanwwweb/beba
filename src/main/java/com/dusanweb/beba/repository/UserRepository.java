package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends UserBaseRepository <User>{
    @Override
    Optional<User> findByEmail(String email);
}
