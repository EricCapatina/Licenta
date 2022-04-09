package com.licenta.demo.service;

import java.util.List;

import com.licenta.demo.database.entity.Post;
import com.licenta.demo.database.entity.User;
import com.licenta.demo.database.entity.dto.UserDTO;
import com.licenta.demo.database.entity.dto.UserDetailsDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User saveUser(User user) throws Exception;

    List<UserDTO> getAllUsers();

    UserDetailsDTO getUserDetailsById(Long id) throws Exception;

    User getUserByUsername(String username);

    UserDTO getUserById(Long id);

    void deleteUserByUsername(String username);

    void deleteUserByID(Long id);

    User addPostToUser(Post post, long id);

    User updateUserById(User userInfo);

    UserDetailsDTO getUserDetailsByUsername(String username);

    UserDetailsDTO getUserMarkedPostsById(Long id);

}