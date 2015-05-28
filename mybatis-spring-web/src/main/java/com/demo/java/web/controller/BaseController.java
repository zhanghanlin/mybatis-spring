package com.demo.java.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.utils.WebConstants;

public class BaseController {

    static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * set UUID to session.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @since JDK 1.7
     */
    void randomUUID(HttpServletRequest request) {
        request.getSession().setAttribute(WebConstants.uuid, UUID.randomUUID());
    }

    /**
     * 检查UUID是否一致.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    boolean checkUUID(HttpServletRequest request) {
        Object UUIDObj = request.getSession().getAttribute(WebConstants.uuid);
        String requestUUID = "";
        if (UUIDObj != null) {
            requestUUID = UUIDObj.toString();
        }
        String uuid = request.getParameter(WebConstants.uuid);
        logger.debug("checkUUID  uuid > {} , requestUUID > {}", uuid, requestUUID);
        if (StringUtils.isBlank(requestUUID) || StringUtils.isBlank(uuid) || !requestUUID.equals(uuid)) {
            return false;
        }
        return true;
    }
}
