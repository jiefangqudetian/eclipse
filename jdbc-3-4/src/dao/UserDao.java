package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import util.DbHelp;

public class UserDao {

	DbHelp<User> dbHelp = new DbHelp<>();
	
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
		return dbHelp.queryForObject(sql, new UserRowMapper(),id );
	}
	
	public class UserRowMapper implements DbHelp.RowMapper<User>{

		@Override
		public User mapper(ResultSet rs) throws SQLException {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setAddress(rs.getString(3));
			user.setTel(rs.getString(4));
			return user;
		}

	}
}
