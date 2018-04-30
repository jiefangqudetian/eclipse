package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Account;
import util.RowMapper;

public class AccountRowMapper implements RowMapper {

	@Override
	public Object mapper(ResultSet rs) throws SQLException {
		Account acc = new Account();

		acc.setId(rs.getInt(1));
		acc.setName(rs.getString(2));
		acc.setPassword(rs.getString(3));
		return acc;
	}

}
