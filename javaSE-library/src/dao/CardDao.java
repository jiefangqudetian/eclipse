package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Card;
import util.DbHelp;
import util.RowMapper;

public class CardDao {

	DbHelp<Card> dbHelp = new DbHelp<>();
	public void saveCard(Card card) {
		String sql = "insert into t_card (cardid,name,sex,address,phone) values (?,?,?,?,?)";
		DbHelp.excuteUpdate(sql, card.getId(),card.getName(),card.getSex(),card.getAddress(),card.getPhone());
	}
	
	public Card findByName(String name) {
		String sql = "select name from t_card where name = ?";
		Card card = dbHelp.queryForObject(sql, new CardRowMapper(), name);
		return card;
	}
	private class CardRowMapper implements RowMapper<Card> {

		@Override
		public Card mapper(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Card card = new Card();
			card.setId(rs.getString(1));
			card.setName(rs.getString(2));
			card.setSex(rs.getString(3));
			card.setAddress(rs.getString(4));
			card.setPhone(rs.getString(5));
			return card;
		}
		
	}
}
