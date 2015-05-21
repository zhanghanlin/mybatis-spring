package com.demo.java.utils.string;

public class StringUtils extends org.apache.commons.lang.StringUtils {
    /**
     * 
     * 将字节数组转成 16. <br/>
     * 进制的字符串来表示.<br/>
     * 每个字节采用两个字符表表示.<br/>
     * 
     * @author zhanghanlin
     * @param bytes
     * @return
     * @since JDK 1.7
     */
    public static String byte2Hex(byte[] bytes) {
        char[] HEX = "0123456789abcdef".toCharArray();
        char[] chars = new char[bytes.length * 2];
        for (int i = 0, offset = 0; i < bytes.length; i++) {
            chars[offset++] = HEX[(bytes[i] >> 4) & 0xf];
            chars[offset++] = HEX[bytes[i] & 0xf];
        }
        return new String(chars);
    }
}