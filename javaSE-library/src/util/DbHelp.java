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
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_libyary";
	private static final String NAME = "root";
	private static final String PASSWORD = "longwear";
	
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
