package com.licenta.demo.dao;

import com.licenta.demo.database.entity.User;

public interface UserDAO {

    User getUserByUsername(String username);
}
