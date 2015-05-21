package com.demo.java.utils.encry;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.Constants;
import com.demo.java.utils.string.StringUtils;

/**
 * ClassName:MD5Utils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-5-21 上午11:36:59 <br/>
 * 
 * @author zhanghanlin
 * @version
 * @since JDK 1.7
 * @see
 */
public class MD5Utils {

    static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    public static String encode(String str, MD5Type type) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(type.getAlgorithms());
            byte[] byteArray = str.getBytes(Constants.ENCODING);
            byte[] md5Bytes = md5.digest(byteArray);
            return StringUtils.byte2Hex(md5Bytes);
        } catch (Exception e) {
            logger.error("md5Encode Exception : {}", e.getMessage(), e);
            return null;
        }
    }

    public static void main(String[] args) {
        String str = new String("test");
        logger.info("Before:{}", str);
        try {
            logger.info("After:{}", encode(str, MD5Type.MD5));
        } catch (Exception e) {
            logger.error("main Exception :{}", e.getMessage(), e);
        }
    }
}