package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {

    //Optional is primarily intended for use as a method return type where
    // there is a clear need to represent "no result," and where using null is likely to cause errors
    Optional<Child> findByFirstName(String firstName);
}