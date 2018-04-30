package com.kaishengit;


import org.apache.commons.codec.digest.DigestUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num = DigestUtils.md5Hex("123456");
		System.out.println(num);
	}

}
