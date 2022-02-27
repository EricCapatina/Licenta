package com.licenta.demo.service.implementation;

import com.licenta.demo.converter.UserEntityToUserDTO;
import com.licenta.demo.dao.implementation.UserDAOImpl;
import com.licenta.demo.database.entity.SecurityUser;
import com.licenta.demo.database.entity.User;
import com.licenta.demo.database.entity.dto.UserDTO;
import com.licenta.demo.service.UserService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
//    final static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserDAOImpl userDAO;
    private final UserEntityToUserDTO converter;

    @Autowired
    public UserServiceImpl(UserDAOImpl userDAO, UserEntityToUserDTO converter) {
        this.userDAO = userDAO;
        this.converter = converter;
    }


    @Override
    public UserDTO getUserById(Long id) {
        return converter.userModelToUserDto(userDAO.getById(id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public List<UserDTO> getAllUsers() throws HibernateException {
        List<UserDTO> users = converter.userModelToUserDto(userDAO.getAll());
        if (users == null) throw new HibernateException("Users == null");
        return users;
    }

    @Override
    public User saveUser(User user) throws Exception {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(user.getPassword()));
        return userDAO.add(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User by username " + username + " not found");
        return SecurityUser.fromUser(user);
    }

    @Override
    public User updateUserById(User userInfo) {
        User user = userDAO.getById(userInfo.getId());
        if (user == null) throw new UsernameNotFoundException("User not found");
        if (!userInfo.getUserName().isEmpty())
            user.setUserName(userInfo.getUserName());
        if (!userInfo.getFirstName().isEmpty())
            user.setFirstName(userInfo.getFirstName());
        if (!userInfo.getLastName().isEmpty())
            user.setLastName(userInfo.getLastName());
        return userDAO.update(user);
    }

    @Override
    public void deleteUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        userDAO.remove(user);
    }

    @Override
    public void deleteUserByID(Long id) {
        userDAO.removeById(id);
    }
}
