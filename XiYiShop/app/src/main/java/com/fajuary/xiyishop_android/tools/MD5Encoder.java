package com.fajuary.xiyishop_android.tools;

import java.security.MessageDigest;

/**
 *
 *@author 黄辉
 *@time 2016/10/19 13:48
 *
 * MD5加密
*/
public class MD5Encoder {
	
	public static String encode(String string) throws Exception {
	    byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
	    StringBuilder hex = new StringBuilder(hash.length * 2);
	    for (byte b : hash) {
	        if ((b & 0xFF) < 0x10) {
	        	hex.append("0");
	        }
	        hex.append(Integer.toHexString(b & 0xFF));
	    }
	    return hex.toString();
	}
}
