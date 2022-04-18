package com.dusanweb.beba.controller;

import com.dusanweb.beba.model.Section;
import com.dusanweb.beba.repository.SectionRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@RestController
@RequestMapping("/api")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

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


/*
    @GetMapping("/section")
    public List<Section> getAllSectionsssss() {
        return sectionRepository.findAll();
    }

 */

}
