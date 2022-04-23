package com.dusanweb.beba.controller;

import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.model.Notebook;
import com.dusanweb.beba.repository.ChildRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
// the annotation enables Cross-Origin Resource Sharing (CORS) on the server.
//This step isn't always necessary, but since we're deploying our Angular frontend to http://localhost:4200,
// and our Boot backend to http://localhost:8080, the browser would otherwise deny requests from one to the other.
@Slf4j
@RestController
@Builder
@RequestMapping("/api")
public class ChildController {

    @Autowired
    ChildRepository childRepository;

    //OK
    //http://localhost:8080/api/children
    @GetMapping("/children")
    public ResponseEntity<List<Child>> getAllChildren() {
        try {
            List<Child> children = new ArrayList<>();
            children.addAll(childRepository.findAll());

            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("200 (OK)");
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //OK
    //http://localhost:8080/api/children/1
    @GetMapping("/children/{id}")
    public ResponseEntity<Child> getChildById(@PathVariable("id") String id) {
        Optional<Child> childData = childRepository.findById(Long.parseLong(id));
        if (childData.isPresent()) {
            log.trace("Get child by id: {}", id);
            return new ResponseEntity<>(childData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //OK
    //http://localhost:8080/api/children
    @PostMapping("/children")
    public ResponseEntity<Child> createChild(@RequestBody Child child) {
        try {
            Child _child = childRepository.save(child);
            log.trace("Create child with id : {}", child.getId());
            return new ResponseEntity<>(_child, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //OK
    //http://localhost:8080/api/children/7
    @PutMapping("/children/{id}")
    public ResponseEntity<Child> updateChild(@PathVariable("id") long id, @RequestBody Child child) {
        Optional<Child> childData = childRepository.findById(id);

        if (childData.isPresent()) {
            Child _child = childData.get();
            _child.setFirstName(child.getFirstName());
            _child.setLastName(child.getLastName());
            _child.setAddress(child.getAddress());
            _child.setCity(child.getCity());
            _child.setDateOfBirth(child.getDateOfBirth());
            _child.setWeight(child.getWeight());
            _child.setSex(child.getSex());
            _child.setAllergyType(child.getAllergyType());
            _child.setProfilePhoto(child.getProfilePhoto());
            _child.setNotebook(child.getNotebook());
            _child.setParents(child.getParents());

            log.trace("Updated child with ID: {}", id);
            return new ResponseEntity<>(childRepository.save(_child), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //OK
    //http://localhost:8080/api/children/2
    @DeleteMapping("/children/{id}")
    public ResponseEntity<HttpStatus> deleteChild(@PathVariable("id") long id) {
        try {
            childRepository.deleteById(id);
            log.trace("Delete child by id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //OK
    //http://localhost:8080/api/children
    @DeleteMapping("/children")
    public ResponseEntity<HttpStatus> deleteAllChildren() {
        try {
            childRepository.deleteAll();
            log.trace("Delete all children");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //http://localhost:8080/api/children/find/2021-05-09
    @GetMapping("/children/find/{dob}")
    public ResponseEntity<List<Child>> findByDateOfBirth(@PathVariable("dob") String dob) {
        try {
            List<Child> children = childRepository.findByDateOfBirth(LocalDate.parse(dob));
            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("Get child by date of birth: {}", dob);
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/children/name/
    @GetMapping("/children/name/{name}")
    public ResponseEntity<Child> getChildByFirstName(@PathVariable("name") String firstName) {
        Optional<Child> childData = childRepository.findByFirstName(firstName);
        if (childData.isPresent()) {
            log.trace("Get child by first name: {}", firstName);
            return new ResponseEntity<>(childData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/children/weight
    @GetMapping("/children/weight")
    public ResponseEntity<List<Child>> findByWeight() {
        try {
            List<Child> children = childRepository.findAllOrderByWeightDesc();
            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("Get all children by weight");
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/children/allergy
    @GetMapping("/children/allergy")
    public ResponseEntity<List<Child>> findByAllergy() {
        try {
            List<Child> children = childRepository.findAllOrderByAllergyTypeAsc();
            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("Get all children by weight");
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/children/sex/f
    @GetMapping("children/sex/{sex}")
    public ResponseEntity<List<Child>> findChildBySex(@PathVariable(value = "sex") char sex) {
        try {
            List<Child> children = childRepository.findBySex(sex);
            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("Get child by sex: {}", sex);
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    //http://localhost:8080/api/children/dateofbirth
    @GetMapping("/children/dateofbirth")
    public ResponseEntity<List<Child>> findByDateOfBirthAscending() {
        try {
            List<Child> children = childRepository.findAllOrderByDateOfBirthAsc();
            if (children.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("Get all children by weight");
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 */


}
