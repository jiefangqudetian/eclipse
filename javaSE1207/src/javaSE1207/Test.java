package javaSE1207;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/mydb";
		String user = "root";
		String password = "longwear";
		try {
			
			//1.�������ݿ�����
			Class.forName(driver);
			
			//2.��ȡ���ݿ�����
			Connection conn = DriverManager.getConnection(url, user, password);
			
			// 3.ִ��sql
			String sql = "insert into t_stu (name,address,tel) values (?, ?, ?)";
//			String sql = "insert into t_stu (name,address,tel) values ('faker','korea',110)";
					
					
//			Statement stat = conn.createStatement();
//			stat.executeUpdate(sql);
					
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, "faker");
			pstat.setString(2, "korea");
			pstat.setInt(3, 110);
			pstat.executeUpdate();
			//4. �ͷ���Դ
//			stat.close();
			pstat.close();
			conn.close();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
