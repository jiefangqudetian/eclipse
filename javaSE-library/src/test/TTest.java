package test;

import java.util.Scanner;

public class TTest {

	public static void main(String[] args) throws Exception {
		System.out.println("1.加法");
		System.out.println("2.减法");
		System.out.println("请输入选项");
		Scanner input = new Scanner(System.in);
		int option = 0;
		try {
			
			option = input.nextInt();
		} catch (Exception e) {
			System.out.println(123);
		}
		/*String string = null;
		try {
			string.equals("abc");
			
		} catch (Exception e) {
			System.out.println(111);;
		}*/
		
		
		if (option == 1) {
			System.out.println("您选择的是加法");
		} else if (option == 2) {
			System.out.println("您选择的是减法");
		}else {
			System.out.println("选项输入错误");
		}
		
		input.close();
	}

}
