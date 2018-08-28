package com.sdy.controller.test;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

import com.sdy.util.MD5;

public class SHA1 {

	private static final char[] HEX = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式  
        for (int j = 0; j < len; j++) {
            buf.append(HEX[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static void main(String[] args) {
    	String para = "你好";
    	String sha1Str  = encode(para);
    	System.out.println(sha1Str);
    	String md5Str = MD5.md5(para);
    	System.out.println(md5Str);
    	String sha1 = DigestUtils.sha1Hex(para);
    	System.out.println(sha1);
	}
}
