package com.demo.java.service;

import com.demo.java.entity.User;

public interface UserService {

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
     * 插入用户. <br/>
     * 不验证字段非空.<br/>
     * 
     * @author zhanghanlin
     * @param user
     * @return
     * @since JDK 1.7
     */
    int insert(User user);

    /**
     * 
     * 插入用户. <br/>
     * 验证字段非空.<br/>
     * 
     * @author zhanghanlin
     * @param user
     * @return
     * @since JDK 1.7
     */
    int insertSelective(User user);

    /**
     * 
     * 根据用户Id查询用户. <br/>
     * 
     * @author zhanghanlin
     * @param id
     * @return
     * @since JDK 1.7
     */
    User selectById(Integer id);

    /**
     * 
     * 更新用户. <br/>
     * 验证字段非空.<br/>
     * 
     * @author zhanghanlin
     * @param user
     * @return
     * @since JDK 1.7
     */
    int updateByIdSelective(User user);

    /**
     * 
     * 更新用户. <br/>
     * 不验证字段非空.<br/>
     * 
     * @author zhanghanlin
     * @param user
     * @return
     * @since JDK 1.7
     */
    int updateById(User user);

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
}
