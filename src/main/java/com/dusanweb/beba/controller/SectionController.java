package com.dusanweb.beba.controller;

import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.model.Employee;
import com.dusanweb.beba.model.Section;
import com.dusanweb.beba.repository.ChildRepository;
import com.dusanweb.beba.repository.SectionRepository;
import com.dusanweb.beba.service.EmployeeServiceImpl;
import com.dusanweb.beba.service.SectionServiceImpl;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Builder
@RestController
@RequestMapping("/api")
public class SectionController {

    @Autowired
    private SectionServiceImpl sectionService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ChildRepository childRepository;

    //http://localhost:8080/api/section
    @GetMapping("/section")
    public ResponseEntity<List<Section>> getAllSections() {
        try {
            List<Section> sections = new ArrayList<>(sectionService.findAll());

            if (sections.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("200 (OK)");

            return new ResponseEntity<>(sections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/section/1
    @GetMapping("/section/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable("id") String id) {
        Optional<Section> sectionData = sectionService.findById(Long.parseLong(id));
        if (sectionData.isPresent()) {
            log.trace("Get section by id: {}", id);
            return new ResponseEntity<>(sectionData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/section/1/children/6
    @PutMapping("/section/{s_id}/children/{c_id}")
    public ResponseEntity<Section> addOndChildToSection(
            @PathVariable("s_id") String s_id,
            @PathVariable("c_id") String c_id
    ) {
        Optional<Section> section = sectionService.findById(Long.parseLong(s_id));
        Child child = childRepository.getById(Long.parseLong(c_id));

        if (section.isPresent()) {
            Section _section = section.get();
            _section.addChild(child);
            final Section updatedSection = sectionService.save(_section);
            return new ResponseEntity<>(updatedSection, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/section/2/child
    @PostMapping("/section/{id}/child")
    public ResponseEntity<Section> addNewChildToSection(@PathVariable("id") String id, @RequestBody Child child) {
        Optional<Section> sectionData = sectionService.findById(Long.parseLong(id));

        if (sectionData.isPresent()) {
            Section _section = sectionData.get();
            _section.addChild(child);
            childRepository.save(child);
            log.trace("Get section with ID: {}", id);
            return new ResponseEntity<>(sectionData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/section/1/children
    @GetMapping("/section/{id}/children")
    public ResponseEntity<Set<Child>> getAllChildrenFromSection(@PathVariable String id) {
        Optional<Section> sectionData= sectionService.findById(Long.parseLong(id));

        if (sectionData.isPresent()) {
            log.trace("Get section with ID: {}", id);
            return new ResponseEntity<>(sectionData.get().getChildren(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/section/3/employees
    @GetMapping("/section/{id}/employees")
    public ResponseEntity<Set<Employee>> getAllEmployeesFromSection(@PathVariable String id) {
        Optional<Section> sectionData= sectionService.findById(Long.parseLong(id));

        if (sectionData.isPresent()) {
            log.trace("Get Section with ID: {}", id);
            return new ResponseEntity<>(sectionData.get().getEmployees(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/section/1/employee
    @PostMapping("/section/{id}/employee")
    public ResponseEntity<Section> addEmployeeToSection(@PathVariable("id") String id, @RequestBody Employee employee) {
        Optional<Section> sectionData = sectionService.findById(Long.parseLong(id));

        if (sectionData.isPresent()) {
            Section _section = sectionData.get();
            _section.addEmployee(employee);
            employeeService.save(employee);
            log.trace("Get section with ID: {}", id);
            return new ResponseEntity<>(sectionData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
