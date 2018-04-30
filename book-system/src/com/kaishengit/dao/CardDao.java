package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.kaishengit.entity.Card;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.RowMapper;

public class CardDao {

	public Card findByCode(String code) {
		String sql = "select * from t_card where code = ?";
		return DbHelp.queryForObject(sql, new RowMapper<Card>() {

			@Override
			public Card mapperRow(ResultSet rs) throws SQLException {
				Card card = new Card();
				card.setId(rs.getInt("id"));
				card.setCode(rs.getString("code"));
				card.setName(rs.getString("name"));
				card.setTel(rs.getString("tel"));
				return card;
			}
			
		}, code);
	}

	public void save(Card card) {
		String sql = "insert into t_card (code, name, tel) values (?,?,?)";
		DbHelp.executeUpdate(sql, card.getCode(), card.getName(),card.getTel());
	}

}
