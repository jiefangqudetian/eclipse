import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerConnection {

	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_25";
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
}
