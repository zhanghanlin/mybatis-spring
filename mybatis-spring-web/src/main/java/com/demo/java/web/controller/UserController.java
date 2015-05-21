/**
 * Project Name:mybatis-spring-web
 * File Name:LoginController.java
 * Package Name:com.demo.java.web.controller
 * Date:2015-5-21下午3:43:53
 *
 */

package com.demo.java.web.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.utils.PagePath;

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
    public String signIn(HttpServletRequest request, String userName, String password, String uuid) {
        String requestUUID = request.getSession().getAttribute("uuid") != null ? request.getSession().getAttribute("uuid").toString() : "";
        setUUID(request); // 重置UUID
        if (StringUtils.isBlank(requestUUID) || StringUtils.isBlank(uuid) || !requestUUID.equals(uuid)) {
            return PagePath.login_page;
        }
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            logger.info("singIn userName/password is null!");
            request.setAttribute("error_info", "请输入用户名/密码");
            return PagePath.login_page;
        }
        User user = userService.vaild(userName, password);
        if (null != user) {
            logger.info("singIn is OK! - {}", JSONObject.toJSONString(user));
            request.setAttribute("userName", user.getUserName());
        } else {
            logger.info("singIn userName/password is error!");
            request.setAttribute("error_info", "用户名/密码错误");
            return PagePath.login_page;
        }
        return PagePath.index_page;
    }

    void setUUID(HttpServletRequest request) {
        request.getSession().setAttribute("uuid", UUID.randomUUID());
    }
}
