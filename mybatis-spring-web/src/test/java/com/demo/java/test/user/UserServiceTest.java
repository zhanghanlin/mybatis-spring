package com.demo.java.test.user;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;
import com.demo.java.test.AbstractTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends AbstractTest {

    @Resource
    UserService userService;

    static Integer userId = 10012;

    @Test
    public void testAInsert() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("111111");
        user.setSources(0);
        user.setSex(0);
        user.setStatus(0);
        user.setCreatedAt(new Date());
        userService.insert(user);
        logger.info(user.getId().toString());
    }

    @Test
    public void testBGetd() {
        User user = userService.get(userId);
        logger.info(JSONObject.toJSONString(user));
    }

    @Test
    public void testCVaild() {
        User user = userService.vaild("test", "111111");
        logger.info(JSONObject.toJSONString(user));
    }

    @Test
    public void testDDeleteById() {
        Integer res = userService.deleteById(userId);
        logger.info(res.toString());
    }
}
