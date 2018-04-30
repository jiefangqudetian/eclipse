package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Admin;
import util.DbHelp;
import util.RowMapper;

public class AdminDao {
	DbHelp<Admin> dbHelp = new DbHelp<>();
	public Admin findByNameAndPassword(String name,String password) {
		String sql = "select * from t_administrator where name = ? and password = ?";
		Admin admin = dbHelp.queryForObject(sql, new adminRowMapper(), name,password);
		return admin;
	}
	
	private class adminRowMapper implements RowMapper<Admin>  {
		public Admin mapper(ResultSet rs) throws SQLException {
			Admin admin = new Admin();
			admin.setId(rs.getInt(1));
			admin.setName(rs.getString(2));
			admin.setPassword(rs.getString(3));
			return admin;
		}
	}
}
