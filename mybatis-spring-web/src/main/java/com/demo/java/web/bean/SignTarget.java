package com.demo.java.web.bean;

import org.joda.time.DateTime;

import com.demo.java.utils.crypto.DESUtils;
import com.demo.java.utils.string.StringUtils;

public class SignTarget {

    private final String name;
    private final String key;
    private final DateTime time;
    private String token;

    public SignTarget(String name) {
        this.key = StringUtils.getRandomString(8);
        this.name = name;
        this.time = DateTime.now();
        try {
            this.token = DESUtils.encrypt(name + "@@" + time.toString(), key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public DateTime getTime() {
        return time;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "SignTarget [name=" + name + ", key=" + key + ", time=" + time + "]";
    }
}