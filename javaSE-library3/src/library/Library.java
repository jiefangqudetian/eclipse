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
 *bookdao 方法 findbyall finbykey  findbyid save
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
		
		System.out.println("请输入管理员名字");
		String name = input.next();
		System.out.println("请输入管理员密码");
		String password = input.next();
		Admin admin = adminDao.findByNameAndPassword(name, password);
		if (admin!=null) {
			System.out.println("登录成功");
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

	/**
	 * 选项2.还书
	 */
	private void returnBook() {
		// TODO Auto-generated method stub
		//看借书证号是否存在
		//显示所借书籍
		//输入还书的isbn
		//还书，消除bid cid关系，书籍当前数量+1
		
		
		CardDao cardDao = new CardDao();
		CardBookDao cardBookDao = new CardBookDao();
		System.out.println("请输入您的借书证号");
		String cardid = input.next();
		Card card = cardDao.findByName(cardid);
		if(card!=null) {
			List<Book> list = cardBookDao.findBorrowBookByCardid(card.getId());//关键，多表联查
			if (!list.isEmpty()) {
				
				System.out.println("您借的书籍有");
				System.out.println("书籍编号"+"\t\t"+"书籍名称");
				for (Book book : list) {
					System.out.println(book.getIsbn()+"\t\t"+book.getName());
				}
				System.out.println("请输入您要还的书籍编号");
				String isbn = input.next();
				Book returnbook = bookDao.findByIsbn(isbn);
				if(returnbook!=null) {
					CardBook cardBook = cardBookDao.findByCidAndBid(card.getId(), returnbook.getId());
					if (cardBook!=null) {
						cardBookDao.delete(cardBook);//书籍，借书卡关系表删除记录，同步到数据库
						returnbook.setCurrcount(returnbook.getCurrcount()+1);//书籍+1
						bookDao.update(returnbook);//同步到数据库
						System.out.println("书籍："+returnbook.getName()+" 已经归还成功");
					} else {
						System.out.println("您未借此书，返回主菜单");
					}
				} else {
					System.out.println("不存在此图书编号，返回主菜单");
				}
				
			} else {
				System.out.println("您尚未借书，返回主菜单");
			}
				
		} else {
			System.out.println("借书证号不存在，返回主菜单");
		}
		showview();//出口，返回主菜单
	}

	/**
	 * 选项1.借书
	 */
	private void brrowBook() {
		//判断借书证是否存在，以及是否借够5本
		//显示所有图书
		//判断所借图书是否借过，是否存在，所借图书数量是否大于零
		//借书，book数量减1，cardbook数量加1
		CardBookDao cardBookDao = new CardBookDao();
		BookDao bookDao = new BookDao();
		CardDao cardDao = new CardDao();
		
		System.out.println("请输入您的借书证号");
		String cardid = input.next();
		Card card = cardDao.findByName(cardid);
		if(card!=null) {
			int count = cardBookDao.countByCardid(card.getId());
			if (count < 5) {
				showAllBooks();
				System.out.println("请输入您要借书的编号");
				String isbn = input.next();
				Book book = bookDao.findByIsbn(isbn);
				if (book!=null) {
					//判断数量是否大于0
					if (book.getCurrcount()>0) {
						CardBook cardBook = cardBookDao.findByCidAndBid(card.getId(), book.getId());
						//判断是否借阅
						if (cardBook==null) {
							cardBook = new CardBook();
							cardBook.setCardid(card.getId());
							cardBook.setBookid(book.getId());
							//同步，增加借阅记录，同步现存数量
							cardBookDao.save(cardBook);//增加借阅记录
							book.setCurrcount(book.getCurrcount() - 1);//书籍现存量减一
							bookDao.update(book);
							System.out.println("借书成功");
						} else {
							System.out.println("该书您已借阅，不能重复借阅");
						}
					} else {
						System.out.println("图书库存不足，无法借阅");
					}
				} else {
					System.out.println("所借图书不存在");
				}
			} else {
				System.out.println("借书数量已达到最大值");
			}
		} else {
			System.out.println("借书证号不存在");
		}
		showview();//返回主菜单，出口
	}

	/**
	 * 选项8.新建图书证
	 * 调用方法 savecard() findbyid() 
	 */
	private void addcard() {
		// TODO Auto-generated method stub
		
		System.out.println("请输入借书证号");
		String cardid = input.next();
		Card card = cardDao.findByName(cardid);
		if (card!=null) {
			System.out.println("借书证号已经存在，返回主菜单");
		} else {
			card = new Card();
			card.setCardid(cardid);
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
			System.out.println("借书证办理成功，返回主菜单");
		}
		showview();
		
	}

	/**
	 * 选项3.添加新书
	 * 调用的方法 findbyid  savebook
	 */
	private void addBook() {
		// TODO Auto-generated method stub
		System.out.println("请输入书籍ISBN码");
		String isbn = input.next();
		Book book = bookDao.findByIsbn(isbn);
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
			book.setIsbn(isbn);
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
		String isbn = input.next();
		Book book = bookDao.findByIsbn(isbn);
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
					System.out.println("库存数量不足，不能修改");
				}
				
			}
			if(flag) {
				bookDao.update(book);
				System.out.println("更新成功");
			} else {
				System.out.println("未修改信息，返回主菜单");
			}
			//这样写中途退出就不会保存
		} else {
			System.out.println("编号不存在");
		}
		showview(); //删除不删除都返回主菜单
	}

	/**
	 * 选项6.显示所有书籍，其他选项会调用
	 */
	private void showAllBooks() {
		List<Book> list = bookDao.findAll();
		System.out.println("书籍编号"+"\t\t"+"书名"+"\t\t"+"作者"+"\t\t"+"出版社"+"\t\t\t"+"总数量"+"\t\t"+"现存数量");
		for (Book book : list) {
			System.out.println(book.getIsbn()+"\t\t"+book.getName()+"\t\t"+book.getAuthor()+"\t\t"+book.getPublish()+"\t\t\t"+book.getCount()+"\t\t"+book.getCurrcount()); 
		}
	}

	/**
	 * 选项5.删除书籍 findByAll + if +findById + deleteById
	 */
	private void deleteBook() {
		showAllBooks();
		System.out.println("请输入要删除书籍编号");
		String isbn = input.next();
		Book book = bookDao.findByIsbn(isbn);
		if (book!=null) {
			if (book.getCount() == book.getCurrcount()) {
				bookDao.deleteByIsbn(isbn);
				System.out.println("删除成功");
			} else {
				System.out.println("书籍已被借出，无法删除");
			}
		} else {
			System.out.println("该书籍不存在");
		}
		
		showview(); //不管删除成功不成功，都回主菜单
	}

}
