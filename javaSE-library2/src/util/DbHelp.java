package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DbHelp<T> {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_library";
	private static final String NAME = "root";
	private static final String PASSWORD = "longwear";
	
	/**
	 * @return
	 * 建立连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * @param sql
	 * @param progms
	 * 改
	 */
	public static void excuteUpdate(String sql,Object...progms) {
		Connection conn = getConnection();
		PreparedStatement pstat = null;
		try {
			pstat = conn.prepareStatement(sql);
			for (int i = 0; i < progms.length; i++) {
				pstat.setObject(i+1, progms[i]);
			}
			pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(conn,pstat);
	}

	/**
	 * @param conn
	 * @param pstat
	 * 释放资源
	 */
	private static void close(Connection conn, PreparedStatement pstat) {
		try {
			if (conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstat!=null && !pstat.isClosed()) {
					pstat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param sql
	 * @param progms
	 * @return
	 * 查询数量
	 */
	public  static int  queryForCount(String sql,Object...progms) {
		Connection conn = getConnection();
		PreparedStatement pstat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstat = conn.prepareStatement(sql);
			for (int i = 0; i < progms.length; i++) {
				pstat.setObject(i+1, progms[i]);
			}
			rs = pstat.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn,pstat,rs);
		return count;
	}
	/**
	 * @param sql
	 * @param rowMapper
	 * @param progms
	 * @return
	 * 查找单个对象
	 */
	public  T queryForObject(String sql,RowMapper<T> rowMapper,Object...progms) {
		Connection conn = getConnection();
		PreparedStatement pstat = null;
		T obj = null;
		ResultSet rs = null;
		try {
			pstat = conn.prepareStatement(sql);
			for (int i = 0; i < progms.length; i++) {
				pstat.setObject(i+1, progms[i]);
			}
			rs = pstat.executeQuery();
			if (rs.next()) {
				obj = rowMapper.mapper(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn,pstat,rs);
		return obj;
	}
	
	/**
	 * @param sql
	 * @param rowMapper
	 * @param progms
	 * @return
	 * 查找多个对象
	 */
	public  List<T> queryForList(String sql,RowMapper<T> rowMapper,Object...progms) {
		Connection conn = getConnection();
		PreparedStatement pstat = null;
		T obj = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<>();
		try {
			pstat = conn.prepareStatement(sql);
			for (int i = 0; i < progms.length; i++) {
				pstat.setObject(i+1, progms[i]);
			}
			rs = pstat.executeQuery();
			while (rs.next()) {
				obj = rowMapper.mapper(rs);
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn,pstat,rs);
		return list;
	}

	/**
	 * @param conn
	 * @param pstat
	 * @param rs
	 * 释放资源
	 */
	private static void close(Connection conn, PreparedStatement pstat, ResultSet rs) {
		try {
			if (rs!=null && !rs.isClosed()) {
				
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstat!=null && !pstat.isClosed()) {
					
					pstat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn!=null && !conn.isClosed()) {
						
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
}
