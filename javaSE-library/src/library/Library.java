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
 *bookdao 方法 findbyall finbykey  findbyid save
 *
 *
 *
 */
public class Library {
	
	BookDao bookDao = new BookDao();
	Scanner input = new Scanner(System.in);
	public void startup() {
		
		System.out.println("请输入管理员名字");
		String name = input.next();
		System.out.println("请输入管理员密码");
		String password = input.next();
		AdministratorDao administratorDao = new AdministratorDao();
		Administrator admin = administratorDao.findByNameAndPassword(name, password);
		if (admin!=null) {
			showview();
		} else {
			System.out.println("输入错误，请重新输入");
			startup();
		}
	}
	
	/**
	 * 显示系统功能
	 */
	private void showview() {

		System.out.println("1.借书");  //update book 数量
		System.out.println("2.还书");  //update book 数量
		System.out.println("3.添加新书"); // insert book
		System.out.println("4.修改书籍"); // update book
		System.out.println("5.删除书籍"); // delete book
		System.out.println("6.查看所有书籍"); // select book
		System.out.println("7.查找指定书籍"); // select 模糊查询 book
		System.out.println("8.新建图书证"); // insert 证件
		System.out.println("9.退出系统");  // 
		System.out.println("请输入选项");
		
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
			System.out.println("选项输入错误，请重新输入");
		}
		showview();
	}

	private void returnBook() {
		// TODO Auto-generated method stub
		System.out.println("请输入您的借书证号");
		String name = input.next();
		CardDao cardDao = new CardDao();
		Card card = cardDao.findByName(name);
		if(card!=null) {
			CardBookDao cardBookDao = new CardBookDao();
			List<CardBook> list = cardBookDao.findByCard(name);
			System.out.println("您借的书籍有");
			for (CardBook cardBook : list) {
				System.out.println(cardBook);
			}
			System.out.println("请输入您要还的书籍编号");
			int num = input.nextInt();
			if(cardBookDao.findByInt(num)!=null) {
				cardBookDao.deleteByInt(num);
				System.out.println("幻术成功");
				showview();
			} else {
				System.out.println("您未借此书，请重新输入");
			}
		} else {
			System.out.println("借书证号不存在，请重新输入");
		}
		returnBook();
	}

	/**
	 * 选项1.借书
	 */
	private void brrowBook() {
		// TODO Auto-generated method stub
		System.out.println("请输入您的借书证号");
		String name = input.next();
		CardDao cardDao = new CardDao();
		Card card = cardDao.findByName(name);
		if(card!=null) {
			showAllBooks();
			System.out.println("请输入您要借书的编号");
			int id = input.nextInt();
			Book book = bookDao.findById(id);
			if(book!=null) {
				
				//book.setCurrcount(book.getCurrcount() - 1); //假设只借一本书
				CardBook cardBook = new CardBook();
				cardBook.setCard(name);
				cardBook.setBook(book.getName());
				cardBook.setCount(book.getId());
				CardBookDao cardBookDao = new CardBookDao();
				cardBookDao.save(cardBook);
				System.out.println("借书成功");
				showview();
			} else {
				System.out.println("图书编号不存在，请重新输入");
			}
		} else {
			System.out.println("借书证号不存在，请重新输入");
		}
		brrowBook();
	}

	/**
	 * 选项8.新建图书证
	 */
	private void addcard() {
		// TODO Auto-generated method stub
		Card card = new Card();
		System.out.println("请输入要办理的借书证号");
		card.setId(input.next());
		System.out.println("请输入办理该借书证的人名");
		card.setName(input.next());
		System.out.println("请输入性别");
		card.setSex(input.next());
		System.out.println("请输入家庭住址");
		card.setAddress(input.next());
		System.out.println("请输入联系人电话");
		card.setPhone(input.next());
		CardDao cardDao = new CardDao();
		cardDao.saveCard(card);
	}

	/**
	 * 选项3.添加新书
	 */
	private void addBook() {
		// TODO Auto-generated method stub
		System.out.println("请输入书籍ISBN码");
		int bookid = input.nextInt();
		Book book = bookDao.findById(bookid);
		if (book==null) {
			book = new Book();
			System.out.println("请输入您要添加的书籍名称");
			book.setName(input.next());
			System.out.println("请输入书籍的作者");
			book.setAuthor(input.next());
			System.out.println("请输入书籍的出版社");
			book.setPublish(input.next());
			System.out.println("请输入添加数量");  //数量如果输成字符就要抛异常了
			book.setCount(input.nextInt());
			book.setCurrcount(input.nextInt());
			book.setId(bookid);
			bookDao.saveBook(book);
			
			System.out.println("书籍添加成功");
			
		} else {
			System.out.println("该书籍已存在，请勿重复录入");
		}
		
		
	}

	/**
	 * 选项7.查找指定书籍
	 * 只查书名么，还是所有信息都检索
	 */
	private void selectBooks() {
		// TODO Auto-generated method stub
		System.out.println("请输入你要查找的内容：");
		String string = input.next();
		List<Book> list = bookDao.findByKey(string);
		System.out.println("书籍编号"+"\t\t"+"书名"+"\t\t"+"作者"+"\t\t"+"出版社"+"\t\t"+"数量"+"\t\t"+"当前数量");
		for (Book book : list) {
			System.out.println(book);
		}
	}

	/**
	 * 选项4.修改书籍 
	 */
	private void changeBook() {
		showAllBooks();
		System.out.println("请输入您要修改的书籍编号：");
		int id = input.nextInt();
		Book book = bookDao.findById(id);
		if (book!=null) {
			boolean flag = false;
			System.out.println("是否需要修改书籍的名称?(Y|N)");
			String answer = input.next();
			
			if(answer.equalsIgnoreCase("y")) {
				System.out.println("请输入新的书籍名称:");
				String bookName = input.next();
				book.setName(bookName);
				flag = true;
			}
			
			System.out.println("是否要修改书籍的作者名称?(Y|N)");
			answer = input.next();
			
			if(answer.equalsIgnoreCase("y")) {
				System.out.println("请输入新的作者名称:");
				String authorName = input.next();
				book.setAuthor(authorName);
				flag = true;
			}
			
			System.out.println("是否要修改书籍的出版社名称?(Y|N)");
			answer = input.next();
			
			if(answer.equalsIgnoreCase("y")) {
				System.out.println("请输入新的出版社名称:");
				String publish = input.next();
				book.setPublish(publish);
				flag = true;
			}
			
			System.out.println("是否要修改书籍的总量?(Y|N)");
			answer = input.next();
			if(answer.equalsIgnoreCase("y")) {
				System.out.println("请输入新的书籍总量");
				int count = input.nextInt();
				int change = count - book.getCount();
				int currcount = book.getCurrcount() + change;
				if(currcount >= 0) {
					book.setCount(count);
					book.setCurrcount(currcount);
					flag = true;
				} else {
					System.out.println("数据库存数量不足，不能修改");
				}
				
			}
			if(flag) {
				bookDao.update(book);
				System.out.println("更新成功");
			}//这样写中途退出就不会保存
		} else {
			System.out.println("编号不存在");
		}
		/*String num = input.next();
		boolean bool = decideint(num);
		if (bool) {
			Integer id = new Integer(num);
			if (bookDao.findById(id)!=null) {
				Book book = bookDao.findById(id);
				System.out.println(book);
				System.out.println("书籍名字修改为");
				book.setName(input.next());
				System.out.println("书籍作者修改为");
				book.setAuthor(input.next());
				System.out.println("书籍出版社修改为");
				book.setPublish(input.next());
				System.out.println("书籍总数量修改为");//数量如果输成字符就要抛异常了
				book.setCount(input.nextInt());
				System.out.println("书籍现有数量修改为");//数量如果输成字符就要抛异常了
				book.setCurrcount(input.nextInt());*/
				bookDao.update(book);
				showview();
//			} else {
//				System.out.println("查无此书，请重新输入");
//			}
//		} else {
//			System.out.println("编号格式错误，请重新输入");
//		}
//		changeBook();
	}

	/**
	 * 选项6.显示所有书籍，其他选项会调用
	 */
	private void showAllBooks() {
		// TODO Auto-generated method stub
		List<Book> list = bookDao.findAll();
		System.out.println("书籍编号"+"\t\t"+"书名"+"\t\t"+"作者");
		for (Book book : list) {
			System.out.println(book.getId()+"\t\t"+book.getName()+"\t\t"+book.getAuthor()); //book的to string方法需要重写，还要多输出一行目录
		}
	}

	/**
	 * 选项5.删除书籍 findByAll + if +findById + deleteById
	 */
	private void deleteBook() {
		// TODO Auto-generated method stub
		showAllBooks();
		System.out.println("请输入要删除书籍编号");
		int id = input.nextInt();
		Book book = bookDao.findById(id);
		if (book!=null) {
			if (book.getCount() == book.getCurrcount()) {
				bookDao.deleteById(id);
			} else {
				System.out.println("书籍已被借出，无法删除");
			}
		} else {
			System.out.println("该书籍不存在");
		}
		
		/*String num = input.next();  //string转int 写个方法
		boolean bool = decideint(num);
		if (bool) {
			Integer id = new Integer(num);
			if (bookDao.findById(id)!=null) {
				bookDao.deleteById(id);
				showview();
			} else {
				System.out.println("查无此书，请重新输入");
			}
		} else {
			System.out.println("编号格式错误，请重新输入");
		}*/
		deleteBook();
	}

	/**
	 * 查看输入的书籍编号是不是纯数字，是返回true，不是返回false
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
