package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/company?characterEncoding=utf-8";
		String user = "root";
		String psw = "root";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, psw);
		
		return conn;
	}
	
	public static void close(Connection conn, Statement stat, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}

			if (stat != null) {
				stat.close();
			}

			if (rs != null) {
				rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstat, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}

			if (pstat != null) {
				pstat.close();
			}

			if (rs != null) {
				rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
