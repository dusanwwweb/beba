package com.dusanweb.beba.controller;

import com.dusanweb.beba.model.Child;
import com.dusanweb.beba.model.Section;
import com.dusanweb.beba.repository.ChildRepository;
import com.dusanweb.beba.repository.SectionRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Builder
@RestController
@RequestMapping("/api")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ChildRepository childRepository;

    //OK
    //http://localhost:8080/api/section
    @GetMapping("/section")
    public ResponseEntity<List<Section>> getAllSections() {
        try {
            List<Section> sections = new ArrayList<>(sectionRepository.findAll());

            if (sections.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("200 (OK)");

            return new ResponseEntity<>(sections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //OK
    //http://localhost:8080/api/section/1
    @GetMapping("/section/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable("id") String id) {
        Optional<Section> sectionData = sectionRepository.findById(Long.parseLong(id));
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
        Optional<Section> section = sectionRepository.findById(Long.parseLong(s_id));
        Child child = childRepository.getById(Long.parseLong(c_id));

        if (section.isPresent()) {
            Section _section = section.get();
            _section.addChild(child);
            final Section updatedSection = sectionRepository.save(_section);
            return new ResponseEntity<>(updatedSection, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
