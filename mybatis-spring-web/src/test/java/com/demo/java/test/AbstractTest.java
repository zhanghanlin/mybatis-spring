package com.demo.java.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration({ "classpath:spring/applicationContext.xml" })
public class AbstractTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void init() {
        logger.info("test");
    }
}