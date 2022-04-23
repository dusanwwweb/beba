package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    //Derived query methods
    List<Child> findBySex(char sex);

    @Override
    Child getById(Long aLong);

    //Optional is primarily intended for use as a method return type where
    // there is a clear need to represent "no result," and where using null is likely to cause errors
    Optional<Child> findByFirstName(String firstName);

    List<Child> findByDateOfBirth(LocalDate dateOfBirth);

    @Query(value = "SELECT c FROM Child c ORDER BY c.weight DESC", nativeQuery = false)
    List<Child> findAllOrderByWeightDesc();

    @Query(value = "SELECT c FROM Child c ORDER BY c.allergyType ASC", nativeQuery = false)
    List<Child> findAllOrderByAllergyTypeAsc();

    @Query(value = "SELECT c FROM Child c WHERE c.firstName = ?1 AND c.lastName = ?2", nativeQuery = false)
    List<Child> findByFirstNameAndLastName(String firstName, String lastName);

    //@Query(value = "SELECT CONCAT(first_name,' ', last_name) FROM beba.child ORDER BY date_of_birth ASC", nativeQuery = true)
    //List<Child> findAllOrderByDateOfBirthAsc();

}