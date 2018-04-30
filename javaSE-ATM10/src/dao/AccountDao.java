
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Account;
import util.DbHelp;
import util.RowMapper;



public class AccountDao {
	DbHelp<Account> dbHelp = new DbHelp<>();
	public void save(Account acc) {
			String sql = "insert into t_account (name, password, money, state) values (?, ?, ?, ?)";
			DbHelp.executeUpdate(sql, acc.getName(),acc.getPassword(),acc.getMoney(),acc.getState());
	}
	
	/**
	 * @param acc 取款机不用改账户，账户也不会重复，这样写也可以
	 */
	public void update(Account acc) {
			String sql = "update t_account set name = ?, password = ?, money = ?, state = ? where name = ?";
			DbHelp.executeUpdate(sql, acc.getName(),acc.getPassword(),acc.getMoney(),acc.getState(),acc.getName());
	}
	

	
	public Account findByName(String accname) {
		String sql = "select name, password, money, state from t_account where name = ?";
		Account acc = dbHelp.queryForObject(sql, new AccountRowMapper(), accname);
		return acc;
	}


	public List<Account> findAll() {
		String sql = "select name, password, money, state from t_account";
		List<Account> list = dbHelp.queryForList(sql, new AccountRowMapper());
		return list;
	}
	
	private class AccountRowMapper implements RowMapper<Account> {

		@Override
		public Account mapper(ResultSet rs) {
			Account acc = new Account();
			try {
				acc.setName(rs.getString(1));
				acc.setPassword(rs.getString(2));
				acc.setMoney(rs.getInt(3));
				acc.setState(rs.getString(4));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return acc;
		}
		 
	}
}
		

	
	
