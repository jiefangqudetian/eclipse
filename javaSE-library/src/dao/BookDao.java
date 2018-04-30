package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Book;
import util.DbHelp;
import util.RowMapper;

public class BookDao {
	DbHelp<Book> dbHelp = new DbHelp<>();
	public List<Book> findAll(){
		String sql = "select bookid,name,author,publish,count,currcount from t_book";
		List<Book> list = dbHelp.queryForList(sql, new BookRowMapper());
		return list;
	}

	/**
	 * @param id
	 * @return
	 * 通过ISBN码查询，可将bookid改为 string类型
	 */
	public Book findById(int id) {
		String sql = "select bookid,name,author,publish,count,currcount from t_book where bookid = ?";
		Book book = dbHelp.queryForObject(sql, new BookRowMapper(), id);
		return book;
	}
	
	public void deleteById(int id) {
		String sql = "delete from t_book where bookid = ?";
		DbHelp.excuteUpdate(sql, id);
	}
	
	public void update(Book book) {
		String sql = "update t_book set name = ?,author = ?,publish = ?,count = ?,currcount = ? where bookid = ?";
		DbHelp.excuteUpdate(sql, new BookRowMapper() ,book.getName(),book.getAuthor(),book.getPublish(),book.getCount(),book.getCurrcount(),book.getId());
	}
	
	public List<Book> findByKey(String string) {
		String sql = "select bookid,name,author,publish,count,currcount from t_book where name like '%?%'";
		List<Book> list = dbHelp.queryForList(sql, new BookRowMapper(), string);
		return list;
	}
	
	public void saveBook(Book book) {
		String sql = "insert into t_book (bookid,name,author,publish,count) values (?,?,?,?,?)";
		DbHelp.excuteUpdate(sql, book.getId(),book.getName(),book.getAuthor(),book.getPublish(),book.getCount());
	}
	
	private class BookRowMapper implements RowMapper<Book> {

		@Override
		public Book mapper(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Book book = new Book();
			book.setId(rs.getInt(1));
			book.setName(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setPublish(rs.getString(4));
			book.setCount(rs.getInt(5));
			book.setCurrcount(rs.getInt(6));
			return book;
		}
		
	}
}
