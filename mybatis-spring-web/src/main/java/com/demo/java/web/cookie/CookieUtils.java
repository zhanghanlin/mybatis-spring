package com.demo.java.web.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.Constants;

public class CookieUtils {

    static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        addCookie(request, response, name, value, CookieConstants.MAX_AGE);
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int expiry) {
        try {
            value = URLEncoder.encode(value, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.error("setCookie URLEncoder Error : {}", e.getMessage(), e);
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expiry);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        addCookie(request, response, name, null, 0);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), Constants.ENCODING);
                    } catch (UnsupportedEncodingException e) {
                        logger.error("getCookie URLDecoder Error : {}", e.getMessage(), e);
                    }
                }
            }
        }
        return null;
    }
}
