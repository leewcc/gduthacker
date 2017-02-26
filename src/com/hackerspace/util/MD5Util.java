package com.hackerspace.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述：对密码进行MD5加密
 * @author CHEN
 *
 */
public final class MD5Util {
    public static String GetMD5Code(String str)  {
      	StringBuilder newStr=new StringBuilder();
      	newStr.append("");
    	try {
	    	MessageDigest md=MessageDigest.getInstance("MD5");
	    	md.update(str.getBytes());
	    	byte mdBytes[]=md.digest();
	  
	    	for(int i=0;i<mdBytes.length;i++) {
	    		int temp;
	    		if(mdBytes[i]<0) {
	    			temp=256+mdBytes[i];    			
	    		} else {
	    			temp=mdBytes[i];
	    		}
	    		if(temp<16) {
	    			newStr.append("0");
	    		} 
	    		newStr.append(Integer.toString(temp,16));
	    	}
    	} catch (Exception e) {
    		newStr.append(1);
    	}
    	return newStr.toString();
    }
}
