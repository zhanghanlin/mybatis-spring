package com.demo.java.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.dao.UserMapper;
import com.demo.java.dict.UserStatus;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;
import com.demo.java.utils.encry.MD5Type;
import com.demo.java.utils.encry.MD5Utils;
import com.demo.java.utils.redis.JedisSupport;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    JedisSupport jedisSupport;
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
            user.setPassword(MD5Utils.encode(user.getPassword(), MD5Type.MD5));
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
        String user = jedisSupport.get(redis_id_prefix + k);
        if (user == null) {
            user = jedisSupport.get(redis_name_prefix + k);
        }
        return null;
    }

    void setByRedis(User user) {
        jedisSupport.set(redis_id_prefix + user.getId(), user.toString());
        jedisSupport.set(redis_name_prefix + user.getUserName(), user.toString());
    }

    void deleteByRedis(User user) {
        jedisSupport.delete(new String[] { redis_id_prefix + user.getId(), redis_id_prefix + user.getUserName() });
    }
}
