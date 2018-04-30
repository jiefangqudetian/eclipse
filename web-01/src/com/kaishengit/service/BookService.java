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
			throw new ServiceException("ISBN�ظ�");
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
				//�ж��鼮���޽��
				bookDao.delById(id);
			} else {
				throw new ServiceException("�����쳣");
			}
		} else {
			throw new ServiceException("�����쳣");
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
	 * ����ҳ���ö�Ӧ��booklist����
	 * @param pageNo
	 * @return booklist����
	 */
	public List<Book> findBookByPageNo(int pageNo) {
		// ÿҳ��ʾ����
		int pageSize = 5;
		//��ѯ��ʼ�к�
		int start = (pageNo-1)*pageSize;
		return bookDao.findByPage(start,pageSize);
	}
	
	
	/**
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page<Book> findBookByPage(int pageNo) {
		// ����鼮������
		int total = bookDao.countBook();
		//���������� ��ǰҳ�����������page����
		Page<Book> page = new Page<>(total,pageNo);
		//����page��start����ʼ�У�  pageSize���ԣ�ÿҳ��ʾ�������booklist
		List<Book> booklist = bookDao.findByPage(page.getStart(), page.getPageSize());
		//��booklist��page����items��ֵ
		page.setItems(booklist);
		//����page����
		return page;
	}

}
