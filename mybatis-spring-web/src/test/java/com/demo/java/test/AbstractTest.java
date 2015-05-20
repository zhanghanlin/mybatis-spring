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

    protected static final Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    @Test
    public void testInit() {
        logger.info("test init");
    }
}
