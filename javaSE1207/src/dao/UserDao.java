package dao;

import entity.User;
import util.DbHelp;

public class UserDao {

	public void save(User user) {
		String sql = "insert into t_user (name,address,tel) values (?,?,?)";
		DbHelp.executeUpdate(sql, user.getName(),user.getAddress(),user.getTel());
	}
	
	public void update(User user) {
		String sql = "update t_user set name = ?, address = ?, tel = ?, where id = ?";
		DbHelp.executeUpdate(sql,user.getName(),user.getAddress(),user.getTel(),user.getId() );
	}
	
	public void delete(int id) {
		String sql = "delete from t_user where id = ?";
		DbHelp.executeUpdate(sql, id);
	}
	
	public User findById(int id) {
		String sql = "select * from t_user where id = ?";
		return (User)DbHelp.queryForObject(sql, new UserRowMapper(),id );
	}
}
