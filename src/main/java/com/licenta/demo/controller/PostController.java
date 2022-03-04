package com.licenta.demo.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.licenta.demo.database.entity.Post;
import com.licenta.demo.service.implementation.PostServiceImpl;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        try {
            return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Cannot receive list of posts from server", HttpStatus.OK);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        try {
            post.setTimePlaced(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
            return new ResponseEntity<>(postService.createPost(post), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Cannot create new post in database", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/posts/delete/id/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<String> deletePostkById(@PathVariable("id") Long id) {
        try {
            postService.deletePostByID(id);
            return new ResponseEntity<>("post was deleted successfully", HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("post can't be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/posts/id/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Cannot create new post in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/posts/userName")
    public ResponseEntity<?> getUserPosts(String userName) {
        try {
            List<Post> userposts = postService.getUserPosts(userName);
            if (userposts == null) {
                return new ResponseEntity<>("User don't have any posts to work on", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userposts, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Cannot get information about user posts from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}