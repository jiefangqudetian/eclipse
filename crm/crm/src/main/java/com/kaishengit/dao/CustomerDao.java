package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Customer;
import com.kaishengit.util.DbHelp;

public class CustomerDao {

	public void add(Customer customer) {
		String sql = "insert into t_customer (cust_name,sex,job_title,mobile,address,trade,source,level,mark,account_id,reminder) values (?,?,?,?,?,?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, customer.getCustName(),customer.getSex(),customer.getJobTitle(),customer.getMobile(),customer.getAddress(),customer.getTrade(),customer.getSource(),customer.getLevel(),customer.getMark(),customer.getAccountId(),customer.getReminder());
	}

	public int count(int accountId) {
		String sql = "select count(*) from t_customer where account_id = ?";
		return DbHelp.query(sql, new ScalarHandler<Long>(), accountId).intValue();
	}

	public List<Customer> findCustomerListByPage(int accountId, int start, int pageSize) {
		String sql = "select * from t_customer where account_id = ? limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Customer.class, new BasicRowProcessor(new GenerousBeanProcessor())), accountId, start, pageSize);
	}


	/**
	 * 根据customer实体类对象更新数据库
	 * @param cust
	 */
	public void update(Customer cust) {
		String sql = "update t_customer set cust_name = ?, sex = ?, job_title=?, mobile = ?, address=?, trade=?, source=?,level=?,mark=?, account_id=?, last_concat_time =?, update_time=?, reminder=? where id=?";
		DbHelp.executeUpdate(sql, cust.getCustName(),cust.getSex(),cust.getJobTitle(),cust.getMobile(),cust.getAddress(),cust.getTrade(),cust.getSource(),cust.getLevel(),cust.getMark(),cust.getAccountId(),cust.getLastConcatTime(),cust.getUpdateTime(),cust.getReminder(),cust.getId());
	}


	public List<Customer> findByAccountId(int accountId) {
		String sql = "select * from t_customer where account_id = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Customer.class, new BasicRowProcessor(new GenerousBeanProcessor())), accountId);
	}

	public Customer findById(int custId) {
		String sql = "select * from t_customer where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Customer.class, new BasicRowProcessor(new GenerousBeanProcessor())), custId);
	}

	public List<Map<String, Object>> countLevel() {
		String sql = "select count(*) as count,level from t_customer group by level";
		return DbHelp.query(sql, new ResultSetHandler<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> handle(ResultSet rs) throws SQLException {
				List<Map<String, Object>> mapList = new ArrayList<>();
				while(rs.next()) {
					Map<String, Object> map = new HashMap<>();
					map.put("count", rs.getInt("count"));
					map.put("level", rs.getString("level"));
					mapList.add(map);
				}
				return mapList;
			}
		});
	}

	public List<Integer> findSaleByCustId(int id) {
		System.out.println(id);
		String sql = "select id from sale_chance where cust_id = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Integer.class), id);
		
	}

	public void delById(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from t_customer where id = ?";
		DbHelp.executeUpdate(sql, id);
		
	}

}
