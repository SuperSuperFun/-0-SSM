package com.wangli.dao;

import com.wangli.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User getUserById(int id);
}