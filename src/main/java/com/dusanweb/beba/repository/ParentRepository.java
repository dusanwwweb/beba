package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Parent;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface ParentRepository extends UserBaseRepository<Parent>{
    @Override
    Optional<Parent> findByEmail(String email);
}
