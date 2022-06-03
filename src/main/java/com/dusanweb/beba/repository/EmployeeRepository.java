package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Employee;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface EmployeeRepository extends UserBaseRepository <Employee>{
    @Override
    Optional<Employee> findByEmail(String email);
    @Override
    Optional<Employee> findByEmailAndPassword(String email, String password);
}
