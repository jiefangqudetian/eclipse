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
 * ���ֻ����ɾ�飬�����ڸĵ����
 */
public class CardBookDao {
	DbHelp<CardBook> dbHelp = new DbHelp<>();
	DbHelp<Book> dbHelp1 = new DbHelp<>();
	
	
	/**
	 * @param cid
	 * @return
	 * ����cardbook�� ͨ��card ��id ���Ҷ�Ӧ�鼮 ����boss 
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
	 * ɾ
	 */
	public void delete(CardBook cardBook) {
		String sql = "delete from t_cardbook where cid = ? and bid = ? ";
		DbHelp.excuteUpdate(sql, cardBook.getCardid(),cardBook.getBookid());
	}
	
	/**
	 * @param cardBook
	 * ��
	 */
	public void save(CardBook cardBook) {
		String sql = "insert into t_cardbook (cid,bid) values (?,?)";
		DbHelp.excuteUpdate(sql, cardBook.getCardid(),cardBook.getBookid());
	}

	
	/**
	 * @param cid
	 * @return
	 * ���ݽ���֤�Ų���˼���
	 */
	public int countByCardid(int cid) {
		String sql = "select count(*) from t_cardbook where cid = ?";
		int count = DbHelp.queryForCount(sql, cid);
		return count;
	}
	
	/**
	 * @param cid
	 * @param bid
	 * @return cardbook������ϵ
	 * ����bid cid�������ϵ��¼����������ֻ����һ��
	 */
	public CardBook findByCidAndBid(int cid,int bid) {
		String sql = "select cid as cardid,bid as bookid from t_cardbook where cid = ? and bid = ?";
		CardBook cardBook = dbHelp.queryForObject(sql, new BeanRowMapper<>(CardBook.class), cid,bid);
		return cardBook;
	}
	
	
}
