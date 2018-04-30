package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Book;
import com.kaishengit.util.DbHelp;

public class BookDao {
	public Book findByIsbn(String isbn) {
		String sql = "select * from t_book where isbn = ?";
		return DbHelp.query(sql, new BeanHandler<>(Book.class), isbn);
	}

	public void saveBook(Book book) {
		// TODO Auto-generated method stub
		String sql = "insert into t_book (isbn,name,author,publish,count,currcount) values (?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, book.getIsbn(),book.getName(),book.getAuthor(),book.getPublish(),book.getCount(),book.getCurrcount());
	}

	public  List<Book> findAll() {
		String sql = "select * from t_book";
		return DbHelp.query(sql, new BeanListHandler<>(Book.class));
	}

	public Book findById(String id) {
		String sql = "select * from t_book where id=?";
		return DbHelp.query(sql, new BeanHandler<>(Book.class), id);
	}

	public void delById(String id) {
		String sql = "delete from t_book where id = ?";
		DbHelp.executeUpdate(sql, id);
	}

	public void update(Book book) {
		String sql = "update t_book set name = ?,author=?,publish=?,isbn=?,count=?,currcount=? where id = ?";
		DbHelp.executeUpdate(sql, book.getName(),book.getAuthor(),book.getPublish(),book.getIsbn(),book.getCount(),book.getCurrcount(),book.getId());
	}

	/**
	 * 根据起始行号，每页显示数量查询数据库，返回booklist
	 * @return
	 */
	public List<Book> findByPage(int start, int pageSize) {
		// TODO Auto-generated method stub
		
		String sql = "select * from t_book limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Book.class), start,pageSize);
	}

	/**
	 * 查询book数量
	 * @return
	 */
	public int countBook() {
		// 查询到的是Long类型，转换为int
		String sql = "select count(*) from t_book";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}
	
	
}
