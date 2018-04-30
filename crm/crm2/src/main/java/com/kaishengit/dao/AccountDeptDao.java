package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;

import com.kaishengit.entity.AccountDept;
import com.kaishengit.util.DbHelp;

public class AccountDeptDao {

	public void save(List<AccountDept> accountDeptLists) {
		String sql = "insert into t_account_dept (account_id,dept_id) values";
		List<Object> arrays = new ArrayList<>();
		for (AccountDept accountDept : accountDeptLists) {
			sql +="(?,?),";
			arrays.add(accountDept.getAccountId());
			arrays.add(accountDept.getDeptId());
		}
		sql = sql.substring(0, sql.length()-1);
		DbHelp.executeUpdate(sql, arrays.toArray());
	}

	public void edit(String[] deptIds, String editId) {
		String sql = "delete from t_account_dept where account_id = ?";
		DbHelp.executeUpdate(sql, editId);
		List<AccountDept> accountDeptLists = new ArrayList<>();
		for (String deptId : deptIds) {
			AccountDept accountDept = new AccountDept();
			accountDept.setAccountId(Integer.parseInt(editId));
			accountDept.setDeptId(Integer.parseInt(deptId));
			accountDeptLists.add(accountDept);
		}
		save(accountDeptLists);
		
		
	}

}
