package dao;


import entity.Admin;
import util.BeanRowMapper;
import util.DbHelp;

public class AdminDao {
	DbHelp<Admin> dbHelp = new DbHelp<>();
	public Admin findByNameAndPassword(String name,String password) {
		String sql = "select * from t_administrator where name = ? and password = ?";
		Admin admin = dbHelp.queryForObject(sql, new BeanRowMapper<>(Admin.class), name,password);
		return admin;
	}
	
}
