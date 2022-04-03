package com.licenta.demo.controller;

import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.Role;
import com.licenta.demo.database.entity.User;
import com.licenta.demo.database.entity.dto.UserDTO;
import com.licenta.demo.service.implementation.PostServiceImpl;
import com.licenta.demo.service.implementation.UserServiceImpl;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserServiceImpl userService;
    private final PostServiceImpl postService;

    public UserController(UserServiceImpl userService, PostServiceImpl postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Server doesn't respond. Database error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/users/userName/{userName}")
    public ResponseEntity<?> getByUsername(@PathVariable("userName") String userName) {
        try {
            return new ResponseEntity<User>(userService.getUserByUsername(userName), HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>("User by username " + userName + "  not found", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (HibernateException e) {
            return new ResponseEntity<>("User can't be found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/users/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (HibernateException ex) {
            return new ResponseEntity<>("User by ID " + id + "  not found in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/update")
    public ResponseEntity<?> updateUserInformation(@RequestBody User user) {
        try {
            User userInDatabase = userService.updateUserById(user);
            return new ResponseEntity<>(userInDatabase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User userInDatabase = userService.getUserByUsername(user.getUserName());

            if (Objects.nonNull(userInDatabase)) {
                return new ResponseEntity<String>("User by username " + user.getUserName() + " already exists in database",
                        HttpStatus.BAD_REQUEST);
            }
            user.setRole(new Role(1L));
            userService.saveUser(user);
            return new ResponseEntity<User>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Cannot create new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/users/delete/id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        try {
            userService.deleteUserByID(id);
            return new ResponseEntity<String>("User was deleted successfully", HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<String>("User by id " + id + "  not found", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (HibernateException e) {
            return new ResponseEntity<String>("User can't be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/addPost/{userName}")
    public ResponseEntity<?> addPostToUser(@PathVariable("userName") String userName, @RequestBody Post post) {
        try {
            Post markedPost = postService.getPostById(post.getId());
            if(Objects.isNull(markedPost)) System.out.println("Post doesn't exists, creating a new one...");
            User user = userService.getUserByUsername(userName);
            post.setUser(user);
            if(!post.getAuthorUserName().equalsIgnoreCase(post.getAuthor()))
                throw new IllegalArgumentException("No user exists in database with such Author Name");
            postService.createPost(post);
            Long id = user.getId();
            userService.addPostToUser(post, id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cannot mark post for user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/users/delete/{username}")
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable("username") String username) {
        try {
            userService.deleteUserByUsername(username);
            return new ResponseEntity<String>("User was deleted successfully", HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<String>("User by username " + username + "  not found", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (HibernateException e) {
            return new ResponseEntity<String>("User can't be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
