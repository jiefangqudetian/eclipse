//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
	
	public static void change(Student stu) {
		stu.setScore(100);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Student student = new Student("tom",12,80);
		System.out.println(student);
		change(student);
		System.out.println(student);
		Util.goodStudent(student);
		System.out.println(student);*/
		
		/*Student student  = new Student();
		System.out.println(student.weight);
		char a = '\u1234';
		System.out.println(a);*/
		
		for(int i = 0; i < 200 ; i++) {
			char c = (char) i;
			System.out.print(c);
		}
		
//		int b = 'a';
//		System.out.println(b);
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*Scanner input = new Scanner(System.in);
		String string = input.next() ;
		switch (string) {
		case "1":
			
			break;

		default:
			break;
		}*/
		
//		int a = '��';
//		System.out.println(a);
/*		try {
			FileInputStream fileInputStream = new FileInputStream("e:/kaisheng25/�μ�/��ϰ/1.txt");
			FileOutputStream fileOutputStream = new FileOutputStream("e:/kaisheng25/�μ�/��ϰ/�½��ı��ĵ�.txt",true);
			int len = 0;
			int i = 0; 
			int o = 0;
			while ((len=fileInputStream.read())!=-1) {
				fileOutputStream.write(len);
				fileOutputStream.write(len);
				fileOutputStream.write(len);
				System.out.print(len);
				i++;
				if (i==100) {
					break;
				}
			}
			
			System.out.println("\n");
			System.out.println("ת��"+i+"��");
			System.out.println("û�йؼ���");
			while ((len=fileInputStream.read())!=-1) {
				fileOutputStream.write(len);
				fileOutputStream.write(len);
				fileOutputStream.write(len);
				System.out.print(len);
				o++;
				if (o==100) {
					break;
				}
			}
			fileOutputStream.close();
			fileInputStream.close();
			System.out.println("\n");
			System.out.println("�ڶ���ת��"+o+"��");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			/*
			FileInputStream fileInputStream = new FileInputStream("e:/kaisheng25/�μ�/��ϰ/4.txt");
			int len = 0;
			int i = 0;
			while ((len=fileInputStream.read())!=-1) {
				System.out.println(len);
				i++;
			}
			System.out.println("\nת��"+i+"��");
			fileInputStream.close();*/
			
			
			FileOutputStream fileOutputStream = new FileOutputStream("e:/kaisheng25/�μ�/��ϰ/�½��ı��ĵ�.txt", true);
			int i = 0;
			while (i<1000) {
				fileOutputStream.write(i);
				if (i%100==0) {
					fileOutputStream.write(13);
					fileOutputStream.write(10);
				}
				i++;
			}
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
