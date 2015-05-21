package com.demo.java.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.dao.UserMapper;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;
import com.demo.java.utils.encry.MD5Type;
import com.demo.java.utils.encry.MD5Utils;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Integer insert(User user) {
        String pwd = user.getPassword();
        try {
            user.setPassword(MD5Utils.encode(pwd, MD5Type.MD5));
        } catch (Exception e) {
            logger.error("insert MD5Utils error : {}", e.getMessage(), e);
        }
        return userMapper.insert(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User vaild(String userName, String password) {
        User user = userMapper.selectByUserName(userName);
        if (null != user) {
            String userPwd = user.getPassword();
            String inputPwd = "";
            try {
                inputPwd = MD5Utils.encode(password, MD5Type.MD5);
            } catch (Exception e) {
                logger.error("vaild MD5Utils error : {}", e.getMessage(), e);
            }
            if (userPwd.equals(inputPwd)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public int updatePassword(String userName, String oldPwd, String newPwd) {
        int res = 0;
        User user = vaild(userName, oldPwd);
        if (null != user) {
            try {
                user.setPassword(MD5Utils.encode(newPwd, MD5Type.MD5));
                res = userMapper.updatePassword(user);
            } catch (Exception e) {
                logger.error("updatePassword MD5Utils error : {}", e.getMessage(), e);
            }
        }
        return res;
    }
}
