package com.demo.java.utils.crypto;

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
public class DigestUtils {

    public final class Algorithm {
        public static final String MD5 = "MD5";
        public static final String SHA = "SHA";
        public static final String SHA1 = "SHA-1";
        public static final String SHA256 = "SHA-256";
        public static final String SHA384 = "SHA-384";
        public static final String SHA512 = "SHA-512";
    }

    static Logger logger = LoggerFactory.getLogger(DigestUtils.class);

    public static String encode(String str, String algorithm) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(algorithm);
            byte[] byteArray = str.getBytes(Constants.ENCODING);
            byte[] md5Bytes = md5.digest(byteArray);
            return StringUtils.byte2Hex(md5Bytes);
        } catch (Exception e) {
            logger.error("encode Exception : {}", e.getMessage(), e);
            return null;
        }
    }

    public static String md5(String str) {
        return encode(str, Algorithm.MD5);
    }

    public static String sha(String str) {
        return encode(str, Algorithm.SHA);
    }

    public static String sha1(String str) {
        return encode(str, Algorithm.SHA1);
    }

    public static String sha256(String str) {
        return encode(str, Algorithm.SHA256);
    }

    public static String sha384(String str) {
        return encode(str, Algorithm.SHA384);
    }

    public static String sha512(String str) {
        return encode(str, Algorithm.SHA512);
    }
}