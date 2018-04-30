package com.kaishengit;

import org.apache.commons.codec.digest.DigestUtils;

import com.kaishengit.util.Config;

public class Test {

	public static void main(String[] args) {
		/*String password = "000000";
		System.out.println(Config.get("user.password.salt"));
		String md5Password = DigestUtils.md5Hex(password + Config.get("user.password.salt"));
		System.out.println(md5Password);
		password.equals("111");
		int a = 16>>3;
		int b = 2<<3;
		System.out.println(a);
		System.out.println(b);*/
		System.out.println('a');
		System.out.println('a'*'b');
		System.out.println((char)('a'+4));
		char tmp1 = '1';
		char tmp2 = '0';
		String tmp = (tmp1 + tmp2)+"";
		tmp.equals("123");
	}

}
