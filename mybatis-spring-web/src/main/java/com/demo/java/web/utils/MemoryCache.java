/**
 * Project Name:mybatis-spring-utils
 * File Name:MemoryCache.java
 * Package Name:com.demo.java.utils.memory
 * Date:2015-5-29上午9:59:16
 *
 */

package com.demo.java.web.utils;

import java.util.HashMap;
import java.util.Map;

import com.demo.java.entity.User;
import com.demo.java.web.bean.SignTarget;

/**
 * ClassName:MemoryCache <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-5-29 上午9:59:16 <br/>
 * 
 * @author zhanghanlin
 * @version
 * @since JDK 1.7
 * @see
 */
public class MemoryCache {

    public static Map<String, SignTarget> signMaps = new HashMap<String, SignTarget>();
    public static Map<String, User> userMaps = new HashMap<String, User>();
}
