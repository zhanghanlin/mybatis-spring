/**
 * Project Name:mybatis-spring-web
 * File Name:LoginController.java
 * Package Name:com.demo.java.web.controller
 * Date:2015-5-21下午3:43:53
 *
 */

package com.demo.java.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;

/**
 * ClassName:LoginController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-5-21 下午3:43:53 <br/>
 * 
 * @author zhanghanlin
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
public class UserController {

    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        return "/view/jsp/login";
    }

    /**
     * 
     * 登陆.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("/signIn")
    public String signIn(HttpServletRequest request, String userName, String password) {
        User user = userService.vaild(userName, password);
        if (null != user) {
            logger.info("singIn is OK! - {}", JSONObject.toJSONString(user));
            request.setAttribute("userName", user.getUserName());
        }
        logger.info("singIn is error!");
        return "/index";
    }
}
