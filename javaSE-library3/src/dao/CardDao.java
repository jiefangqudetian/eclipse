package dao;


import entity.Card;
import util.BeanRowMapper;
import util.DbHelp;

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
		Card card = dbHelp.queryForObject(sql, new BeanRowMapper<>(Card.class), cardid);
		return card;
	}
	
	/**
	 * @param cardid
	 * @return
	 * ͨ��Cardid������֤�ţ���ѯ
	 */
	
	
}
