package test;

import java.util.Scanner;

public class TTest {

	public static void main(String[] args) throws Exception {
		System.out.println("1.�ӷ�");
		System.out.println("2.����");
		System.out.println("������ѡ��");
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
			System.out.println("��ѡ����Ǽӷ�");
		} else if (option == 2) {
			System.out.println("��ѡ����Ǽ���");
		}else {
			System.out.println("ѡ���������");
		}
		
		input.close();
	}

}
