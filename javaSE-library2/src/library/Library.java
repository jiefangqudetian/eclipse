package library;

import java.util.List;
import java.util.Scanner;

import dao.AdminDao;
import dao.BookDao;
import dao.CardBookDao;
import dao.CardDao;
import entity.Admin;
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
	CardDao cardDao = new CardDao();
	AdminDao adminDao = new AdminDao();
	Scanner input = new Scanner(System.in);
	public void startup() {
		
		System.out.println("���������Ա����");
		String name = input.next();
		System.out.println("���������Ա����");
		String password = input.next();
		Admin admin = adminDao.findByNameAndPassword(name, password);
		if (admin!=null) {
			System.out.println("��¼�ɹ�");
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

	/**
	 * ѡ��2.����
	 */
	private void returnBook() {
		// TODO Auto-generated method stub
		//������֤���Ƿ����
		//��ʾ�����鼮
		//���뻹���isbn
		//���飬����bid cid��ϵ���鼮��ǰ����+1
		
		
		CardDao cardDao = new CardDao();
		CardBookDao cardBookDao = new CardBookDao();
		System.out.println("���������Ľ���֤��");
		String cardid = input.next();
		Card card = cardDao.findByName(cardid);
		if(card!=null) {
			List<Book> list = cardBookDao.findBorrowBookByCardid(card.getId());//�ؼ����������
			if (!list.isEmpty()) {
				
				System.out.println("������鼮��");
				System.out.println("�鼮���"+"\t\t"+"�鼮����");
				for (Book book : list) {
					System.out.println(book.getIsbn()+"\t\t"+book.getName());
				}
				System.out.println("��������Ҫ�����鼮���");
				String isbn = input.next();
				Book returnbook = bookDao.findByIsbn(isbn);
				if(returnbook!=null) {
					CardBook cardBook = cardBookDao.findByCidAndBid(card.getId(), returnbook.getId());
					if (cardBook!=null) {
						cardBookDao.delete(cardBook);//�鼮�����鿨��ϵ��ɾ����¼��ͬ�������ݿ�
						returnbook.setCurrcount(returnbook.getCurrcount()+1);//�鼮+1
						bookDao.update(returnbook);//ͬ�������ݿ�
						System.out.println("�鼮��"+returnbook.getName()+" �Ѿ��黹�ɹ�");
					} else {
						System.out.println("��δ����飬�������˵�");
					}
				} else {
					System.out.println("�����ڴ�ͼ���ţ��������˵�");
				}
				
			} else {
				System.out.println("����δ���飬�������˵�");
			}
				
		} else {
			System.out.println("����֤�Ų����ڣ��������˵�");
		}
		showview();//���ڣ��������˵�
	}

	/**
	 * ѡ��1.����
	 */
	private void brrowBook() {
		//�жϽ���֤�Ƿ���ڣ��Լ��Ƿ�蹻5��
		//��ʾ����ͼ��
		//�ж�����ͼ���Ƿ������Ƿ���ڣ�����ͼ�������Ƿ������
		//���飬book������1��cardbook������1
		CardBookDao cardBookDao = new CardBookDao();
		BookDao bookDao = new BookDao();
		CardDao cardDao = new CardDao();
		
		System.out.println("���������Ľ���֤��");
		String cardid = input.next();
		Card card = cardDao.findByName(cardid);
		if(card!=null) {
			int count = cardBookDao.countByCardid(card.getId());
			if (count < 5) {
				showAllBooks();
				System.out.println("��������Ҫ����ı��");
				String isbn = input.next();
				Book book = bookDao.findByIsbn(isbn);
				if (book!=null) {
					//�ж������Ƿ����0
					if (book.getCurrcount()>0) {
						CardBook cardBook = cardBookDao.findByCidAndBid(card.getId(), book.getId());
						//�ж��Ƿ����
						if (cardBook==null) {
							cardBook = new CardBook();
							cardBook.setCardid(card.getId());
							cardBook.setBookid(book.getId());
							//ͬ�������ӽ��ļ�¼��ͬ���ִ�����
							cardBookDao.save(cardBook);//���ӽ��ļ�¼
							book.setCurrcount(book.getCurrcount() - 1);//�鼮�ִ�����һ
							bookDao.update(book);
							System.out.println("����ɹ�");
						} else {
							System.out.println("�������ѽ��ģ������ظ�����");
						}
					} else {
						System.out.println("ͼ���治�㣬�޷�����");
					}
				} else {
					System.out.println("����ͼ�鲻����");
				}
			} else {
				System.out.println("���������Ѵﵽ���ֵ");
			}
		} else {
			System.out.println("����֤�Ų�����");
		}
		showview();//�������˵�������
	}

	/**
	 * ѡ��8.�½�ͼ��֤
	 * ���÷��� savecard() findbyid() 
	 */
	private void addcard() {
		// TODO Auto-generated method stub
		
		System.out.println("���������֤��");
		String cardid = input.next();
		Card card = cardDao.findByName(cardid);
		if (card!=null) {
			System.out.println("����֤���Ѿ����ڣ��������˵�");
		} else {
			card = new Card();
			card.setCardid(cardid);
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
			System.out.println("����֤����ɹ����������˵�");
		}
		showview();
		
	}

	/**
	 * ѡ��3.�������
	 * ���õķ��� findbyid  savebook
	 */
	private void addBook() {
		// TODO Auto-generated method stub
		System.out.println("�������鼮ISBN��");
		String isbn = input.next();
		Book book = bookDao.findByIsbn(isbn);
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
			book.setIsbn(isbn);
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
		String isbn = input.next();
		Book book = bookDao.findByIsbn(isbn);
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
					System.out.println("����������㣬�����޸�");
				}
				
			}
			if(flag) {
				bookDao.update(book);
				System.out.println("���³ɹ�");
			} else {
				System.out.println("δ�޸���Ϣ���������˵�");
			}
			//����д��;�˳��Ͳ��ᱣ��
		} else {
			System.out.println("��Ų�����");
		}
		showview(); //ɾ����ɾ�����������˵�
	}

	/**
	 * ѡ��6.��ʾ�����鼮������ѡ������
	 */
	private void showAllBooks() {
		List<Book> list = bookDao.findAll();
		System.out.println("�鼮���"+"\t\t"+"����"+"\t\t"+"����"+"\t\t"+"������"+"\t\t\t"+"������"+"\t\t"+"�ִ�����");
		for (Book book : list) {
			System.out.println(book.getIsbn()+"\t\t"+book.getName()+"\t\t"+book.getAuthor()+"\t\t"+book.getPublish()+"\t\t\t"+book.getCount()+"\t\t"+book.getCurrcount()); 
		}
	}

	/**
	 * ѡ��5.ɾ���鼮 findByAll + if +findById + deleteById
	 */
	private void deleteBook() {
		showAllBooks();
		System.out.println("������Ҫɾ���鼮���");
		String isbn = input.next();
		Book book = bookDao.findByIsbn(isbn);
		if (book!=null) {
			if (book.getCount() == book.getCurrcount()) {
				bookDao.deleteByIsbn(isbn);
				System.out.println("ɾ���ɹ�");
			} else {
				System.out.println("�鼮�ѱ�������޷�ɾ��");
			}
		} else {
			System.out.println("���鼮������");
		}
		
		showview(); //����ɾ���ɹ����ɹ����������˵�
	}

}
