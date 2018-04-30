import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import entity.Account;

public class Test {

	public static void main(String[] args) {

		QueryRunner runner = new QueryRunner();
		Connection conn = ManagerConnection.getConnection();
		
		String sql = "insert into t_account (name ,password) values (? ,?)";
		try {
			runner.execute(ManagerConnection.getConnection(), sql, "joker", 123456, 123);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		String sql1 = "select * from t_account where id = ? ";
//		try {
//			Account account = runner.query(ManagerConnection.getConnection(), sql1, new BeanHandler<Account>(Account.class),2);
//			System.out.println(account);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			Map<String, Object> map;
//			map = runner.query(conn, sql1, new MapHandler(), 5);
//			Set<String> set = map.keySet();
//			for (String string : set) {
//				System.out.println(string+"--"+map.get(string));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		String sql2 = "select * from t_account ";
//		try {
//			List<Account> list = runner.query(ManagerConnection.getConnection(), sql2, new BeanListHandler<Account>(Account.class));
//			for (Account account : list) {
//				System.out.println(account);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			Map<Object, Account> maps = runner.query(conn, sql2, new BeanMapHandler<>(Account.class));
//			Set<Object> sets = maps.keySet();
//			for (Object obj : sets) {
//				System.out.println(obj+"--"+maps.get(obj));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String sql3 = "select count(*) from t_account";
		try {
			int count = runner.query(conn, sql3, new ScalarHandler<Long>()).intValue();
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
