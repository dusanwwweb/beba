package com.dusanweb.beba.controller;

import com.dusanweb.beba.enumeration.AllergyType;
import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.model.Section;
import com.dusanweb.beba.repository.ChildRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@Slf4j
@RestController
@RequestMapping("/api")
public class ChildController {

    @Autowired
    ChildRepository childRepository;

    @GetMapping("/children")
    public ResponseEntity<List<Child>> getAllChildren() {
        try {
            List<Child> children = new ArrayList<>();
            children.addAll(childRepository.findAll());

            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/children/{id}")
    public ResponseEntity<Child> getChildById(@PathVariable("id") long id) {
        Optional<Child> childData = childRepository.findById(id);
        if (childData.isPresent()) {
            return new ResponseEntity<>(childData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/children")
    public ResponseEntity<Child> createChild(@RequestBody Child child) {
        try {
            Child _child = childRepository.save(Child.builder()
                    .firstName(child.getFirstName())
                    .lastName(child.getLastName())
                    .address(child.getAddress())
                    .city(child.getCity())
                    .dateOfBirth(child.getDateOfBirth())
                    .weight(child.getWeight())
                    .allergyType(child.getAllergyType())
                    //.profilePhoto()
                    .notebook(child.getNotebook())
                    .section(child.getSection())
                    .build());
            return new ResponseEntity<>(_child, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @PutMapping("/children/{id}")
    public ResponseEntity<Child> updateChild(@PathVariable("id") long id, @RequestBody Child child) {
        Optional<Child> childData = childRepository.findById(id);
        if (childData.isPresent()) {
            Child _child = childData.get();
            _child.setTitle(child.getTitle());
            _child.setDescription(child.getDescription());
            _child.setPublished(child.isPublished());
            return new ResponseEntity<>(childRepository.save(_child), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     */

    @DeleteMapping("/children/{id}")
    public ResponseEntity<HttpStatus> deleteChild(@PathVariable("id") long id) {
        try {
            childRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/children")
    public ResponseEntity<HttpStatus> deleteAllChildren() {
        try {
            childRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/children/{dob}")
    public ResponseEntity<List<Child>> findByDateOfBirth(@PathVariable("dob") LocalDate dob) {
        try {
            List<Child> children = childRepository.findByDateOfBirthOrderByDateOfBirthAsc(dob);
            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/children/{section}")
    public ResponseEntity<List<Child>> findBySection(@PathVariable("section") Section name) {
        try {
            List<Child> children = childRepository.findBySection(name);
            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
