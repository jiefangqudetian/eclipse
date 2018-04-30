package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Administrator;
import util.DbHelp;
import util.RowMapper;

public class AdministratorDao {
	DbHelp<Administrator> dbHelp = new DbHelp<>();
	public Administrator findByNameAndPassword(String name,String password) {
		String sql = "select name,password from t_administrator where name = ? and password = ?";
		Administrator administrator = dbHelp.queryForObject(sql, new administratorRowMapper(), name,password);
		return administrator;
	}
	
	private class administratorRowMapper implements RowMapper<Administrator>  {
		public Administrator mapper(ResultSet rs) throws SQLException {
			Administrator admin = new Administrator();
			admin.setName(rs.getString(1));
			admin.setPassword(rs.getString(2));
			return admin;
		}
	}
}
