package com.demo.java.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.dao.UserMapper;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int insertSelective(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateByPrimaryKey(user);
    }
}
