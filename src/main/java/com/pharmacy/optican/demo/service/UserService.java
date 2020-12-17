package com.pharmacy.optican.demo.service;

import com.pharmacy.optican.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User findUserByEmail(String email);

    User findUserByPhone(String phone);

    void updateUser(User user);
}
