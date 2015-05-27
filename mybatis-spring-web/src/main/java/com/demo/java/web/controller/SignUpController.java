package com.demo.java.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.java.service.UserService;
import com.demo.java.web.utils.PathConstants;

@Controller
public class SignUpController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Resource
    UserService userService;

    /**
     * 
     * 注册.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("/signUp")
    public String signUp(HttpServletRequest request, String userName, String password, String uuid) {
        return PathConstants.index_page;
    }
}
