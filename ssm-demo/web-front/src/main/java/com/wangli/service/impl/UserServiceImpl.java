package com.wangli.service.impl;

import com.wangli.dao.UserMapper;
import com.wangli.pojo.User;
import com.wangli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        if (user != null) {
            return userMapper.insert(user);
        }
        return 0;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

}
