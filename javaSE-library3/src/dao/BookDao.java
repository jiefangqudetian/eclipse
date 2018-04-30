package dao;

import java.util.List;

import entity.Book;
import util.BeanRowMapper;
import util.DbHelp;

public class BookDao {
	
	DbHelp<Book> dbHelp = new DbHelp<>();
	
	
	/**
	 * 查询所有
	 */
	public  List<Book> findAll(){
		String sql = "select * from t_book";
		List<Book> list = dbHelp.queryForList(sql, new BeanRowMapper<>(Book.class));
		return list;
	}

	/**
	 * 通过ISBN码查询
	 */
	public Book findByIsbn(String isbn) {
		String sql = "select * from t_book where isbn = ?";
		Book book = dbHelp.queryForObject(sql, new BeanRowMapper<>(Book.class), isbn);
		return book;
	}
	
	/**
	 * 通过关键字对书名查询
	 */
	public List<Book> findByKey(String string) {
		String key = "%"+string+"%";
		String sql = "select * from t_book where name like ?";
		List<Book> list = dbHelp.queryForList(sql, new BeanRowMapper<>(Book.class), key);
		return list;
	}
	
	/**
	 * 删
	 */
	public void deleteByIsbn(String isbn) {
		String sql = "delete from t_book where isbn = ?";
		DbHelp.excuteUpdate(sql, isbn);
	}
	
	/**
	 * 增
	 */
	public void saveBook(Book book) {
		String sql = "insert into t_book (isbn,name,author,publish,count,currcount) values (?,?,?,?,?,?)";
		DbHelp.excuteUpdate(sql, book.getIsbn(),book.getName(),book.getAuthor(),book.getPublish(),book.getCount(),book.getCount());
	}
	
	/**
	 * 改
	 */
	public void update(Book book) {
		String sql = "update t_book set name = ?,author = ?,publish = ?,count = ?,currcount = ? where id = ?";
		DbHelp.excuteUpdate(sql, book.getName(),book.getAuthor(),book.getPublish(),book.getCount(),book.getCurrcount(),book.getId());
	}
	
	
	
	
	/**
	 * RowMapper实现类
	 *
	 */
	/*private class BookRowMapper implements RowMapper<Book> {

		@Override
		public Book mapper(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Book book = new Book();
			book.setId(rs.getInt(1));
			book.setIsbn(rs.getString(2));
			book.setName(rs.getString(3));
			book.setAuthor(rs.getString(4));
			book.setPublish(rs.getString(5));
			book.setCount(rs.getInt(6));
			book.setCurrcount(rs.getInt(7));
			return book;
		}
		
	}*/
}
