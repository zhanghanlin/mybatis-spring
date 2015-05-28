/**
 * Project Name:mybatis-spring-web
 * File Name:SignTarget.java
 * Package Name:com.demo.java.web.bean
 * Date:2015-5-27下午2:39:35
 *
 */

package com.demo.java.web.bean;

import java.util.Date;

/**
 * ClassName:SignTarget <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-5-27 下午2:39:35 <br/>
 * 
 * @author zhanghanlin
 * @version
 * @since JDK 1.7
 * @see
 */
public class SignTarget {

    private String uid;
    private String uName;
    private Date time;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}