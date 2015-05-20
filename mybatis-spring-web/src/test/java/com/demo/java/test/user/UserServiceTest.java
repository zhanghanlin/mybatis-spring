package com.demo.java.test.user;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;
import com.demo.java.test.AbstractTest;

public class UserServiceTest extends AbstractTest {

    @Resource
    UserService userService;

    @Test
    public void testSelectById() {
        User user = userService.selectById(10000);
        logger.info(JSONObject.toJSONString(user));
    }

    @Test
    public void testInsertSelective() {
        User user = new User();
        user.setUserName("test4");
        user.setPassword("11111");
        user.setSources(0);
        user.setSex(0);
        user.setStatus(0);
        user.setCreatedAt(new Date());
        Integer res = userService.insertSelective(user);
        logger.info(res.toString());
    }

    @Test
    public void testDeleteById() {
        Integer res = userService.deleteById(10002);
        logger.info(res.toString());
    }
}
