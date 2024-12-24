package com.dp.dao;

import java.util.List;

import com.dp.model.User;

public interface UserDAO {
    int addUser(User user);

    User getUser(String username);

    List<User> getAllUsers();

    int updateUser(String username, String name, String password, String phone, String address, String role);

    int deleteUser(String username);
}
