package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {

    public T findByEmail(String email);

}