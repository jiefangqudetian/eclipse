package javaSE1207;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql:///mydb";
		String user = "root";
		String password = "longwear";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "select * from t_user where id = 2 ";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
		//	System.out.println("id:"+"  name:"+"  age"+"  address:");
			while (rs.next()) {
//				int id = rs.getInt(1);
//				String name = rs.getString(2);
//				int stuage = rs.getInt(3);
//				String address = rs.getString(4);
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int stuage = rs.getInt("stuage");
				String address = rs.getString("address");
				
				//System.out.println(id+"   "+name+"   "+stuage+"   "+address);
				System.out.println("id:"+id+"  name:"+name+"  age"+stuage+"  address:"+address );
			}
		} catch (Exception e) {
		} finally {
			try {
				if (rs!=null && !rs.isClosed()) {
					
					rs.close();
				}
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			} finally {
				
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
			
		}
		
		
		
	}

}
