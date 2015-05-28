package com.demo.java.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.dao.UserMapper;
import com.demo.java.dict.UserStatus;
import com.demo.java.entity.User;
import com.demo.java.redis.JedisUtils;
import com.demo.java.service.UserService;
import com.demo.java.utils.crypto.DigestUtils;
import com.demo.java.utils.string.StringUtils;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    JedisUtils jedisUtils;
    @Resource
    UserMapper userMapper;

    @Override
    public int deleteById(Integer id) {
        return updateStatus(id, UserStatus.DELETE);
    }

    @Override
    public int freezeById(Integer id) {
        return updateStatus(id, UserStatus.FREEZE);
    }

    @Override
    public int updateStatus(Integer id, Integer status) {
        int res = 0;
        User user = get(id);
        if (user != null) {
            user.setStatus(UserStatus.FREEZE);
            res = update(user);
            if (res > 0) {
                setByRedis(user);
            }
        }
        return res;
    }

    @Override
    public Integer insert(User user) {
        int res = 0;
        try {
            user.setPassword(DigestUtils.md5(user.getPassword()));
            res = userMapper.insert(user);
            if (res > 0) {
                setByRedis(user);
            }
        } catch (Exception e) {
            logger.error("insert MD5Utils error : {}", e.getMessage(), e);
        }
        return res;
    }

    @Override
    public User get(Integer id) {
        User user = getByRedis(id.toString());
        if (user == null) {
            user = userMapper.get(id);
            if (user != null) {
                setByRedis(user);
            }
        }
        return user;
    }

    @Override
    public int update(User user) {
        int res = userMapper.update(user);
        if (res > 0) {
            setByRedis(user);
        }
        return res;
    }

    @Override
    public User vaild(String userName, String password) {
        User user = getByUserName(userName);
        if (null != user) {
            String userPwd = user.getPassword();
            String inputPwd = "";
            try {
                inputPwd = DigestUtils.md5(password);
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
                user.setPassword(DigestUtils.md5(newPwd));
                res = userMapper.updatePassword(user);
                if (res > 0) {
                    setByRedis(user);
                }
            } catch (Exception e) {
                logger.error("updatePassword MD5Utils error : {}", e.getMessage(), e);
            }
        }
        return res;
    }

    @Override
    public User getByUserName(String userName) {
        User user = getByRedis(userName);
        if (user == null) {
            user = userMapper.getByUserName(userName);
            if (user != null) {
                setByRedis(user);
            }
        }
        return user;
    }

    User getByRedis(String k) {
        User user = null;
        String user_str = jedisUtils.get(redis_id_prefix + k);
        if (StringUtils.isBlank(user_str)) {
            user_str = jedisUtils.get(redis_name_prefix + k);
        }
        if (StringUtils.isNotBlank(user_str)) {
            user = JSONObject.toJavaObject(JSONObject.parseObject(user_str), User.class);
        }
        return user;
    }

    void setByRedis(User user) {
        jedisUtils.set(redis_id_prefix + user.getId(), user.toString());
        jedisUtils.set(redis_name_prefix + user.getUserName(), user.toString());
    }

    void deleteByRedis(User user) {
        jedisUtils.delete(new String[] { redis_id_prefix + user.getId(), redis_id_prefix + user.getUserName() });
    }
}
