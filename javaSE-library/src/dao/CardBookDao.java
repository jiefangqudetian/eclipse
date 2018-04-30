package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.CardBook;
import util.DbHelp;
import util.RowMapper;

public class CardBookDao {
	DbHelp<CardBook> dbHelp = new DbHelp<>();
	public List<CardBook> findByCard(String card) {
		String sql = "select card,book,count from t_cardbook where card = ? ";
		List<CardBook> list = dbHelp.queryForList(sql,new CardBookRowMapper(), card);
		return list;
	}
	
	public List<CardBook> findByInt(int num) {
		String sql = "select card,book,count from t_cardbook where count = ? ";
		List<CardBook> list = dbHelp.queryForList(sql,new CardBookRowMapper(), num);
		return list;
	}
	
	public void deleteByInt(int num ) {
		String sql = "delete from t_cardbook where count = ? ";
		DbHelp.excuteUpdate(sql, num);
	}
	
	public void save(CardBook cardBook) {
		String sql = "insert into t_cardbook (card,book,count) values (?,?,?)";
		DbHelp.excuteUpdate(sql, cardBook.getCard(),cardBook.getBook(),cardBook.getCount());
	}
	/*public void updateCount(CardBook cardBook) {
		String sql = "update t_cardbook set count = ? where card = ?,book = ?";
		DbHelp.excuteUpdate(sql, cardBook.getCount(),cardBook.getCard(),cardBook.getBook());
	}*/
	private class CardBookRowMapper implements RowMapper<CardBook> {

		@Override
		public CardBook mapper(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			CardBook cardBook = new CardBook();
			cardBook.setCard(rs.getString(1));
			cardBook.setBook(rs.getString(2));
			cardBook.setCount(rs.getInt(3));
			return cardBook;
		}
		
	}
}
