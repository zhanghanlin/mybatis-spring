package com.demo.java.dao;

import com.demo.java.entity.User;

public interface UserMapper {
    int deleteById(Integer id);

    Integer insert(User user);

    User get(Integer id);

    int update(User user);

    User getByUserName(String userName);

    int updatePassword(User user);
}