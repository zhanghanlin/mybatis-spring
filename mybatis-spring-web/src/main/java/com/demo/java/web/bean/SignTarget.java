package com.demo.java.web.bean;

import org.joda.time.DateTime;

import com.demo.java.utils.string.StringUtils;

public class SignTarget {

    private final String name;
    private final String key;
    private final DateTime time;

    public SignTarget(String name) {
        this.key = StringUtils.getRandomString(8);
        this.name = name;
        this.time = DateTime.now();
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
}