package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaishengit.entity.Book;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.RowMapper;

public class BookDao {

	public Book findByIsbn(String isbn) {
		String sql = "select * from t_book where isbn = ?";
		return DbHelp.queryForObject(sql, new BookRowMapper(), isbn);
	}

	private class BookRowMapper implements RowMapper<Book> {

		@Override
		public Book mapperRow(ResultSet rs) throws SQLException {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setISBN(rs.getString("isbn"));
			book.setBookAuthor(rs.getString("authorname"));
			book.setBookName(rs.getString("bookname"));
			book.setPublishName(rs.getString("publishname"));
			book.setTotalNum(rs.getInt("totalnum"));
			book.setCurrNum(rs.getInt("currentnum"));
			return book;
		}

	}

	public void save(Book book) {
		String sql = "insert into t_book (isbn,bookname,authorname, publishname,totalnum,currentnum) values (?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, book.getISBN(), book.getBookName(), book.getBookAuthor(), book.getPublishName(),book.getTotalNum(), book.getCurrNum());
	}

	public void update(Book book) {
		String sql = "update t_book set bookname = ?, authorname = ?, totalNum = ?, currentNum = ? where id = ? ";
		DbHelp.executeUpdate(sql, book.getBookName(), book.getBookAuthor(), book.getTotalNum(), book.getCurrNum(),book.getId());
	}

	public void delete(int id) {
		String sql = "delete from t_book where id = ?";
		DbHelp.executeUpdate(sql, id);
	}

	public List<Book> findAll() {
		String sql = "select * from t_book";
		return DbHelp.queryForList(sql, new BookRowMapper());
	}

	public List<Book> findByKeys(String keys) {
//		String keyWords = "%" + keys + "%";
//		String sql = "select * from t_book where bookname like ?";
		String sql = "select * from t_book where bookname like concat('%',?,'%')";
		return DbHelp.queryForList(sql, new BookRowMapper(), keys);
	}

}
