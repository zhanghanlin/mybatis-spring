package com.demo.java.utils.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.string.StringUtils;

public class DESUtils {

    static final Logger logger = LoggerFactory.getLogger(DESUtils.class);
    /** 加密算法,可用 DES,DESede,Blowfish. */
    private final static String ALGORITHM = "DES";

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位.<br/>
     * 
     * @author zhanghanlin
     * @param keyBytes
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    private static Key getKey(byte[] keyBytes) {
        byte[] bytes = new byte[8];
        for (int i = 0; (i < keyBytes.length) && (i < bytes.length); i++) {
            bytes[i] = keyBytes[i];
        }
        return new SecretKeySpec(bytes, ALGORITHM);
    }

    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(key));
            return cipher.doFinal(data);
        } catch (Exception e) {
            logger.error("encrypt error : {}", e.getMessage(), e);
            return null;
        }
    }

    public static String encrypt(String data, String key) {
        logger.debug("encrypt [data : {}, key : {}]", data, key);
        return StringUtils.byte2Hex(encrypt(data.getBytes(), key.getBytes()));
    }

    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getKey(key));
            return cipher.doFinal(data);
        } catch (Exception e) {
            logger.error("decrypt error : {}", e.getMessage(), e);
            return null;
        }
    }

    public static String decrypt(String data, String key) {
        logger.debug("decrypt [data : {}, key : {}]", data, key);
        return new String(decrypt(StringUtils.hex2byte(data), key.getBytes()));
    }
}
