package com.demo.java.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.entity.User;

public interface UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    static final String redis_id_prefix = "USER_I_";
    static final String redis_name_prefix = "USER_N_";

    /**
     * 
     * 根据用户Id删除用户. <br/>
     * 
     * @author zhanghanlin
     * @param id
     * @return
     * @since JDK 1.7
     */
    int deleteById(Integer id);

    /**
     * 
     * 根据用户Id冻结用户. <br/>
     * 
     * @author zhanghanlin
     * @param id
     * @return
     * @since JDK 1.7
     */
    int freezeById(Integer id);

    /**
     * 更新用户状态.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param status
     * @return
     * @since JDK 1.7
     */
    int updateStatus(Integer id, Integer status);

    /**
     * 
     * 插入用户. <br/>
     * 
     * @author zhanghanlin
     * @param user
     * @return
     * @since JDK 1.7
     */
    Integer insert(User user);

    /**
     * 
     * 根据用户Id查询用户. <br/>
     * 
     * @author zhanghanlin
     * @param id
     * @return
     * @since JDK 1.7
     */
    User get(Integer id);

    /**
     * 
     * 更新用户. <br/>
     * 
     * @author zhanghanlin
     * @param user
     * @return
     * @since JDK 1.7
     */
    int update(User user);

    /**
     * 
     * 根据用户名密码验证用户. <br/>
     * 
     * @author zhanghanlin
     * @param userName
     * @param password
     * @return
     * @since JDK 1.7
     */
    User vaild(String userName, String password);

    /**
     * 
     * 根据用户名查询用户信息
     * 
     * @author zhanghanlin
     * @param userName
     * @since JDK 1.7
     */
    User getByUserName(String userName);

    /**
     * 更新用户密码.<br/>
     * 
     * @author zhanghanlin
     * @param userName
     * @param oldPwd
     * @param newPwd
     * @return
     * @since JDK 1.7
     */
    int updatePassword(String userName, String oldPwd, String newPwd);
}
