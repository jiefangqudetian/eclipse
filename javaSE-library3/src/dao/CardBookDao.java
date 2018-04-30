package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Book;
import entity.CardBook;
import util.BeanRowMapper;
import util.DbHelp;
import util.RowMapper;

/**
 * @author LENOVO
 * 这个只有增删查，不存在改的情况
 */
public class CardBookDao {
	DbHelp<CardBook> dbHelp = new DbHelp<>();
	DbHelp<Book> dbHelp1 = new DbHelp<>();
	
	
	/**
	 * @param cid
	 * @return
	 * 根据cardbook表 通过card 的id 查找对应书籍 ，大boss 
	 * 
	 */
	public List<Book> findBorrowBookByCardid(int cid) {
		String sql = "select isbn,name from t_cardbook as cb,t_book as b where cb.bid = b.id and cb.cid = ? ";
		List<Book> list = dbHelp1.queryForList(sql,new RowMapper<Book>() {

			@Override
			public Book mapper(ResultSet rs) throws SQLException {
				Book book = new Book();
				book.setIsbn(rs.getString(1));
				book.setName(rs.getString(2));
				return book;
			}
		}, cid);
		return list;
	}
	
	
	/**
	 * @param num
	 * 删
	 */
	public void delete(CardBook cardBook) {
		String sql = "delete from t_cardbook where cid = ? and bid = ? ";
		DbHelp.excuteUpdate(sql, cardBook.getCardid(),cardBook.getBookid());
	}
	
	/**
	 * @param cardBook
	 * 增
	 */
	public void save(CardBook cardBook) {
		String sql = "insert into t_cardbook (cid,bid) values (?,?)";
		DbHelp.excuteUpdate(sql, cardBook.getCardid(),cardBook.getBookid());
	}

	
	/**
	 * @param cid
	 * @return
	 * 根据借书证号查借了几本
	 */
	public int countByCardid(int cid) {
		String sql = "select count(*) from t_cardbook where cid = ?";
		int count = DbHelp.queryForCount(sql, cid);
		return count;
	}
	
	/**
	 * @param cid
	 * @param bid
	 * @return cardbook关联关系
	 * 根据bid cid查关联关系记录，联合主键只能有一条
	 */
	public CardBook findByCidAndBid(int cid,int bid) {
		String sql = "select cid as cardid,bid as bookid from t_cardbook where cid = ? and bid = ?";
		CardBook cardBook = dbHelp.queryForObject(sql, new BeanRowMapper<>(CardBook.class), cid,bid);
		return cardBook;
	}
	
	
}
