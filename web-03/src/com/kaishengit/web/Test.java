package com.kaishengit.web;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		File saveDir = new File("e:/upload");
		String fileName = "123.txt";
		File file = new File(saveDir,fileName);
		System.out.println(file.exists());
	}

}
