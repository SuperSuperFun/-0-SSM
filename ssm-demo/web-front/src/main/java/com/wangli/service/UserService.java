package com.wangli.service;

import com.wangli.pojo.User;

public interface UserService {
    int addUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();
}
