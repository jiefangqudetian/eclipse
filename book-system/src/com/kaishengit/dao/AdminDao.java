package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.kaishengit.entity.Admin;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.RowMapper;

public class AdminDao {

	/**
	 * 根据用户名名查找admin对象
	 * @return
	 */
	public Admin findByName(String name) {
		String sql = "select * from t_admin where name = ?";
		return DbHelp.queryForObject(sql, new RowMapper<Admin>(){

			@Override
			public Admin mapperRow(ResultSet rs) throws SQLException {
				Admin admin = new Admin();
				admin.setId(rs.getInt(1));
				admin.setName(rs.getString(2));
				admin.setPassword(rs.getString(3));
				return admin;
			}
			
		}, name);
	}

}
