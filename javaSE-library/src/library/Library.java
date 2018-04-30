package library;

import java.util.List;
import java.util.Scanner;

import dao.AdministratorDao;
import dao.BookDao;
import dao.CardBookDao;
import dao.CardDao;
import entity.Administrator;
import entity.Book;
import entity.Card;
import entity.CardBook;

/**
 * @author LENOVO
 *bookdao ���� findbyall finbykey  findbyid save
 *
 *
 *
 */
public class Library {
	
	BookDao bookDao = new BookDao();
	Scanner input = new Scanner(System.in);
	public void startup() {
		
		System.out.println("���������Ա����");
		String name = input.next();
		System.out.println("���������Ա����");
		String password = input.next();
		AdministratorDao administratorDao = new AdministratorDao();
		Administrator admin = administratorDao.findByNameAndPassword(name, password);
		if (admin!=null) {
			showview();
		} else {
			System.out.println("�����������������");
			startup();
		}
	}
	
	/**
	 * ��ʾϵͳ����
	 */
	private void showview() {

		System.out.println("1.����");  //update book ����
		System.out.println("2.����");  //update book ����
		System.out.println("3.�������"); // insert book
		System.out.println("4.�޸��鼮"); // update book
		System.out.println("5.ɾ���鼮"); // delete book
		System.out.println("6.�鿴�����鼮"); // select book
		System.out.println("7.����ָ���鼮"); // select ģ����ѯ book
		System.out.println("8.�½�ͼ��֤"); // insert ֤��
		System.out.println("9.�˳�ϵͳ");  // 
		System.out.println("������ѡ��");
		
		String option = input.next();
		if (option.equals("1")) {
			brrowBook();
		} else if (option.equals("2")) {
			returnBook();
		} else if (option.equals("3")) {
			addBook();
		} else if (option.equals("4")) {
			changeBook();
		} else if (option.equals("5")) {
			deleteBook();
		} else if (option.equals("6")) {
			showAllBooks();
		} else if (option.equals("7")) {
			selectBooks();
		} else if (option.equals("8")) {
			addcard();
		} else if (option.equals("9")) {
			System.exit(0);
		} else {
			System.out.println("ѡ�������������������");
		}
		showview();
	}

	private void returnBook() {
		// TODO Auto-generated method stub
		System.out.println("���������Ľ���֤��");
		String name = input.next();
		CardDao cardDao = new CardDao();
		Card card = cardDao.findByName(name);
		if(card!=null) {
			CardBookDao cardBookDao = new CardBookDao();
			List<CardBook> list = cardBookDao.findByCard(name);
			System.out.println("������鼮��");
			for (CardBook cardBook : list) {
				System.out.println(cardBook);
			}
			System.out.println("��������Ҫ�����鼮���");
			int num = input.nextInt();
			if(cardBookDao.findByInt(num)!=null) {
				cardBookDao.deleteByInt(num);
				System.out.println("�����ɹ�");
				showview();
			} else {
				System.out.println("��δ����飬����������");
			}
		} else {
			System.out.println("����֤�Ų����ڣ�����������");
		}
		returnBook();
	}

	/**
	 * ѡ��1.����
	 */
	private void brrowBook() {
		// TODO Auto-generated method stub
		System.out.println("���������Ľ���֤��");
		String name = input.next();
		CardDao cardDao = new CardDao();
		Card card = cardDao.findByName(name);
		if(card!=null) {
			showAllBooks();
			System.out.println("��������Ҫ����ı��");
			int id = input.nextInt();
			Book book = bookDao.findById(id);
			if(book!=null) {
				
				//book.setCurrcount(book.getCurrcount() - 1); //����ֻ��һ����
				CardBook cardBook = new CardBook();
				cardBook.setCard(name);
				cardBook.setBook(book.getName());
				cardBook.setCount(book.getId());
				CardBookDao cardBookDao = new CardBookDao();
				cardBookDao.save(cardBook);
				System.out.println("����ɹ�");
				showview();
			} else {
				System.out.println("ͼ���Ų����ڣ�����������");
			}
		} else {
			System.out.println("����֤�Ų����ڣ�����������");
		}
		brrowBook();
	}

	/**
	 * ѡ��8.�½�ͼ��֤
	 */
	private void addcard() {
		// TODO Auto-generated method stub
		Card card = new Card();
		System.out.println("������Ҫ����Ľ���֤��");
		card.setId(input.next());
		System.out.println("���������ý���֤������");
		card.setName(input.next());
		System.out.println("�������Ա�");
		card.setSex(input.next());
		System.out.println("�������ͥסַ");
		card.setAddress(input.next());
		System.out.println("��������ϵ�˵绰");
		card.setPhone(input.next());
		CardDao cardDao = new CardDao();
		cardDao.saveCard(card);
	}

	/**
	 * ѡ��3.�������
	 */
	private void addBook() {
		// TODO Auto-generated method stub
		System.out.println("�������鼮ISBN��");
		int bookid = input.nextInt();
		Book book = bookDao.findById(bookid);
		if (book==null) {
			book = new Book();
			System.out.println("��������Ҫ��ӵ��鼮����");
			book.setName(input.next());
			System.out.println("�������鼮������");
			book.setAuthor(input.next());
			System.out.println("�������鼮�ĳ�����");
			book.setPublish(input.next());
			System.out.println("�������������");  //�����������ַ���Ҫ���쳣��
			book.setCount(input.nextInt());
			book.setCurrcount(input.nextInt());
			book.setId(bookid);
			bookDao.saveBook(book);
			
			System.out.println("�鼮��ӳɹ�");
			
		} else {
			System.out.println("���鼮�Ѵ��ڣ������ظ�¼��");
		}
		
		
	}

	/**
	 * ѡ��7.����ָ���鼮
	 * ֻ������ô������������Ϣ������
	 */
	private void selectBooks() {
		// TODO Auto-generated method stub
		System.out.println("��������Ҫ���ҵ����ݣ�");
		String string = input.next();
		List<Book> list = bookDao.findByKey(string);
		System.out.println("�鼮���"+"\t\t"+"����"+"\t\t"+"����"+"\t\t"+"������"+"\t\t"+"����"+"\t\t"+"��ǰ����");
		for (Book book : list) {
			System.out.println(book);
		}
	}

	/**
	 * ѡ��4.�޸��鼮 
	 */
	private void changeBook() {
		showAllBooks();
		System.out.println("��������Ҫ�޸ĵ��鼮��ţ�");
		int id = input.nextInt();
		Book book = bookDao.findById(id);
		if (book!=null) {
			boolean flag = false;
			System.out.println("�Ƿ���Ҫ�޸��鼮������?(Y|N)");
			String answer = input.next();
			
			if(answer.equalsIgnoreCase("y")) {
				System.out.println("�������µ��鼮����:");
				String bookName = input.next();
				book.setName(bookName);
				flag = true;
			}
			
			System.out.println("�Ƿ�Ҫ�޸��鼮����������?(Y|N)");
			answer = input.next();
			
			if(answer.equalsIgnoreCase("y")) {
				System.out.println("�������µ���������:");
				String authorName = input.next();
				book.setAuthor(authorName);
				flag = true;
			}
			
			System.out.println("�Ƿ�Ҫ�޸��鼮�ĳ���������?(Y|N)");
			answer = input.next();
			
			if(answer.equalsIgnoreCase("y")) {
				System.out.println("�������µĳ���������:");
				String publish = input.next();
				book.setPublish(publish);
				flag = true;
			}
			
			System.out.println("�Ƿ�Ҫ�޸��鼮������?(Y|N)");
			answer = input.next();
			if(answer.equalsIgnoreCase("y")) {
				System.out.println("�������µ��鼮����");
				int count = input.nextInt();
				int change = count - book.getCount();
				int currcount = book.getCurrcount() + change;
				if(currcount >= 0) {
					book.setCount(count);
					book.setCurrcount(currcount);
					flag = true;
				} else {
					System.out.println("���ݿ���������㣬�����޸�");
				}
				
			}
			if(flag) {
				bookDao.update(book);
				System.out.println("���³ɹ�");
			}//����д��;�˳��Ͳ��ᱣ��
		} else {
			System.out.println("��Ų�����");
		}
		/*String num = input.next();
		boolean bool = decideint(num);
		if (bool) {
			Integer id = new Integer(num);
			if (bookDao.findById(id)!=null) {
				Book book = bookDao.findById(id);
				System.out.println(book);
				System.out.println("�鼮�����޸�Ϊ");
				book.setName(input.next());
				System.out.println("�鼮�����޸�Ϊ");
				book.setAuthor(input.next());
				System.out.println("�鼮�������޸�Ϊ");
				book.setPublish(input.next());
				System.out.println("�鼮�������޸�Ϊ");//�����������ַ���Ҫ���쳣��
				book.setCount(input.nextInt());
				System.out.println("�鼮���������޸�Ϊ");//�����������ַ���Ҫ���쳣��
				book.setCurrcount(input.nextInt());*/
				bookDao.update(book);
				showview();
//			} else {
//				System.out.println("���޴��飬����������");
//			}
//		} else {
//			System.out.println("��Ÿ�ʽ��������������");
//		}
//		changeBook();
	}

	/**
	 * ѡ��6.��ʾ�����鼮������ѡ������
	 */
	private void showAllBooks() {
		// TODO Auto-generated method stub
		List<Book> list = bookDao.findAll();
		System.out.println("�鼮���"+"\t\t"+"����"+"\t\t"+"����");
		for (Book book : list) {
			System.out.println(book.getId()+"\t\t"+book.getName()+"\t\t"+book.getAuthor()); //book��to string������Ҫ��д����Ҫ�����һ��Ŀ¼
		}
	}

	/**
	 * ѡ��5.ɾ���鼮 findByAll + if +findById + deleteById
	 */
	private void deleteBook() {
		// TODO Auto-generated method stub
		showAllBooks();
		System.out.println("������Ҫɾ���鼮���");
		int id = input.nextInt();
		Book book = bookDao.findById(id);
		if (book!=null) {
			if (book.getCount() == book.getCurrcount()) {
				bookDao.deleteById(id);
			} else {
				System.out.println("�鼮�ѱ�������޷�ɾ��");
			}
		} else {
			System.out.println("���鼮������");
		}
		
		/*String num = input.next();  //stringתint д������
		boolean bool = decideint(num);
		if (bool) {
			Integer id = new Integer(num);
			if (bookDao.findById(id)!=null) {
				bookDao.deleteById(id);
				showview();
			} else {
				System.out.println("���޴��飬����������");
			}
		} else {
			System.out.println("��Ÿ�ʽ��������������");
		}*/
		deleteBook();
	}

	/**
	 * �鿴������鼮����ǲ��Ǵ����֣��Ƿ���true�����Ƿ���false
	 * @param string
	 * @return
	 */
	/*private boolean decideint(String string) {
		char[] cha = string.toCharArray();
		for (char c : cha) {
			int i = c;
			if(i<48 && i>57) {
				return false;
			}
		}
		return true;
	}*/
}
