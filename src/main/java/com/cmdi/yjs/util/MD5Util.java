package com.cmdi.yjs.util;

import java.security.MessageDigest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.DigestUtils;

import sun.misc.BASE64Encoder;

public class MD5Util {
	@SuppressWarnings("restriction")
	public static String md5(String message) {
	     try{
	         MessageDigest md = MessageDigest.getInstance("md5");
	         byte b[] = md.digest(message.getBytes("utf-8"));
	         return new BASE64Encoder().encode(b);
	     }catch(Exception e){
	         throw new RuntimeException(e);
	     }
	}
	public static void main(String []args){
		String md5 = md5("19980101@qq");
		System.out.println(md5);
		
		Md5Hash md51 = new Md5Hash("19980101@qq");
		System.out.println(md51.toHex());
		System.out.println(md51.toBase64());
		System.out.println(md51.toString());
		
		System.out.println(DigestUtils.md5DigestAsHex("19980101@qq".getBytes()));

	}
}
