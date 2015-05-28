package com.demo.java.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.java.entity.User;
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
    public String signUp(HttpServletRequest request) {
        randomUUID(request); // 重置UUID
        return PathConstants.signUp_page;
    }

    /**
     * 
     * 注册.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("/signUpSubmit")
    public String submit(HttpServletRequest request) {
        boolean checkUUID = checkUUID(request);
        randomUUID(request); // 重置UUID
        if (!checkUUID) {
            return PathConstants.signIn_page;
        }
        User user = new User();
        user.setUserName(request.getParameter("account"));
        user.setPassword(request.getParameter("password"));
        user.setSources(0);
        user.setSex(0);
        user.setStatus(0);
        user.setCreatedAt(new Date());
        userService.insert(user);
        logger.info(user.getId().toString());
        return PathConstants.signIn_page;
    }
}
