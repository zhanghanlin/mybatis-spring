package com.demo.java.service;

import com.demo.java.entity.User;

public interface UserService {
    int deleteById(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectById(Integer id);

    int updateByIdSelective(User user);

    int updateById(User user);
}
