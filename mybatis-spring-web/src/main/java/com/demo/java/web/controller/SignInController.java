package com.demo.java.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.cookie.CookieConstants;
import com.demo.java.web.cookie.CookieUtils;
import com.demo.java.web.utils.PathConstants;

@Controller
public class SignInController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(SignInController.class);

    @Resource
    UserService userService;

    @RequestMapping("/index")
    public String index() {
        return PathConstants.index_page;
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
    public String signIn(HttpServletRequest request, HttpServletResponse response, String userName, String password, String uuid) {
        if (!checkUUID(request)) {
            return PathConstants.login_page;
        }
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            logger.debug("singIn userName/password is null!");
            request.setAttribute("error_info", "请输入用户名/密码");
            return PathConstants.login_page;
        }
        User user = userService.vaild(userName, password);
        if (null == user) {
            logger.debug("singIn userName/password is error!");
            request.setAttribute("error_info", "用户名/密码错误");
            return PathConstants.login_page;
        }
        logger.debug("singIn is OK! - {}", JSONObject.toJSONString(user));
        request.getSession().setAttribute("userName", user.getUserName());
        CookieUtils.addCookie(request, response, CookieConstants.USERNAME, user.getUserName());
        return "redirect:/index";
    }
}
