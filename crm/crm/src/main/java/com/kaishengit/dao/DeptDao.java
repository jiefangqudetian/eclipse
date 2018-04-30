package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Account;
import com.kaishengit.entity.Dept;
import com.kaishengit.util.DbHelp;

public class DeptDao {

	public Dept findByName(String deptName) {
		String sql = "select * from t_dept where deptname = ?";
		return DbHelp.query(sql, new BeanHandler<>(Dept.class), deptName);
	}

	public void save(Dept dept) {
		String sql = "insert into t_dept (deptname, pid) values (?,?)";
		DbHelp.executeUpdate(sql, dept.getDeptName(), dept.getpId());
	}

	public List<Dept> findAll() {
		String sql = "select * from t_dept";
		return DbHelp.query(sql, new BeanListHandler<>(Dept.class));
	}

	public String findNameByAccount(Account account) {
		String sql = "select deptname from t_dept td,t_account ta,t_account_dept tad where td.id=tad.dept_id and tad.account_id = ta.id and ta.id=?";
		
		return DbHelp.query(sql, new ScalarHandler<String>(), account.getId());
	}
}
