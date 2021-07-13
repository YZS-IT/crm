package com.yzs.crm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String getMD5(String password){

        try {
            MessageDigest digest = MessageDigest.getInstance("md5"); //得到一个MD5消息摘要器
            byte[] bytes = digest.digest(password.getBytes()); //得到哈希值,是一个长度为16的字节数组,共128位的二进制.
            StringBuffer buffer = new StringBuffer();
            for (byte aByte : bytes) {
                int number = aByte & 0xff; //加盐
                String result = Integer.toHexString(number);
                if(result.length()==1) buffer.append("0");
                buffer.append(result);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

}
