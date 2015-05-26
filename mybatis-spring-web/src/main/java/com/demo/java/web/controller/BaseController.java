/**
 * Project Name:mybatis-spring-web
 * File Name:BaseController.java
 * Package Name:com.demo.java.web.controller
 * Date:2015-5-26下午1:53:56
 *
 */

package com.demo.java.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:BaseController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-5-26 下午1:53:56 <br/>
 * 
 * @author zhanghanlin
 * @version
 * @since JDK 1.7
 * @see
 */
public class BaseController {

    /**
     * set UUID to session.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @since JDK 1.7
     */
    void randomUUID(HttpServletRequest request) {
        request.getSession().setAttribute("uuid", UUID.randomUUID());
    }
}
