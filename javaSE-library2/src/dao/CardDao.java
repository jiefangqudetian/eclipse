package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Card;
import util.DbHelp;
import util.RowMapper;

public class CardDao {

	DbHelp<Card> dbHelp = new DbHelp<>();
	
	/**
	 * ��
	 */
	public void saveCard(Card card) {
		String sql = "insert into t_card (cardid,name,sex,address,phone) values (?,?,?,?,?)";
		DbHelp.excuteUpdate(sql, card.getCardid(),card.getName(),card.getSex(),card.getAddress(),card.getPhone());
	}
	
	/**
	 * @param cardid
	 * @return
	 * ͨ������֤�Ų�ѯ
	 */
	public Card findByName(String cardid) {
		String sql = "select * from t_card where cardid = ?";
		Card card = dbHelp.queryForObject(sql, new CardRowMapper(), cardid);
		return card;
	}
	
	/**
	 * @param cardid
	 * @return
	 * ͨ��Cardid������֤�ţ���ѯ
	 */
	/*public Card findByCardid(String cardid) {
		String sql = "select * from t_card where cardid = ?";
		Card card = dbHelp.queryForObject(sql, new CardRowMapper(), cardid);
		return card;
	}*/
	
	private class CardRowMapper implements RowMapper<Card> {

		@Override
		public Card mapper(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Card card = new Card();
			card.setId(rs.getInt(1));
			card.setCardid(rs.getString(2));
			card.setName(rs.getString(3));
			card.setSex(rs.getString(4));
			card.setAddress(rs.getString(5));
			card.setPhone(rs.getString(6));
			return card;
		}
		
	}
	
}
