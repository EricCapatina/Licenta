package com.licenta.demo.controller;

import java.util.List;

import com.licenta.demo.database.entity.Role;
import com.licenta.demo.database.entity.User;
import com.licenta.demo.service.implementation.RoleServiceImpl;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private final RoleServiceImpl roleService;

    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles/update/{userId}")
    public ResponseEntity<?> changeRole(@PathVariable("userId") Long id, @RequestBody Role role) {
        try {
            User user = roleService.updateRole(id, role);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Cannot update user role", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        try {
            List<Role> roles = roleService.getAllRoles();
            if (roles == null) {
                return new ResponseEntity<>(roles, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Cannot get information about roles from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles/name/{name}")
    public ResponseEntity<?> getRolesByName(@PathVariable("name") String name) {
        try {
            Role role = roleService.getRoleByName(name);
            if (role == null) {
                return new ResponseEntity<>(role, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Cannot get information about roles from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles/id/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable("id") Long id) {
        try {
            Role role = roleService.getRoleById(id);
            if (role == null) {
                return new ResponseEntity<>(role, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>("Cannot get information about roles from database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
