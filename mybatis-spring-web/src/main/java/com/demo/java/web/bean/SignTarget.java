package com.demo.java.web.bean;

import org.joda.time.DateTime;

import com.demo.java.utils.crypto.DESUtils;
import com.demo.java.utils.string.StringUtils;

public class SignTarget {

    private final String name;
    private final String key;
    private final String token;
    private DateTime time;

    public SignTarget(String name) {
        this.key = StringUtils.getRandomString(8);
        this.name = name;
        this.token = DESUtils.encrypt(name, key);
        this.time = DateTime.now().plusHours(2);
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getToken() {
        return token;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public DateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "SignTarget [name=" + name + ", key=" + key + "]";
    }
}