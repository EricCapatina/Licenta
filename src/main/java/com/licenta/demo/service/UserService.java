package com.licenta.demo.service;

import java.util.List;

import com.licenta.demo.database.entity.User;
import com.licenta.demo.database.entity.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User saveUser(User user) throws Exception;

    List<UserDTO> getAllUsers();

    User getUserByUsername(String username);

    UserDTO getUserById(Long id);

    void deleteUserByUsername(String username);

    void deleteUserByID(Long id);

    User updateUserById(User userInfo);

}