package com.kaishengit.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.exception.DataAccessException;


public class DbHelp {

	private static QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
	private static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	/**
	 *执行insert delete update 插入 更新 删除  除了查询
	 */
	public static void executeUpdate(String sql, Object... params) {
		try {
			logger.info("SQL:{}",sql);
			runner.update(sql, params);
		} catch (SQLException e) {
			logger.error("执行{}异常", sql);
			throw new DataAccessException("ִ执行" + sql + "异常", e);
		} 
	}

	/**
	 * 执行 查询
	 */
	public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
		try {
			logger.info("SQL:{}",sql);
			return runner.query(sql, rsh, params);
		} catch (SQLException e) {
			logger.error("执行{}异常", sql);
			throw new DataAccessException("ִ执行" + sql + "异常", e);
		} 
	}

	/**
	 * 执行 插入
	 */
	public static int executeInsert(String sql, Object...params) {
		
		try {
			logger.info("SQL:{}",sql);
			return runner.insert(sql, new ScalarHandler<Long>(),params).intValue();
		} catch (SQLException e) {
			logger.error("执行{}异常", sql);
			throw new DataAccessException("ִ执行" + sql + "异常", e);
		}
	}
}