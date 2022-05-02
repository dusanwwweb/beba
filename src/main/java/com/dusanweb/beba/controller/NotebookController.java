package com.dusanweb.beba.controller;

import com.dusanweb.beba.dto.NotebookPostsResponse;
import com.dusanweb.beba.model.Notebook;
import com.dusanweb.beba.model.Post;
import com.dusanweb.beba.repository.NotebookRepository;
import com.dusanweb.beba.repository.PostRepository;
import com.dusanweb.beba.service.NotebookServiceImpl;
import com.dusanweb.beba.service.PostServiceImpl;
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
public class NotebookController {

    @Autowired
    private NotebookServiceImpl notebookService;

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private PostRepository postRepository;

    //http://localhost:8080/api/notebook
    @GetMapping("/notebook")
    public ResponseEntity<List<Notebook>> getAllNotebooks() {
        try {
            List<Notebook> notebook = new ArrayList<>();
            notebook.addAll(notebookService.findAll());

            if (notebook.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("200 (OK)");
            return new ResponseEntity<>(notebook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/notebook/1
    @GetMapping("/notebook/{id}")
    public ResponseEntity<Notebook> getNotebookById(@PathVariable("id") String id) {
        Optional<Notebook> notebookData = notebookService.findById(Long.parseLong(id));
        if (notebookData.isPresent()) {
            log.trace("Get notebook by id: {}", id);
            return new ResponseEntity<>(notebookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/notebook
    @PostMapping("/notebook")
    public ResponseEntity<Notebook> createNotebook(@RequestBody Notebook notebook) {
        try {
            Notebook _notebook = notebookService.save(notebook);
            log.trace("Create notebook with id : {}", notebook.getId());
            return new ResponseEntity<>(_notebook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/notebook/1
    @PutMapping("/notebook/{id}")
    public ResponseEntity<Notebook> updateNotebook(@PathVariable("id") String id, @RequestBody Notebook notebook) {
        Optional<Notebook> notebookData = notebookService.findById(Long.parseLong(id));

        if (notebookData.isPresent()) {
            Notebook _notebook = notebookData.get();
            _notebook.setName(notebook.getName());
            _notebook.setCreated(notebook.getCreated());
            //_notebook.setPosts(notebook.getPosts());

            log.trace("Updated notebook with ID: {}", id);
            return new ResponseEntity<>(notebookService.save(_notebook), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/notebook/2
    @DeleteMapping("/notebook/{id}")
    public ResponseEntity<HttpStatus> deleteNotebook(@PathVariable("id") Long id) {
        try {
            notebookService.deleteById(id);
            log.trace("Delete notebook by id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/notebook/posts
    @GetMapping("/notebook/posts")
    public ResponseEntity<List<NotebookPostsResponse>> getAllNotebooksPosts() {
        try {
            List<NotebookPostsResponse> notebook = new ArrayList<>();
            notebook.addAll(notebookService.getPostObservations());

            if (notebook.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("200 (OK)");
            return new ResponseEntity<>(notebook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/notebook/6/post
    @PostMapping("/notebook/{id}/post")
    public ResponseEntity<Notebook> addPostToNotebook(@PathVariable("id") String id, @RequestBody Post post) {
        Optional<Notebook> notebookData = notebookService.findById(Long.parseLong(id));

        if (notebookData.isPresent()) {
            Notebook _notebook = notebookData.get();
            //map the notebook to the post
            post.setNotebook(_notebook);
            //save post to the database
            postService.save(post);
            log.trace("Get notebook with ID: {}", id);
            return new ResponseEntity<>(notebookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/notebook/6/post/9
    @PutMapping("/notebook/{n_id}/post/{p_id}")
    public ResponseEntity<Notebook> removePostFromNotebook(@PathVariable("n_id") String n_id,
                                                           @PathVariable("p_id") String p_id) {
        Optional<Notebook> notebookData = notebookService.findById(Long.parseLong(n_id));
        Post postData = postRepository.getById(Long.parseLong(p_id));

        if (notebookData.isPresent()) {
            Notebook _notebook = notebookData.get();
            _notebook.removePost(postData);
            //notebookService.removePost(postData);

            log.trace("Updated notebook with ID: {}", n_id);
            return new ResponseEntity<>(notebookService.save(_notebook), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/notebook/6/posts
    @GetMapping("/notebook/{id}/posts")
    public ResponseEntity<Set<Post>> getAllPostsFromNotebook(@PathVariable String id) {
        Optional<Notebook> notebookData= notebookService.findById(Long.parseLong(id));

        if (notebookData.isPresent()) {
            log.trace("Get notebook with ID: {}", id);
            return new ResponseEntity<>(notebookData.get().getPosts(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}