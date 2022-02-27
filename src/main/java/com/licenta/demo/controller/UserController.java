package com.licenta.demo.controller;

import com.licenta.demo.database.entity.User;
import com.licenta.demo.service.implementation.UserServiceImpl;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
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

            if (userInDatabase != null) {
                return new ResponseEntity<String>("User by username " + user.getUserName() + " already exists in database",
                        HttpStatus.BAD_REQUEST);
            }
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
