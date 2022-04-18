package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    //Optional is primarily intended for use as a method return type where
    // there is a clear need to represent "no result," and where using null is likely to cause errors
    Optional<Child> findByFirstName(String firstName);
    List<Child> findByDateOfBirthOrderByDateOfBirthAsc(LocalDate dateOfBirth);

}