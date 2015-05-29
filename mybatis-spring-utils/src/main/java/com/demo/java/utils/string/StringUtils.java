package com.demo.java.utils.string;

import java.util.Random;

public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static String getRandomString(int length) {
        String randomStr = "abcdefghijklmnopqrstuvwxyz0123456789=!@#";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(randomStr.length());
            sb.append(randomStr.charAt(num));
        }
        return sb.toString();
    }

    public static String byte2Hex(byte[] bytes) {
        String hex = "", tmp = "";
        for (int i = 0; i < bytes.length; i++) {
            tmp = (Integer.toHexString(bytes[i] & 0XFF));
            if (tmp.length() == 1) {
                hex = hex + "0" + tmp;
            } else {
                hex = hex + tmp;
            }
        }
        tmp = null;
        return hex.toUpperCase();
    }

    public static byte[] hex2byte(String str) {
        byte[] arrB = str.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
}