package com.icss.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class GetMd5 {
	/**
	 * 加密函数
	 */
    public static String md5(String str){
    	//加密盐
		String salt = "zhongruanguojichangshaetc*lm";
	    return new Md5Hash(str,salt).toString() ;
	}
    
    public static String defultPwd(){//生成随机密码
    	String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz!@#$%^&*_";
    	String rtnStr = "";
    	for (int i = 0; i < 10; i++) {
	        //循环随机获得当次字符，并移走选出的字符
	        String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
	        rtnStr += nowStr;
	        generateSource = generateSource.replaceAll(nowStr, "");
	  }
    	return rtnStr;
    }
    
}
