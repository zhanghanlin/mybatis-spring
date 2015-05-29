package com.demo.java.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.crypto.DESUtils;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.bean.SignTarget;
import com.demo.java.web.utils.MemoryCache;
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
        logger.debug("checkUUID  uuid : {} , requestUUID : {}", uuid, requestUUID);
        if (StringUtils.isBlank(requestUUID) || StringUtils.isBlank(uuid) || !requestUUID.equals(uuid)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 检查Token
     * 
     * @author zhanghanlin
     * @param token
     * @param userName
     * @return
     * @since JDK 1.7
     */
    public SignTarget checkToken(String token, String userName) {
        SignTarget st = MemoryCache.signMaps.get(userName);
        if (st == null) {
            logger.debug("checkToken SignTarget is null!");
            return null;
        }
        String signKey = st.getKey();
        if (StringUtils.isBlank(signKey)) {
            logger.debug("checkToken signKey is null!");
            return null;
        }
        logger.debug("checkToken signKey : {}", signKey);
        String deToken = "";
        try {
            deToken = DESUtils.decrypt(token, signKey);
        } catch (Exception e) {
            logger.error("checkToken decrypt error : {}", e.getMessage(), e);
        }
        if (StringUtils.isNotBlank(deToken)) {
            logger.debug("checkToken deToken : {}", deToken);
            String uName = deToken;
            if (uName.equals(userName) && st.getTime().isAfterNow()) {
                st.setTime(st.getTime().plusHours(2));
                return st;
            }
        } else {
            logger.debug("checkToken deToken is null");
        }
        return null;
    }
}
