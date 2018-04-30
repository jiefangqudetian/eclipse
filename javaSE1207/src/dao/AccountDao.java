
package dao;
import java.util.List;
import entity.Account;
import util.DbHelp;



public class AccountDao {

	public void save(Account acc) {
			String sql = "insert into t_account (name, password) values (?, ?)";
			DbHelp.executeUpdate(sql, acc.getName(),acc.getPassword());
	}
	
	public void update(Account acc) {
			String sql = "update t_account set name = ?, password = ? where id = ?";
			DbHelp.executeUpdate(sql, acc.getName(),acc.getPassword(),acc.getId());
	}
	

	public void delete(int id) {
			String sql = "delete from t_Account where id = ?";
			DbHelp.executeUpdate(sql, id);
	}

	public Account findById(int id) {
		String sql = "select id, name, password from t_account where id = ?";
		Account acc = (Account)DbHelp.queryForObject(sql, new AccountRowMapper(), id);
		return acc;
	}

	public Account findByNameAndPassword(String accName, String accPassword) {
		String sql = "select id, name, password from t_account where name = ?,password = ?";
		Account acc = (Account)DbHelp.queryForObject(sql, new AccountRowMapper(), accName,accPassword);
		return acc;
	}

	public List<Account> findAll() {
		String sql = "select id, name, password from t_account";
		@SuppressWarnings("unchecked")
		List<Account> list = DbHelp.queryForList(sql, new AccountRowMapper());
		return list;
	}
}
		

	
	
