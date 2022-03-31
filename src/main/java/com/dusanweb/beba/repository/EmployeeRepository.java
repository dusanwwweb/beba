package com.dusanweb.beba.repository;

import com.dusanweb.beba.model.Employee;

import javax.transaction.Transactional;

@Transactional
public interface EmployeeRepository extends UserBaseRepository <Employee>{
}
