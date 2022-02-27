package com.licenta.demo;

import com.licenta.demo.dao.implementation.UserDAOImpl;
import com.licenta.demo.database.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDAOTests {

    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private UserDAOImpl userDAO;

    @BeforeEach
    public void setUp() {
        user1 = new User("Eric", "Capatina", "krab", "1234");
        user2 = new User("Eric1", "Capatina1", "krab1", "1234");
        user3 = new User("Eric12", "Capatina12", "krab12", "1234");
    }

    @AfterEach
    public void deleteAllUsers() {
        userDAO.remove(user1);
        userDAO.remove(user2);
        userDAO.remove(user3);
        user1 = null;
        user2 = null;
        user3 = null;
    }

    @Test
    public void addNewUserTest(){
        userDAO.add(user1);
        User fetchedUser = userDAO.getById(user1.getId());
        assertEquals(user1.getId(), fetchedUser.getId());
    }

    @Test
    public void getUserList() {
        userDAO.add(user2);
        userDAO.add(user3);
        List<User> actualList = userDAO.getAll();
        assertEquals(2, actualList.size());
    }

}
