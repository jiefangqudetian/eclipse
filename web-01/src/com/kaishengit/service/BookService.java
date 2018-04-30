package com.kaishengit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.BookDao;
import com.kaishengit.entity.Book;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;


public class BookService {
	BookDao bookDao = new BookDao();

	public void addBook(String name, String author, String publish, String isbn, String num)
		throws ServiceException{
		// TODO Auto-generated method stub
		Book book = bookDao.findByIsbn(isbn);
		if (book==null) {
			book = new Book();
			book.setName(name);
			book.setAuthor(author);
			book.setPublish(publish);
			book.setIsbn(isbn);
			book.setCount(Integer.parseInt(num));
			book.setCurrcount(Integer.parseInt(num));
			
			bookDao.saveBook(book);
			
		} else {
			throw new ServiceException("ISBN重复");
		}
	}

	public List<Book> findAllBook() {
		// TODO Auto-generated method stub
		return bookDao.findAll();
	}

	public void delBook(String id) {
		// TODO Auto-generated method stub
		if (StringUtils.isNumeric(id)) {
			Book book = bookDao.findById(id);
			if (book!=null) {
				//判断书籍有无借出
				bookDao.delById(id);
			} else {
				throw new ServiceException("参数异常");
			}
		} else {
			throw new ServiceException("参数异常");
		}
	}

	public Book findByBook(String id) {
		
		return bookDao.findById(id);
	}

	public void updateBook(String id, String name, String author, String publish, String isbn, String count,
			String currCount) {
		Book book = new Book();
		
		book.setId(Integer.parseInt(id));
		book.setName(name);
		book.setAuthor(author);
		book.setPublish(publish);
		book.setIsbn(isbn);
		book.setCount(Integer.parseInt(count));
	
		book.setCurrcount(Integer.parseInt(currCount));
		
		bookDao.update(book);
		
		
	}

	
	
	/**
	 * 根据页码获得对应的booklist集合
	 * @param pageNo
	 * @return booklist集合
	 */
	public List<Book> findBookByPageNo(int pageNo) {
		// 每页显示数量
		int pageSize = 5;
		//查询起始行号
		int start = (pageNo-1)*pageSize;
		return bookDao.findByPage(start,pageSize);
	}
	
	
	/**
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page<Book> findBookByPage(int pageNo) {
		// 获得书籍总数量
		int total = bookDao.countBook();
		//传入总数量 当前页码参数，构造page对象
		Page<Book> page = new Page<>(total,pageNo);
		//根据page的start（起始行）  pageSize属性（每页显示数）获得booklist
		List<Book> booklist = bookDao.findByPage(page.getStart(), page.getPageSize());
		//将booklist给page对象items赋值
		page.setItems(booklist);
		//返回page对象
		return page;
	}

}
