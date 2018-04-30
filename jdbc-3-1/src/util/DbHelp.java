package util;

import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelp {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_25";
	private static final String NAME = "root";
	private static final String PASSWORD = "longwear";
	
	public static Connection getconnection() {
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
	
	public static void executeUpdate(String sql,Object...progms) {
		Connection conn = getconnection();
		PreparedStatement pstat = null;
		try {
			pstat = conn.prepareStatement(sql);
			for (int i = 0; i < progms.length; i++) {
				pstat.setObject(i+1, progms[i]);
			}
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstat, conn);
	}

	private static void close(Statement stat,Connection conn) {
		try {
			if (stat!=null && !stat.isClosed()) {
				stat.close();
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

	public static Object queryForObject(String sql,RowMapper rowMapper,Object...progms) {
		Connection conn = getconnection();
		PreparedStatement pstat = null;
		ResultSet rs = null;
		Object obj = null;
		try {
			pstat = conn.prepareStatement(sql);
			for (int i = 0; i < progms.length; i++ ) {
				pstat.setObject(i+1, progms[i]);
			}
			rs = pstat.executeQuery();
			if (rs.next()) {
				obj =  rowMapper.mapper(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			close(rs,pstat, conn);
			return obj;
	}

	private static void close(ResultSet rs, PreparedStatement pstat, Connection conn) {
		try {
			if (rs!=null && !rs.isClosed()) {
				rs.close();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstat, conn);
		}
	}

	
	@SuppressWarnings("rawtypes")
	public static List queryForList(String sql,RowMapper rowMapper,Object... progms) {
			PreparedStatement pstat = null;
			ResultSet rs = null;
			Connection conn = getconnection();
			
			List<Object> list = new ArrayList<Object>();
			try {
				pstat = conn.prepareStatement(sql);
				for (int i = 0; i < progms.length; i++) {
					pstat.setObject(i+1, progms[i]);
				}
				
				rs = pstat.executeQuery();
				while (rs.next()) {
					Object obj = rowMapper.mapper(rs);
					list.add(obj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs, pstat, conn);
			}
		return list;
	}

//	public interface RowMapper {
//
//		public abstract Object mapper(ResultSet rs) throws SQLException;
//	}


}
