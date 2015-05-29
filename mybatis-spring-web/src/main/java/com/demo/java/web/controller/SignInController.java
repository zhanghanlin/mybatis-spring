package com.demo.java.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.entity.User;
import com.demo.java.service.UserService;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.bean.SignTarget;
import com.demo.java.web.cookie.CookieConstants;
import com.demo.java.web.cookie.CookieUtils;
import com.demo.java.web.utils.MemoryCache;
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

    @RequestMapping("/signIn")
    public String signIn(HttpServletRequest request) {
        randomUUID(request); // 重置UUID
        return PathConstants.signIn_page;
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
    @RequestMapping("/signInSubmit")
    public String submit(HttpServletRequest request, HttpServletResponse response, String userName, String password, String uuid) {
        boolean checkUUID = checkUUID(request);
        randomUUID(request); // 重置UUID
        if (!checkUUID) {
            return PathConstants.signUp_page;
        }
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            logger.debug("singIn userName/password is null!");
            request.setAttribute("error_info", "请输入用户名/密码");
            return PathConstants.signIn_page;
        }
        User user = userService.vaild(userName, password);
        if (null == user) {
            logger.debug("singIn userName/password is error!");
            request.setAttribute("error_info", "用户名/密码错误");
            return PathConstants.signIn_page;
        }
        logger.debug("singIn is OK! - {}", JSONObject.toJSONString(user));
        SignTarget st = new SignTarget(userName);
        logger.debug("singIn SignTarget - {}", st.toString());
        isSignIn(request, response, st, user);
        return "redirect:/index";
    }

    @RequestMapping("/token2Cookie")
    @ResponseBody
    public Map<String, Object> checkToken2Cookie(HttpServletRequest request, HttpServletResponse response, String userName) {
        Map<String, Object> map = new HashMap<String, Object>();
        String token = CookieUtils.getCookie(request, CookieConstants.TOKEN);
        SignTarget st = checkToken(token, userName);
        User user = MemoryCache.userMaps.get(userName);
        map.put("valid", false);
        map.put("user", null);
        if ((st != null) && (user != null)) {
            map.put("valid", true);
            map.put("user", user);
            isSignIn(request, response, st, user);
        }
        return map;
    }

    @RequestMapping("/token2Request")
    @ResponseBody
    public Map<String, Object> checkToken2Request(HttpServletRequest request, HttpServletResponse response, String userName, String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        SignTarget st = checkToken(token, userName);
        User user = MemoryCache.userMaps.get(userName);
        map.put("valid", false);
        map.put("user", null);
        if ((st != null) && (user != null)) {
            map.put("valid", true);
            map.put("user", user);
            isSignIn(request, response, st, user);
        }
        return map;
    }

    void isSignIn(HttpServletRequest request, HttpServletResponse response, SignTarget st, User user) {
        request.getSession().setAttribute("userName", user.getUserName());
        request.getSession().setAttribute("token", st.getToken());
        CookieUtils.addCookie(request, response, CookieConstants.TOKEN, st.getToken());
        CookieUtils.addCookie(request, response, CookieConstants.USERNAME, user.getUserName());
        MemoryCache.signMaps.put(user.getUserName(), st);
        MemoryCache.userMaps.put(user.getUserName(), user);
    }
}
