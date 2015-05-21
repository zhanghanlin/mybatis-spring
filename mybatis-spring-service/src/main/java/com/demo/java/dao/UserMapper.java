package com.demo.java.dao;

import com.demo.java.entity.User;

public interface UserMapper {
    int deleteById(Integer id);

    Integer insert(User user);

    User selectById(Integer id);

    int updateById(User user);

    User selectByUserName(String userName);

    int updatePassword(User user);
}