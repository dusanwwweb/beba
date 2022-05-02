package com.dusanweb.beba.controller;

import com.dusanweb.beba.model.Employee;
import com.dusanweb.beba.repository.EmployeeRepository;
import com.dusanweb.beba.service.EmployeeServiceImpl;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    //http://localhost:8080/api/employee
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employee = new ArrayList<>();
            employee.addAll(employeeService.findAll());

            if (employee.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("200 (OK)");
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
