package com.demo.java.utils.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.demo.java.utils.string.StringUtils;

public class DESUtils {
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
    private static Key getKey(byte[] keyBytes) throws Exception {
        byte[] bytes = new byte[8];// 创建一个空的8位字节数组（默认值为0）
        for (int i = 0; (i < keyBytes.length) && (i < bytes.length); i++) {// 将原始字节数组转换为8位
            bytes[i] = keyBytes[i];
        }
        Key key = new SecretKeySpec(bytes, ALGORITHM);// 生成密钥
        return key;
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(key));
        return cipher.doFinal(data);
    }

    public static String encrypt(String data, String key) throws Exception {
        return StringUtils.byte2Hex(encrypt(data.getBytes(), key.getBytes()));
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getKey(key));
        return cipher.doFinal(data);
    }

    public static String decrypt(String data, String key) throws Exception {
        return new String(decrypt(StringUtils.hex2byte(data), key.getBytes()));
    }
}
