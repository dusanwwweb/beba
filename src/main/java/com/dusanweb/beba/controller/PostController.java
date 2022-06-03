package com.dusanweb.beba.controller;

import com.dusanweb.beba.model.Post;
import com.dusanweb.beba.repository.PostRepository;
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

@Slf4j
@Builder
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    //http://localhost:8080/api/post
    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> post = new ArrayList<>();
            post.addAll(postService.findAll());

            if (post.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.trace("200 (OK)");
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/post/1
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") String id) {
        Optional<Post> postData = postService.findById(Long.parseLong(id));
        if (postData.isPresent()) {
            log.trace("Get post by id: {}", id);
            return new ResponseEntity<>(postData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/post
    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            Post _post = postService.save(post);
            log.trace("Create post with id : {}", post.getId());
            return new ResponseEntity<>(_post, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/api/post/1
    @PutMapping("/post/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") String id, @RequestBody Post post) {
        Optional<Post> postData = postService.findById(Long.parseLong(id));

        if (postData.isPresent()) {
            Post _post = postData.get();
            _post.setActivityType(post.getActivityType());
            _post.setObservation(post.getObservation());
            _post.setStartTime(post.getStartTime());
            _post.setEndTime(post.getEndTime());

            log.trace("Updated post with ID: {}", id);
            return new ResponseEntity<>(postService.save(_post), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //http://localhost:8080/api/post/2
    @DeleteMapping("/post/{id}")
    public ResponseEntity<HttpStatus> deletePostById(@PathVariable("id") Long id) {
        try {
            postService.deleteById(id);
            log.trace("Delete post by id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
