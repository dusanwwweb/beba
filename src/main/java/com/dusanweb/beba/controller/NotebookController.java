package com.dusanweb.beba.controller;

import com.dusanweb.beba.model.Notebook;
import com.dusanweb.beba.repository.NotebookRepository;
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
public class NotebookController {

    @Autowired
    private NotebookRepository notebookRepository;

    //http://localhost:8080/api/notebook
    @GetMapping("/notebook")
    public ResponseEntity<List<Notebook>> getAllNotebooks() {
        try {
            List<Notebook> notebook = new ArrayList<>();
            notebook.addAll(notebookRepository.findAll());

            if (notebook.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("200 (OK)");
            return new ResponseEntity<>(notebook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/notebook/2
    @DeleteMapping("/notebook/{id}")
    public ResponseEntity<HttpStatus> deleteNotebook(@PathVariable("id") Long id) {
        try {
            notebookRepository.deleteById(id);
            log.trace("Delete notebook by id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
